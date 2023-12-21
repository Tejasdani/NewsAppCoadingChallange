package com.example.newsapp;

import static org.junit.Assert.assertEquals;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        android.content.Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.newsapp", appContext.getPackageName());
    }
}
