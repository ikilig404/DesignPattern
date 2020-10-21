package com.ikilig.pattern.adapter;

/**
 *  类适配器模式
 */

// 客户端代码
public class ClassAdapterTest {
    public static void main(String[] args) {
        System.out.println("类适配器模式测试：");
        Target target = new ClassAdapter();
        target.request();
    }
}

//目标接口，即要当前系统业务要实现的
interface Target
{
    public void request();
}

//适配者接口，即现有的、已实现的库组件
class Adaptee
{
    public void specificRequest()
    {
        System.out.println("适配者中的业务代码被调用！");
    }
}

//类适配器类，做目标接口和适配者接口之间的转换
class ClassAdapter extends Adaptee implements Target
{
    public void request()
    {
        specificRequest();
    }
}
