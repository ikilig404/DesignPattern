package com.ikilig.pattern.singleton.type8;

import java.util.Calendar;

public class SingletonTest08 {

    public static void main(String[] args) {
        Singleton instance = Singleton.INSTANCE;
        Singleton instance2 = Singleton.INSTANCE;
        System.out.println(instance == instance2);
        Calendar cal = Calendar.getInstance();
    }
}

/**
 * 借助JDK1.5中添加的枚举来实现单例模式，不仅能避免多线程同步问题，
 * 而且还能防止反序列化重新创建对象。这种方式是Effective Java作者
 * Josh Bloch提倡的方式。
 */
enum Singleton {
    INSTANCE;  // 一个实例，保证是单例
    public void sayOK() {
        System.out.println("ok~");
    }
}
