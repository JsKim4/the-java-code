# 애노테이션 프로세서

## 목차
- 롬복은 어떻게 동작할까?
- 어노테이션 프로세서 실습
- 애노테이션 프로세서 정리

## 롬복은 어떻게 동작할까?
롬복 동작원리
- 컴파일 시점에 애노테이션 프로세서를 사용하여 소스코드의 AST를 조작한다.

논란거리
- 애노테이션 프로세서는 애노테이션이 붙은 정보를 `참조`만 할 수 있지만 롬복은 Processor를 하위타입으로 캐스팅하여 비공개 API를 사용해서 코드를 수정한다. -> 해킹으로 보는 시각도 있다. 또한 비공개 API기 때문에 자바 버전이 변경됨에 따라 변경될 가능성도 존재한다.
- 대안
    - AutoValue
    - Immutables

## 어노테이션 프로세서 실습
- java service provider
- java poet
- auto-service
- Filer
    - 소스 코드, 클래스 코드, 리소스를 생성할 수 있는 인터페이스
```
@Override
  public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
    Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Magic.class);
    for (Element element : elements) {
      checkElement(element);
      TypeElement typeElement = (TypeElement) element;
      ClassName className = ClassName.get(typeElement);

      MethodSpec pullOut = MethodSpec.methodBuilder("pullOut")
          .addModifiers(Modifier.PUBLIC)
          .returns(String.class)
          .addStatement("return $S", "Rabbit!")
          .build();

      TypeSpec magicMoja = TypeSpec.classBuilder("MagicMoja")
          .addModifiers(Modifier.PUBLIC)
          .addMethod(pullOut)
          .addSuperinterface(className)
          .build();

      Filer filer = processingEnv.getFiler();

      try {
        JavaFile.builder(className.packageName(), magicMoja)
            .build()
            .writeTo(filer);
      } catch (IOException e) {
        e.printStackTrace();
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "FATAL ERROR: " + e);
      }
    }
    return true;
  }

  private void checkElement(Element element) {
    Name simpleName = element.getSimpleName();
    if (element.getKind() != ElementKind.INTERFACE) {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, "Magic Annotation can not be used on " + simpleName);
    } else {
      processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Processing " + simpleName);
    }
  }
```

## 어노테이션 프로세서 정리
어노테이션 프로세서 사용 예
- 롬복
- AutoService: java.util.SerivceLoader용 파일 생성 유틸리티
- Override Annotation Processor
    - 사용이유 : 오버라이드 아닌거를 잡아줌!

애노테이션 프로세서 장점
- 런타임 비용 0
