package me.kjs;


/*
*
*
* */
@MyAnnotation("asd")
public class Book {

    private String a = "a";
    private static String B = "BOOK";
    private static String C = "BOOK";
    public String d = "d";
    protected String e = "e";

    public Book() {
    }

    @AnotherAnnotation
    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("f");
    }

    public void g() {
        System.out.println("g");
    }

    @AnotherAnnotation
    public int h() {
        return 100;
    }
}
