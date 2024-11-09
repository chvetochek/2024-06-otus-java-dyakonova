package ru.otus;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class ExampleTest {

    @Before()
    public void setUp() {
        System.out.println("Метод setUp");
    }

    @Before()
    public void setUp2() {
        System.out.println("Метод setUp2");
    }

    @Test()
    public void testExample() {
        System.out.println("Метод testExample");
    }

    @Test()
    public void testExample2() {
        System.out.println("Метод testExample2");
    }

    @Test()
    public void testExample3() {
        System.out.println("Метод testExample3");
        throw new RuntimeException("Неуспешное выполнение теста");
    }

    @After()
    public void afterTest() {
        System.out.println("Метод afterTest");
    }

    @After()
    public void afterTest2() {
        System.out.println("Метод afterTest2");
    }

}
