package com.ikilig.pattern.singleton.type7;

public class SingletonTest07 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);  // true
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }

}

/**
 * 静态内部类
 * 优缺点说明：
 * 1）这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
 * 2）静态内部类在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会
 *    装载SingletonInstance类，从而完成Singleton的实例化。
 * 3）类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，
 *    别的线程无法进入。
 * 结论：推荐使用。
 */
class Singleton {

    private static volatile Singleton instance;

    private Singleton() {

    }

    private static class SingletonInstance {
        private static final Singleton INSTANCE = new Singleton();
    }

    public static synchronized Singleton getInstance() {
        return SingletonInstance.INSTANCE;
    }
}
