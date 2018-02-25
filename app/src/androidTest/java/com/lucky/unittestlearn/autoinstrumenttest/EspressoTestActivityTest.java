package com.lucky.unittestlearn.autoinstrumenttest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lucky.unittestlearn.R;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;
/**
 * Created by zfz on 2017/3/15.
 * UI上的操作都是在LargeTest
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoTestActivityTest {

    //@Rule 注解@Rule的作用就是在测试Test之前可以保证EspressoTestActivity可以正常运行
    //同时也在Test结束之后对EspressoTestActivity进行销毁
    @Rule
    public ActivityTestRule<EspressoTestActivity> rule=new ActivityTestRule<EspressoTestActivity>(EspressoTestActivity.class);


    @BeforeClass
    public static void testBeforeClass(){

    }

    @Before
    public void testBefore(){

    }

    @Test
    public void sayHello(){
        Espresso.onView(ViewMatchers.withId(R.id.edittext))
                .perform(ViewActions.click(),ViewActions.typeText("Aragon is Great!"),
                         ViewActions.replaceText("hello world!"),ViewActions.closeSoftKeyboard())
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.btn))
                .perform(ViewActions.click());
    }

    @After
    public void testAfter(){

    }


    @AfterClass
    public static void testAfterClass(){

    }


}