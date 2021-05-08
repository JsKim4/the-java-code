package me.kjs.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class CglibBookServiceTest {
  @Test
  void di() {
    BookService defaultBookService = new DefaultBookService();
    MethodInterceptor handler = new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("aaaa");
        Object invoke = method.invoke(defaultBookService, args);
        System.out.println("bbbb");
        return invoke;
      }
    };
    BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

    bookService.rent();
    bookService.returnBook();
  }
}
