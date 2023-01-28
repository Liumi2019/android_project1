package com.example.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockitoTest {
    private static final String mockTestName = "testMock";

    private static class ContextMockReturn {
       public static final String mockTestName = "testMock";
    }

    @Mock
    Context context;

    @Test
    public void runMockContextTest() {
        when(context.getString(R.string.mockTestName)).thenReturn(ContextMockReturn.mockTestName);
        assertEquals(mockTestName, context.getString(R.string.mockTestName));

        when(context.getPackageName()).thenReturn("testMock");
        System.out.println(context.getPackageName());
    }

}
