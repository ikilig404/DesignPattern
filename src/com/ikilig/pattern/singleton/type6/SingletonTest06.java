package com.ikilig.pattern.singleton.type6;

public class SingletonTest06 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);  // true
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }

}

class Singleton {

    private static volatile Singleton instance;

    private Singleton() {

    }

    // 加入双重检查的代码，解决线程安全问题，同时解决懒加载问题，还解决了效率低下的问题
    public static synchronized Singleton getInstance() {
        if (instance == null) {  // 这个判断确保在有了一个实例的情况下，后来的线程不会进入到同步代码块
            synchronized (Singleton.class) {
                if (instance == null)  // 这个判断保证了只有一个实例
                    instance = new Singleton();
            }
        }
        return instance;
    }
}
