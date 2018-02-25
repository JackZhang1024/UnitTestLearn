package com.lucky.unittestlearn;

import android.text.TextUtils;

/**
 * Created by zfz on 2017/3/13.
 */

public class StringUtil {

    public String convertToUpperCase(String raw){
        if("".equals(raw) || raw==null) return "Input can't be empty!";
        return raw.toUpperCase();
    }
}
