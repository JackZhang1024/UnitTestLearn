package com.lucky.unittestlearn.autoinstrumenttest;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

import com.lucky.unittestlearn.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * @author zfz
 * Created by zfz on 2017/3/15.
 */

@RunWith(AndroidJUnit4.class)
public class AppStartActivityTest extends ActivityInstrumentationTestCase2<AppStartActivity> {

    private AppStartActivity mActivity;
    private TextView mContentView;


    public AppStartActivityTest() {
        //所有的ActivityInstrumentationTestCase2子类都需要调用该父类的super(Clazz)方法
        super(AppStartActivity.class);
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        //@Before注解表示在执行所有的testCase之前要做的事情
        //Injects instrumentation into this test case. This method is
        //called by the test runner during test setup.
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        // getActivity()方法会在开始所有的testCase之前启动相应的Activity
        mActivity = getActivity();
        // findViewById()方法是找到要测试的控件
        mContentView = (TextView) mActivity.findViewById(R.id.textView);

    }

    @Test
    public void testPreconditions() throws Exception {
        // @Test 注解表示一个测试用例方法
        assertNotNull("AppStartActivity is null ",mActivity);
    }


    @Test
    public void testContentDisplayed() {
         // 这里是我们测试的目标，判断目标控件的text文本内容不为空
         String content=mContentView.getText().toString();
         assertNotNull("AppStartActivity Content is Null ",content);

    }


    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }


}