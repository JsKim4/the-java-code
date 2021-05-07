# 4장 다이나믹 프록시

## 목차
- 스프링 데이터 JPA는 어떻게 동작할까?
- 프록시 패턴
- 다이나믹 프록시 실습
- 클래스의 프록시가 필요하다면?
- 다이나믹 프록시 정리


## 스프링 데이터 JPA는 어떻게 동작할까?
- Proxy -> Spring AOP -> ProxyFactory

- RepositotyFactorySupport 에서 Proxy 객체를 만들어줌 -> 해당 프록시 객체가 빈으로 등록되어 주입됨

## 프록시 패턴


    클라이언트 -> 서브젝트(인터페이스)
                    |
                    |
                    |
                ㅡㅡㅡㅡㅡ
                |        |
                |        |
                |        |
              프록시 -> 리얼 서브젝트

- 동일한 인터페이스를 프록시와 리얼 서브젝트가 구현하고 실제 클라이언트를 프록시를 통해 리억 서브젝트를 실행시킴
- 프록시패턴을 통해 리얼 서브젝트는 기능 구현에만 집중하고, 프록시에서 로깅이나 접근제한, 트랜잭션등을 제공함
- 프록시 패턴을 사용하면 동일한 코드가 반복될 수 있음
- 그래서 런타임 시점에서 동적으로 생성하여 사용하는 기능이 있음

## 다이나믹 프록시 실습

```
class BookServiceTest {

  BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{BookService.class},
      new InvocationHandler() {
        final BookService bookService = new DefaultBookService();
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          if (method.getName().equals("rent")) {
            System.out.println("aaaa");
            Object invoke = method.invoke(bookService, args);
            System.out.println("bbbb");
            return invoke;
          }
          return method.invoke(bookService, args);
        }
      });

  @Test
  void di() {
    bookService.rent();
    bookService.returnBook();
  }

}
```
- 유연한 구조는 아니다!
- 그래서 스프링은 스프링 AOP를 만들었음
- 클래스 기반으로 프록시를 만들지 못함