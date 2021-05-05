package me.kjs.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired BookService bookService;

    @Test
    void test(){
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }
}