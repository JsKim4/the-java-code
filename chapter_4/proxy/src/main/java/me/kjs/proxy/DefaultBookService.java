package me.kjs.proxy;

public class DefaultBookService implements BookService {
  @Override
  public void rent() {
    System.out.println("rent");
  }

  @Override
  public void returnBook() {
    System.out.println("return");
  }
}
