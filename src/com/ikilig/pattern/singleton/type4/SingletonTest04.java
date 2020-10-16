package com.ikilig.pattern.singleton.type4;

public class SingletonTest04 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);  // true
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }

}

/**
 * 优缺点说明：
 * 1）解决了线程不安全问题
 * 2）效率太低。每个线程在想获得类的实例的时候，执行getInstance方法都要进行同步。
 *    而其实这个方法只执行一次实例化代码就够了，后面的线程想获取该实例，直接return
 *    就行了。方法进行同步效率太低。
 * 结论：在实际开发中，不推荐使用这种方式。
 */
class Singleton {

    private static  Singleton instance;

    private Singleton() {

    }

    // 加入synchronized,解决线程安全问题，
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
