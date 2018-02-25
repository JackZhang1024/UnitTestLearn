package com.lucky.unittestlearn;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by zfz on 2017/3/13.
 * TestSuite测试
 * 将多个测试单元放到一块进行处理
 * 如下将StringUtilTest和MyClassTest放到一块进行了处理
 * 最后只需要执行JSuiteTest即可
 *
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({StringUtilTest.class,CalculationTest.class})
public class JSuiteTest {

}
