package com.ikilig.principle.inversion.improve;

public class DependenceInversion {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());

        person.receive(new WeiXin());
    }
}

interface IReceiver {
    public String getInfo();
}

class Email implements IReceiver {
    public String getInfo() {
        return "电子邮件信息：Hello World!";
    }
}

// 增加微信
class WeiXin implements IReceiver {

    @Override
    public String getInfo() {
        return "微信信息：Hello World!";
    }
}

// 方式2
class Person {
    public void receive(IReceiver iReceiver) {
        System.out.println(iReceiver.getInfo());
    }
}
