package com.ikilig.principle.inversion;

public class DependenceInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());
    }
}

class Email {
    public String getInfo() {
        return "电子邮件信息：Hello World!";
    }
}

// 完成Person接收消息的功能
// 方式1
// 如果获取信息的对象不是邮件，而是微信、短信等，则要新增类，同时还要增加方法
// 改进：引入一个抽象的接口IReceiver, 表示接收者，Person与接口IReceiver发生依赖
class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}
