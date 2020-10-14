package com.ikilig.principle.interfaceSegregation;

public class InterfaceSegregation2 {
    public static void main(String[] args) {
        A2 a2 = new A2();
        a2.depend1(new B2());  // A2通过接口(Interface2)去依赖B2类
        a2.depend2(new B2());  // A2通过接口(Interface3)去依赖B2类
        a2.depend3(new B2());  // A2通过接口(Interface3)去依赖B2类

        C2 c2 = new C2();
        c2.depend1(new D2());  // C2通过接口(Interface2)去依赖D2类
        c2.depend4(new D2());  // C2通过接口(Interface4)去依赖D2类
        c2.depend5(new D2());  // C2通过接口(Interface4)去依赖D2类
    }
}

// 接口
interface Interface2 {
    void operation1();
}

interface Interface3 {
    void operation2();
    void operation3();
}

interface Interface4 {
    void operation4();
    void operation5();
}

class B2 implements Interface2, Interface3 {

    @Override
    public void operation1() {
        System.out.println("B2 实现了 operation1");
    }

    @Override
    public void operation2() {
        System.out.println("B2 实现了 operation2");
    }

    @Override
    public void operation3() {
        System.out.println("B2 实现了 operation3");
    }
}

class D2 implements Interface2, Interface4 {

    @Override
    public void operation1() {
        System.out.println("D2 实现了 operation1");
    }

    @Override
    public void operation4() {
        System.out.println("D2 实现了 operation4");
    }

    @Override
    public void operation5() {
        System.out.println("D2 实现了 operation5");
    }
}

// A2类通过接口Interface2, Interface3依赖（使用）B类，只用到1，2，3方法
class A2 {
    public void depend1(Interface2 i) {
        i.operation1();
    }

    public void depend2(Interface3 i) {
        i.operation2();
    }

    public void depend3(Interface3 i) {
        i.operation3();
    }
}

// C2类通过接口Interface2， Interface4依赖（使用）D类，只用到1，4，5方法
class C2 {
    public void depend1(Interface2 i) {
        i.operation1();
    }

    public void depend4(Interface4 i) {
        i.operation4();
    }

    public void depend5(Interface4 i) {
        i.operation5();
    }
}
