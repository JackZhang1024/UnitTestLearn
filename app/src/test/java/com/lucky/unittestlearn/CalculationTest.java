package com.lucky.unittestlearn;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by zfz on 2017/3/13.
 * 进行简单的Junit测试
 * Junit4测试
 * 学习地址：
 * http://blog.csdn.net/zhangxin09/article/details/42418441
 */
public class CalculationTest {

    /**
     * 在类的所有方法之前进行执行
     * **/
    @BeforeClass
    public static void testBeforeClass(){
        System.out.println("Before class");
    }

    /**
     * 在类的所有方法执行完成之后执行
     * **/
    @AfterClass
    public static void testAfterClass(){
        System.out.println("After class");
    }

    /**
     * 在类中的每个方法执行前进行执行
     * **/
    @Before
    public void setUp() throws Exception {
        System.out.println("Before test");
    }

    /**
     * 在类中的每个方法执行后进行执行
     * **/
    @After
    public void tearDown() throws Exception {
        System.out.println("After test");
    }

    @Test
    public void multiply() throws Exception {
        Calculation calculator =new Calculation();
        Assert.assertEquals(50, calculator.multiply(5,10));
    }

    @Test
    public void plus() throws Exception{
        int a=1+2;
        Assert.assertEquals(3,a);
    }

}