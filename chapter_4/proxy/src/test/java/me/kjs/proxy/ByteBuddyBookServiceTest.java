package me.kjs.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static net.bytebuddy.matcher.ElementMatchers.named;

public class ByteBuddyBookServiceTest {
  @Test
  void di() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Class<? extends DefaultBookService> proxyClass = new ByteBuddy().subclass(DefaultBookService.class)
        .method(named("rent")).intercept(InvocationHandlerAdapter.of((proxy, method, args) -> {
          DefaultBookService defaultBookService = new DefaultBookService();
          System.out.println("aaaa");
          Object invoke = method.invoke(defaultBookService, args);
          System.out.println("bbbb");
          return invoke;
        }))
        .make().load(DefaultBookService.class.getClassLoader()).getLoaded();

    BookService bookService = proxyClass.getConstructor(null).newInstance();

    bookService.rent();
    bookService.returnBook();
  }
}
