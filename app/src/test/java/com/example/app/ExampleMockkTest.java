package com.example.app;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExampleMockkTest {
    private static final String FAKE_STRING = "testMock";

    @Mock
    Context context;

    @Test
    public void runMockContextTest() {
        when(context.getString(R.string.mockTestName)).thenReturn(FAKE_STRING);
        assertThat(context.getString(R.string.mockTestName), is(FAKE_STRING));

        when(context.getPackageName()).thenReturn("testMock");
        System.out.println(context.getPackageName());
    }

}
