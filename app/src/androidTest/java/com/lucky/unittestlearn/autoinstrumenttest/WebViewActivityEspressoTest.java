package com.lucky.unittestlearn.autoinstrumenttest;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.web.assertion.WebViewAssertions;
import android.support.test.espresso.web.webdriver.DriverAtoms;
import android.support.test.espresso.web.webdriver.Locator;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.web.sugar.Web.onWebView;
import static android.support.test.espresso.web.webdriver.DriverAtoms.findElement;
import static android.support.test.espresso.web.webdriver.DriverAtoms.webClick;
import static org.hamcrest.Matchers.containsString;

/**
 * @author zfz
 * Created by zfz on 2018/2/24.
 */


@RunWith(AndroidJUnit4.class)
public class WebViewActivityEspressoTest {

    @Rule
    public ActivityTestRule<WebViewEspressoActivity> activityTestRule =
            new ActivityTestRule<WebViewEspressoActivity>(WebViewEspressoActivity.class, false);


    @Test
    public void webViewTest() throws Exception{
        //传递数据到WebViewActivity
        Intent intent = new Intent();
        intent.putExtra(WebViewEspressoActivity.WEB_URL, "https://m.baidu.com/");
        activityTestRule.launchActivity(intent);
        //通过name为"word"找到搜索输入框
        onWebView().withElement(findElement(Locator.NAME, "word"))
                //往输入框中输入字符串"android"
                .perform(DriverAtoms.webKeys("android"))
                //通过id为"index-bn"找到"百度一下"按钮
                .withElement(DriverAtoms.findElement(Locator.ID, "index-bn"))
                //执行点击事件
                .perform(webClick())
                //通过id为"results"找到结果div
                .withElement(DriverAtoms.findElement(Locator.ID, "results"))
                //检查div中是否包含字符串"android"
                .check(WebViewAssertions.webMatches(DriverAtoms.getText(), Matchers.containsString("android")));

    }

}
