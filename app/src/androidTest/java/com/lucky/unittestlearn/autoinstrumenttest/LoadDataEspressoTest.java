package com.lucky.unittestlearn.autoinstrumenttest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.lucky.unittestlearn.R;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author zfz
 * Created by zfz on 2018/2/24.
 */
@RunWith(AndroidJUnit4.class)
public class LoadDataEspressoTest {

    @Rule
    public ActivityTestRule<LoadLocalDataActivity> activityTestRule =
            new ActivityTestRule<LoadLocalDataActivity>(LoadLocalDataActivity.class);

    private IdlingResource mIdlingResources;

    @Before
    public void registerIdlingResource(){
        mIdlingResources = activityTestRule.getActivity().getIdlingResource();
        Espresso.registerIdlingResources(mIdlingResources);
    }


    @Test
    public void loadData(){
        // 执行点击操作 ， 进入耗时
        Espresso.onView(withId(R.id.btn_load_data)).perform(ViewActions.click());
        // 验证
        Espresso.onView(withId(R.id.tv_load_data)).check(ViewAssertions.matches(withText("Data Load Finished 4")));
    }

    @After
    public void unregisterIdlingResources(){
        if (mIdlingResources!=null){
            Espresso.unregisterIdlingResources(mIdlingResources);
        }
    }


}
