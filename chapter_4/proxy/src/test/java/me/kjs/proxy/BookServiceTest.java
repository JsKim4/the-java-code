package me.kjs.proxy;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

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