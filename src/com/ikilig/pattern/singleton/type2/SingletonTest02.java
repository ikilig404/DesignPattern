package com.ikilig.pattern.singleton.type2;

public class SingletonTest02 {
    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);  // true
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }

}

class Singleton {

    private Singleton() {

    }

    private static final Singleton instance;

    static {
        instance  = new Singleton();
    }

    public static Singleton getInstance() {
        return instance;
    }
}
