package com.ikilig.pattern.singleton.type3;

public class SingletonTest03 {

    public static void main(String[] args) {
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();
        System.out.println(instance1 == instance2);  // true
        System.out.println(instance1.hashCode());
        System.out.println(instance2.hashCode());
    }
}

/**
 * 优缺点说明:
 * 1）起到了懒加载的效果，但是只能在单线程下使用
 * 2）如果在多线程下，一个线程进入了if (instance == null)判断语句块，还没来得及
 *    往下执行，另外一个线程也通过了这个判断语句，这时便会产生多个实例。所以在多线程
 *    环境下不可使用这种方式。
 * 结论：在实际开发中，不要使用这种方式。
 */
class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    // 提供一个静态的公有方法，当使用到这个方法时，才去创建实例
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
