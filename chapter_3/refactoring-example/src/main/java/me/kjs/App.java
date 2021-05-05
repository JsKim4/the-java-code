package me.kjs;

import java.lang.reflect.Modifier;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws ClassNotFoundException {
        chap01();
        chap02();
    }

    private static void chap02() {
        Arrays.stream(Book.class.getAnnotations()).forEach(System.out::println);
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);
        Arrays.stream(MyBook.class.getDeclaredAnnotations()).forEach(System.out::println);
        Arrays.stream(MyBook.class.getDeclaredFields())
                .forEach(f -> {
                    Arrays.stream(f.getAnnotations()).forEach(a -> {
                        if (a instanceof AnotherAnnotation) {
                            AnotherAnnotation myAnnotation = (AnotherAnnotation) a;
                            System.out.println(myAnnotation.value());
                            System.out.println(myAnnotation.number());
                        }
                    });
                });
    }

    private static void chap01() throws ClassNotFoundException {
        Class<Book> bookClass = Book.class;
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();
        Class<?> aClass1 = Class.forName("me.kjs.Book");
        Arrays.stream(bookClass.getDeclaredFields()).forEach(
                f -> {
                    try {
                        f.setAccessible(true);
                        System.out.printf("%s %s \n", f, f.get(book));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
        );
        System.out.println();
        System.out.println();
        Arrays.stream(bookClass.getMethods()).forEach(System.out::println);
        System.out.println();
        System.out.println();
        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);
        System.out.println();
        System.out.println();
        System.out.println(MyBook.class.getSuperclass());
        System.out.println();
        System.out.println();
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
        Arrays.stream(Book.class.getDeclaredFields()).forEach(
                f -> {
                    int modifiers = f.getModifiers();
                    System.out.println(f);
                    System.out.println(Modifier.isPrivate(modifiers));
                    System.out.println(Modifier.isStatic(modifiers));
                }
        );
    }
}
