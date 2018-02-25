package com.lucky.unittestlearn.uiautomatortest;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by zfz on 2018/2/23.
 */


/**
 * UiObject uiObject = uiDevice.findObject(new UiSelector().resourceId("com.lucky.androidlearn:id/btn_architecture_mvc").className("android.widget.Button"));
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class UiAutomatorTest {
    private static final String PACKAGE_UIAUTOMATOR_NAME = "com.lucky.unittestlearn";
    private static final String PACKAGE_SETTINGS = "com.android.settings";

    @Test
    public void testUiAutomatorApp() throws Exception {
        // 初始化一个UiDevice对象
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // 点击Home键 返回桌面
        uiDevice.pressHome();
        String launcherPackageName = uiDevice.getLauncherPackageName();
        Assert.assertThat(launcherPackageName, notNullValue());
        // 启动UiAutomatorTest
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        Context context = InstrumentationRegistry.getContext();
        // getTargetContext()获取的是目标应用程序的上下文
        Context context1 = InstrumentationRegistry.getTargetContext();
        Context context2 = instrumentation.getContext();
        if (context == context1) {
            Log.i("Chris", "InstrumentationRegistry getContext == getTargetContext");
        } else {
            Log.i("Chris", "InstrumentationRegistry getContext != getTargetContext");
        }
        if (context == context2) {
            Log.i("Chris", "InstrumentationRegistry getContext == Instrumentation getContext");
        } else {
            Log.i("Chris", "InstrumentationRegistry getContext != Instrumentation getContext");
        }
        //Intent intent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_UIAUTOMATOR_NAME);
        Intent intent = new Intent();
        intent.setClass(context1, UiAutomatorActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context1.startActivity(intent);
        // 等待应用程序启动
        uiDevice.wait(Until.hasObject(By.pkg(PACKAGE_UIAUTOMATOR_NAME).depth(0)), 3);

        // 找到输入框一
        UiObject etInput1 = uiDevice.findObject(new UiSelector().resourceId("com.lucky.unittestlearn:id/et_input_1").className(EditText.class));
        etInput1.setText("12");

        // 找到输入框二
        UiObject etInput2 = uiDevice.findObject(new UiSelector().resourceId("com.lucky.unittestlearn:id/et_input_2").className(EditText.class));
        etInput2.setText("10");

        // 找到按钮
        UiObject btnCalculate = uiDevice.findObject(new UiSelector().resourceId("com.lucky.unittestlearn:id/btn_calculate").className(Button.class));
        btnCalculate.click();

        UiObject tvResult = uiDevice.findObject(new UiSelector().resourceId("com.lucky.unittestlearn:id/tv_result").className(TextView.class));
        //判断结果与预期是否匹配
        assertEquals(tvResult.getText(), "22");

        // 通过id找到RecycleView
        // 该方法可以找到可以滑动的UI控件
        UiScrollable recycleView = new UiScrollable(new UiSelector()
                .resourceId("com.lucky.unittestlearn:id/rv")
                .className(RecyclerView.class)
        );
        // 滑到底部
        recycleView.flingForward();
        // 滑到顶部
        recycleView.flingBackward();
        // 找到item 玩家5
        UiObject item5 = recycleView.getChild(new UiSelector().text("玩家 5"));
        // 点击然后会弹出一个对话框
        item5.click();
        // 通过文本 "确定"找到对话框中的确定按钮
        UiObject btnConfirm = uiDevice.findObject(new UiSelector().text("确定").className(Button.class));
        // 点击确定按钮 关闭对话框
        btnConfirm.click();
        // 另外一种方式找到Item2
        UiObject item2 = uiDevice.findObject(new UiSelector()
                .className(RecyclerView.class)
                .resourceId("com.lucky.unittestlearn:id/rv")
                .childSelector(new UiSelector().text("玩家 2"))
        );
        // 点击item2 弹出对话框
        item2.click();
        // 点击返回关闭对话框
        uiDevice.pressBack();
    }

    @Test
    public void testSettingsApp() throws Exception {
        // 初始化一个UiDevice对象
        Context context = InstrumentationRegistry.getContext();
        UiDevice uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        // 回到Home界面
        uiDevice.pressHome();
        // 启动设置页面
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(PACKAGE_SETTINGS);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(launchIntent);
        // 通过id找到scrollView
        UiScrollable scrollView = new UiScrollable(new UiSelector()
                .className(ScrollView.class)
                .resourceId("com.android.settings:id/dashboard")
        );
        // 滑动到底部
        scrollView.flingForward();
        // 通过文本找到手机
        UiObject aboutPhone = uiDevice.findObject(new UiSelector().text("关于平板电脑"));
        // 点击进入手机信息界面
        aboutPhone.click();
        // 通过description找到向上返回的ImageButton
        UiObject imgBack = uiDevice.findObject(new UiSelector()
                .className(ImageButton.class)
                .description("向上导航")
        );
        // 点击返回按钮 返回到上一页
        imgBack.click();
        // 滑动到包含"提示和通知的"地方
        scrollView.scrollTextIntoView("提示音和通知");
        // 通过显示的文本找到控件
        UiObject notify = uiDevice.findObject(new UiSelector().text("提示音和通知"));
        // 点击跳转到到下一个界面
        notify.click();
        // 通过显示的文本"手机铃声"找到控件
        UiObject sound =  uiDevice.findObject(new UiSelector().text("手机铃声"));
        // 点击跳转到铃声对话框
        sound.click();
        // 通过id找到铃声列表
        UiScrollable listView = new UiScrollable(new UiSelector()
                .className(ListView.class)
                .resourceId("android:id/select_dialog_listview")
        );
        listView.scrollTextIntoView("Sceptrum");
        UiObject sceptrum = listView.getChild(new UiSelector().text("Sceptrum"));
        sceptrum.click();
        // 通过文本找到"确定"按钮
        UiObject btnConfirm  = uiDevice.findObject(new UiSelector()
                .text("确定")
                .className(Button.class));
        // 关闭对话框
        btnConfirm.click();
        // 通过id找到显示结果的TextView
        UiObject tvSound = uiDevice.findObject(new UiSelector().resourceId("android:id/summary").className(TextView.class));
        // 比较与预期结果一致
        assertEquals(tvSound.getText(), "Sceptrum");
        // 点击Home键
        uiDevice.pressHome();
        // 点击最近应用键
        uiDevice.pressRecentApps();
        // 通过类名找到显示最近app的控件TaskStackView
        // com.android.systemui:id/recents_view
        // UiScrollable taskStackView = new UiScrollable(new UiSelector().className("com.android.systemui.recents.views" +
        //        ".TaskStackView"));

        // 这块不同的手机可能对应的控件不一样 测试时具体问题具体分析
        UiScrollable taskStackView = new UiScrollable(new UiSelector()
                .resourceId("com.android.systemui:id/recents_view")
                .className(FrameLayout.class));
        //滑动到包含"UnitTestLearn"处
        taskStackView.scrollTextIntoView("UnitTestLearn");
        //通过显示的文本找到item
        UiObject UnitTestLearnApp = taskStackView.getChild(new UiSelector().text("UnitTestLearn"));
        //点击切换到前面的UnitTestLearnApp
        UnitTestLearnApp.click();
    }

}
