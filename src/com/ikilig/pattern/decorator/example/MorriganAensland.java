package com.ikilig.pattern.decorator.example;

import javax.swing.*;
import java.awt.*;

/**
 * 在《恶魔战士》中，游戏角色“莫莉卡·安斯兰”的原身是一个可爱少女，但当她变身时，会变成头顶及背部延伸出蝙蝠状飞翼的女妖，
 * 当然她还可以变为穿着漂亮外衣的少女。这些都可用装饰模式来实现，在本实例中的“莫莉卡”原身有 setImage(String t) 方法决定其显示方式，
 * 而其 变身“蝙蝠状女妖”和“着装少女”可以用 setChanger() 方法来改变其外观，原身与变身后的效果用 display() 方法来显示
 */
public class MorriganAensland {

    public static void main(String[] args) {
        Morrigan m0 = new Original();
        m0.display();
        Morrigan m1 = new Succubus(m0);
        m1.display();
        Morrigan m2 = new Girl(m0);
        m2.display();
    }
}

// 抽象构件角色：莫莉卡
interface Morrigan {
    public void display();
}

// 具体构件角色：原身
class Original extends JFrame implements Morrigan {

    private static final long serialVersionUID = 1L;
    private String t = "Morrigan0.jpg";

    public Original() {
        super("《恶魔战士》中的莫莉卡·安斯兰");
    }

    public void setImage(String t) {
        this.t = t;
    }

    @Override
    public void display() {
        this.setLayout(new FlowLayout());
        JLabel ll = new JLabel(new ImageIcon("src/com/ikilig/pattern/decorator/example/img/" + t));
        this.add(ll);
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

// 抽象装饰角色：变形
class Changer implements Morrigan {

    Morrigan m;

    public Changer(Morrigan m) {
        this.m = m;
    }

    @Override
    public void display() {
        m.display();
    }
}

// 具体装饰角色：女妖
class Succubus extends Changer {

    public Succubus(Morrigan m) {
        super(m);
    }

    @Override
    public void display() {
        setChanger();
        super.display();
    }

    private void setChanger() {
        ((Original) super.m).setImage("Morrigan1.jpg");
    }
}

// 具体装饰角色：少女
class Girl extends Changer {

    public Girl(Morrigan m) {
        super(m);
    }

    @Override
    public void display() {
        setChanger();
        super.display();
    }

    private void setChanger() {
        ((Original) super.m).setImage("Morrigan2.jpg");
    }
}
