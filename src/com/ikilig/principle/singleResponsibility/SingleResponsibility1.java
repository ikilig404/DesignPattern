package com.ikilig.principle.singleResponsibility;

public class SingleResponsibility1 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");
    }
}

// 交通工具类
// 方案1没有遵循单一职责原则
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行......");
    }
}
