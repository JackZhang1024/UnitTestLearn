package com.lucky.unittestlearn;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zfz on 2017/3/13.
 */
public class StringUtilTest {

    @Test
    public void convertToUpperCase() throws Exception {
        StringUtil util=new StringUtil();
        String formattedDta=util.convertToUpperCase("hello");
        Assert.assertEquals("HELLO",formattedDta);
    }

}