package com.lucky.unittestlearn;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.lucky.unittestlearn.instrumentunittest.Config;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * InstrumentUnit测试
 * 不涉及UI操作
 * @author zfz
 * Created by zfz on 2018/2/24.
 */

@RunWith(AndroidJUnit4.class)
public class ConfigTest {

    private static String userId = null;

    @BeforeClass
    public static void setUp(){
        userId = "10086";
    }

    @Test
    public void setUserId() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        Config.getInstance(context).setUserId(userId);
    }

    @Test
    public void getUserId() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        String id = Config.getInstance(context).getUserId();
        assertEquals(userId, id);
    }



}