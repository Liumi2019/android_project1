package com.example.app;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private Dog dog = new Dog("123");

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
        assertEquals(dog.getInfo(), "Name:123; age:0");
    }
}