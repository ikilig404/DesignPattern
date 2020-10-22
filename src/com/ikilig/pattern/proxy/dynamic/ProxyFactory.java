package com.ikilig.pattern.proxy.dynamic;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    // 维护一个目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象生成一个代理对象

    /**
     * @return
     */
    public Object getProxyInstance() {

        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            System.out.println("JDK代理开始......");
            // 反射机制调用目标对象的方法
            Object invoke = method.invoke(target, args);
            System.out.println("JDK代理结束......");
            return invoke;
        });
    }
}
