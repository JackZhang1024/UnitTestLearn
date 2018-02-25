package com.lucky.unittestlearn.instrumentunittest;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author zfz
 *         Created by zfz on 2018/2/24.
 */

public class Config {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Config instance;

    private Config(Context context) {
        sharedPreferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static Config getInstance(Context context) {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config(context);
                }
            }
        }
        return instance;
    }

    /**
     * 模拟测试方法 通过SharePreferences保存UserId
     *
     * @param userId 用户ID
     */
    public static void setUserId(String userId) {
        editor.putString("userId", userId);
        editor.commit();
    }

    /**
     * 模拟测试方法 通过SharePreferences获取UserId
     *@return 返回用户Id
     */
    public static String getUserId() {
        return sharedPreferences.getString("userId", "");
    }

}
