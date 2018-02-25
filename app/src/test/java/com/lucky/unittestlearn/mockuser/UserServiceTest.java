package com.lucky.unittestlearn.mockuser;

import org.apache.tools.ant.taskdefs.condition.Or;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zfz on 2017/3/13.
 * http://sgq0085.iteye.com/blog/2031319
 * AssertJ的使用
 * http://sgq0085.iteye.com/blog/2030609
 * 强大的Mock测试框架
 * http://www.2cto.com/kf/201302/188003.html
 *
 * mock强调的是某个对象的某个方法是否被调用了
 *
 * 验证某个方法是否被调用以及是否传入正确的参数
 *
 * 一个关于Mockito很好的说明例子
 * 对于Mock和Spy的区别讲的比较详细
 * http://www.open-open.com/lib/view/open1462177583813.html
 *
 * 如果不指定的话，一个mock对象的所有非void方法都将返回默认值：int、long类型方法将返回0，boolean方法将返回false，对象方法将返回null等等；
 * 而void方法将什么都不做。
 * 然而很多时候，你希望达到这样的效果：
 * 除非指定（指定指的就是指定方法的返回结果），否则调用这个对象的默认实现，同时又能拥有验证方法调用的功能。这正好是spy对象所能实现的效果。
 *
 * spy与mock的唯一区别就是默认行为不一样：spy对象的方法默认调用真实的逻辑，mock对象的方法默认什么都不做（void方法），或直接返回默认值（非void方法）。
 *
 * http://www.jianshu.com/p/b00586534fc1
 *
 *
 */
public class UserServiceTest {

    private UserService userService;
    private UserDao mockUserDao;


    /**
     * 在此处进行初始化操作
     */
    @Before
    public void setUp() throws Exception {
        mockUserDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImpl();
        userService.setUserDao(mockUserDao);
    }

    /**
     * 经验总结：
     * 1.设定预定的规则
     * when(mock.method()).thenReturn(返回值);
     * 2.执行预定的操作
     * 3.验证mock对象的指定方法是否执行
     * 4.验证执行方法返回的对象是否和mock对象的返回数据一致
     *
     * Mock的两大用处：
     * 1.验证方法调用
     * 2.指定某个方法的返回值，或者是执行特定的动作
     * (关于第二点的理解：就是我们在执行某一个方法的时候 我们不需要在真实的情况下测试 而是假定一定能符合规则
     *  但是又想要一个结果出来 来验证整个流程上的有效性 没有bug
     *  具体的例子就是比如 我们要做一个登陆的模块 但是我们不一定非得真正的执行登陆操作 而是给个返回值即可
     * )
     */
    @Test
    public void findUserByLoginName() throws Exception {
        User rtnUser = new User("liming");
        //Stud 设置方法调用的预期返回
        // 通过when(mock.someMethod()).thenReturn(value)，
        // 来设定mock对象某个方法调用时的返回值。
        // 这里我们设定调用传入任意字符串调用findUserByLoginName方法，返回rtnUser。
        when(mockUserDao.findUserByUserName(anyString())).thenReturn(rtnUser);
        User user = userService.findUserByLoginName("liming");
        // Mock  验证方法调用
        // mock对象一旦创建，就会自动记录自己的交互行为。
        // 通过verify(mock).someMethod()方法，来验证方法是否被调用。
        // 这里我们验证了mockUserDao的findUserByLoginName方法被调用，
        // 并且通过times(1)，验证是否被调用一次。注意，如果times不传入，则默认是1。
        // 在此处是验证userDao里的findUserByUserName()方法是否被调用

        //此处的作用就是验证方法是否被调用
        verify(mockUserDao, times(1)).findUserByUserName(anyString());
        // assert 返回值是否和预期一样
        assertNotNull(user);
        assertEquals("判读用户名是否正确", user.getUserName(), "liming");
    }

    /**
     * Mock对象的行为验证
     * verify(mock,times(n)).method()
     */
    @Test
    public void verifyTest() {
        List<String> mock  = Mockito.mock(List.class);
        List<String> mock2 = Mockito.mock(List.class);
        when(mock.get(0)).thenReturn("hello");
        when(mock.get(1)).thenReturn("world");
        mock.get(0);
        mock.get(1);
        //mock.get(2) 如果不被注释掉 则我们会在验证100毫秒内被调用两次是抛出错误
        //mock.get(2);
        //验证指定方法被调用一次
        verify(mock).get(0);
        //验证指定方法没有被调用
        verify(mock, never()).get(3);
        //验证get方法在100毫秒内被调用两次
        verify(mock, timeout(100).times(2)).get(anyInt());

        //通过验证方法的执行顺序
        InOrder inOrder = inOrder(mock, mock2);
        inOrder.verify(mock).get(0);
        inOrder.verify(mock).get(1);
        inOrder.verify(mock2, never()).get(1);

        //查询多余的方法调用 mock所有调用的方法都已经被验证
        verifyNoMoreInteractions(mock);
        //查询没有交互的mock对象 ??
        verifyZeroInteractions(mock2);

        //创建ArgumentCaptor(参数捕获器) 用户捕获方法参数进行验证
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        //该方法被调用多次 只能捕捉到最后一次参数
        verify(mock, times(2)).get(argument.capture());
        assertEquals(argument.getValue(), Integer.valueOf(1));

    }


    //监视对象
    @Test
    public void spyTest1() {
        List spy = spy(new LinkedList());
        // IndexOutBoundsException (the list is yet empty)
        // when(spy.get(0)).thenReturn("foo");
        // You have to use  doReturn() for stubbing
        doReturn("foo").when(spy).get(0);

    }

    //官方Demo
    @Test
    public void spyTest2() {
        List list = new LinkedList();
        List spy = spy(list);
        //optionally, you can stub out some method
        when(spy.size()).thenReturn(100);
        //using the spy calls real methods
        spy.add("one");
        spy.add("two");
        //print "one" is the first element of a list
        System.out.println("first element" + spy.get(0));
        //size() method was stubbed - 100 is printed
        System.out.println("spy.size() " + spy.size());
        //optionally,you can verify
        verify(spy).add("one");
        verify(spy).add("two");
        //verify(spy).add("three");
    }

    /**
     * 总结：
     * <p>
     * 根据最开始的userService给出两个基于mockito测试方法来体现上述提到过的知识点
     * <p>
     * http://sgq0085.iteye.com/blog/2031319
     **/

    @Test
    public void save() {
        User user = new User("admin");

        when(mockUserDao.findUserByUserName(anyString())).thenReturn(user).thenReturn(null);
        try {
            //测试如果重名会抛出异常
            userService.saveUser(user);
            //如果没有抛出异常测试不通过
            //这里mockUserDao不会对真实数据产生影响
            //就是不会在userMap中put进user对象
            HashMap<String,User> users=mockUserDao.getAllUsers();
            //fail();
            Set<Map.Entry<String,User>> set=users.entrySet();
            for(Map.Entry<String,User> entry:set){
                String name=entry.getKey();
                User usr=entry.getValue();
                System.out.println("name "+name+" user "+user);
            }
        } catch (IllegalStateException exception) {
            System.out.println("ILLegalStateException 名称不能重复");
        }
        //verify(mockUserDao).findUserByUserName("admin");

        //userService.save(user);
        user.setPassword("123456");
        String userName = userService.saveUser(user);
        //断言返回结果
        assertNotNull(user);

        //verify(mockUserDao, times(2)).findUserByUserName(anyString());
        verify(mockUserDao, never()).findUserByUserName(anyString());
        // verify(mockUserDao).saveUser(any(User.class)) 默认指定执行的次数是一次 times(1)
        //verify(mockUserDao).saveUser(any(User.class));
        verify(mockUserDao,atLeast(1)).saveUser(any(User.class));

    }

    @Test
    public void save2() {
        User user=new User("admin");
        user.setPassword("123456");
        userService.saveUser(user);


        //通过ArgumentCaptor(参数捕获器) 对传入参数进行验证
        ArgumentCaptor<User> argument=ArgumentCaptor.forClass(User.class);
        verify(mockUserDao).saveUser(argument.capture());
        Assert.assertEquals("admin",argument.getValue().getUserName());

        //stub 调用save方法时抛出异常
        doThrow(new IllegalStateException("测试抛出异常")).when(mockUserDao).
                saveUser(any(User.class));
        try {
             userService.saveUser(user);
        }catch (IllegalStateException exception){
            exception.printStackTrace();
        }

    }

    @Test
    public void spySave(){
        userService=new UserServiceImpl();
        UserDao spyUserDao=spy(UserDao.class);
        userService.setUserDao(spyUserDao);
        User user=new User("liming");
        userService.saveUser(user);

        User result=spyUserDao.findUserByUserName("liming");
        System.out.println("result "+result);
        verify(spyUserDao).findUserByUserName("liming");

        when(spyUserDao.findUserByUserName(anyString())).thenReturn(user);
    }

    @Test
    public void mockSave(){
        userService=new UserServiceImpl();
        UserDao mockUserDao=mock(UserDao.class);
        userService.setUserDao(mockUserDao);
        User user=new User("liming");
        userService.saveUser(user);

        User result=mockUserDao.findUserByUserName("liming");
        System.out.println("result "+result);
        verify(mockUserDao).findUserByUserName("liming");

        when(mockUserDao.findUserByUserName(anyString())).thenReturn(user);
    }

}