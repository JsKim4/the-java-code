package me.kjs;


import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor {
  @Override
  public Set<String> getSupportedAnnotationTypes() {
    HashSet hashSet = new HashSet<String>();
    hashSet.add(Magic.class.getName());
    return hashSet;
  }

  @Override
  public SourceVersion getSupportedSourceVersion() {
    return SourceVersion.latestSupported();
  }

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
}
