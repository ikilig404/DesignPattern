package com.ikilig.pattern.proxy.dynamic;

// 真实主题类
public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师授课中......");
    }
}
