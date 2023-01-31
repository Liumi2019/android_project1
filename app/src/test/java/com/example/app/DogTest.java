package com.example.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DogTest {
    @Test
    public void ConstructorTest() {
        Dog dog = new Dog();
        assertEquals(dog.getName(), "Lili");
        assertEquals(dog.getAge(), 0);

        dog = new Dog("nini");
        assertEquals(dog.getName(), "nini");
        assertEquals(dog.getAge(), 0);
    }

    @Test
    public void getInfoTest() {
        Dog dog = new Dog("123");
        assertEquals(dog.getInfo(), "Name:123; age:0");
    }

    @Test
    public void treadTest() {
        Dog dog = new Dog("123");
        assertEquals(dog.testThread(), 0);
    }
}

//@RunWith(MockitoJUnitRunner.class)
//public class ExampleMockitoTest {
//    private static final String mockTestName = "testMock";
//
//    private static class ContextMockReturn {
//        public static final String mockTestName = "testMock";
//    }
//
//    @Mock
//    Context context;
//
//    @Test
//    public void runMockContextTest() {
//        when(context.getString(R.string.mockTestName)).thenReturn(ContextMockReturn.mockTestName);
//        assertEquals(mockTestName, context.getString(R.string.mockTestName));
//
//        when(context.getPackageName()).thenReturn("testMock");
//        System.out.println(context.getPackageName());
//    }
//
//}
