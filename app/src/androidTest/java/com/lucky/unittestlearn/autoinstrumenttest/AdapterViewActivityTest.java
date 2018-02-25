package com.lucky.unittestlearn.autoinstrumenttest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by zfz on 2017/3/15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class AdapterViewActivityTest {

    @Rule
    public ActivityTestRule<AdapterViewActivity> rule=new ActivityTestRule<AdapterViewActivity>(AdapterViewActivity.class);

    @Test
    public void testAdapterView(){
        Espresso.onData(
                AllOf.allOf(
                        is(IsInstanceOf.instanceOf(SearchItem.class)),
                        teacherSearchItemWithName("Teacher 99")
                )
               ).perform(ViewActions.click());

    }

    public static Matcher<Object> teacherSearchItemWithName(final String name){
        return new BoundedMatcher<Object,SearchItem>(SearchItem.class) {

            @Override
            protected boolean matchesSafely(SearchItem item) {
                return item!=null
                         && item.getKeyword()!=null
                         && name.equals(item.getKeyword());
            }

            @Override
            public void describeTo(Description description) {
               description.appendText("SearchItem has Name: "+name);
            }
        };
    }

}