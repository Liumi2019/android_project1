package com.example.app;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DogTest {
    private Dog mDog;

    // 只执行一次 必须是 "public static void" 修饰
    @BeforeClass
    public static void init() {
        System.out.println("stat running all test");
    }

    // 每个测试执行前都执行一次
    @Before
    public void createDog() {
        System.out.println("current test start");
        mDog = new Dog("Create");
    }

    // 每个测试执行完执行一次
    @After
    public void doNothing() {
        System.out.println("current test end");
    }

    // 只执行一次
    @AfterClass
    public static void deinit() {
        System.out.println("end all test");
    }

    @Test
    public void ConstructorTest() {
        Dog dog = new Dog();
        assertEquals(dog.getName(), "Lili");
        assertEquals(dog.getAge(), 0);

        dog = new Dog("nini");
        assertEquals(dog.getName(), "nini");
        assertEquals(dog.getAge(), 0);
    }

    // 暂不执行 @Ignore("TODO")
    @Test
    public void getInfoTest() {
        Dog dog = new Dog("123");
        // 断言 简单方便的判断
        assertEquals(dog.getInfo(), "Name:123; age:0");
    }

    @Test
    public void treadTest() {
        Dog dog = new Dog("123");
        assertEquals(dog.testThread(), 0);
    }

    // 断言高阶匹配 使用匹配符not, is, allOf, lessThan ...
    @Test
    public void testDogName() {
         assertThat(mDog.getInfo(), not("Name:123; age:1"));
    }

    // 测试方法执行顺序不定 可以使用 @FixMethodOrder 固定顺序

    // 测试高阶知识
    // @RunWith(JUnit4.class), SpringJUnit4ClassRunner.class, 使用那个运行器执行
    // suite 测试套件内的测试样例一起执行，包括顺序，且可以嵌套
    // @RunWith(Suite.class) + @Suite.SuiteClasses({TestFeatureLogin.class,TestFeatureLogout.class})
    // 测试参数配置 Parameterized
    // Categories 可分类执行测试代码

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
