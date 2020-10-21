# 设计模式

## 七大原则

### 1. 单一职责原则(Single Responsibility Principle)

### 2. 接口隔离原则(Interface Segregation Principle)

### 3. 依赖倒转原则(Dependence Inversion Principle)

### 4. 里氏替换原则(Liskov Substitution Principle)

### 5. 开闭原则(Open Closed Principle)
* 对扩展开放（提供方），对修改关闭（使用方）：只需要新增类，在使用类的代码中不需要做修改。
### 6. 迪米特法则()

### 7. 合成复用原则()

## UML入门

### 分类

1. 用例图
2. 静态结构图：类图、对象图、包图、组件图、部署图
3. 动态行为图：交互图（时序图与协作图）、状态图、活动图

### UML类图

1. 用于描述系统中的类（对象）本身的组成和类（对象）之间的各种静态关系。
2. 类之间的关系：依赖、泛化（继承）、实现、关联、聚合、组合。
3. 依赖关系：只要是在类中用到了（可以是成员属性、方法的返回类型、方法的参数类型、方法中的局部变量）对方，那么他们之间就存在依赖关系。 
4. 泛化关系：泛化关系实际上就是继承关系，是依赖关系的特例。
5. 实现关系：实现关系实际上就是A类实现B类，是依赖关系的特例。  
6. 关联关系：关联关系实际上就是类与类之间的联系，是依赖关系的特例。  关联具有导航性（双向关系和单向关系）和多重性。  
7. 聚合关系：聚合关系表示的是整体与部分的关系，整体与部分可以分开。聚合关系是关联关系的的特例，所以具有导航性和多重性。  
8. 组合关系：也是整体与部分的关系，但是整体与部分不可分开。  

## 23种设计模式

设计模式的本质是提高软件的维护性、通用性和扩展性，并降低软件的复杂度。  

### 分类

1. 创建型模式：单例模式、抽象工厂模式、原型模式、建造者模式、工厂模式。
2. 结构型模式：适配器模式、桥接模式、装饰模式、组合模式、外观模式、享元模式、代理模式。
3. 行为型模式：模板方法模式、命令模式、访问者模式、迭代器模式、观察者模式、中介者模式、备忘录模式、解释器模式、状态模式、策略模式、责任链模式。

### 23种

#### 1. 单例模式   

* 概念：所谓类的单例设计模式，就是采取一定的方法保证在整个的软件系统中，对某个类只能存在一个对象实例，并且该类只提供一个取得其对象实例的方法（静态方法）。 
* 八种实现方式  
  *  饿汉式（静态常量）
  ```java
  /**
   * 步骤如下：
   * 1）构造器私有化（防止new）
   * 2）类的内部创建对象，用静态常量接收
   * 3）向外暴露一个静态的公共方法
   * 
   * 优点：写法比较简单，在类装载的时候就完成实例化，避免了线程同步问题。
   * 缺点：在类装载的时候就完成实例化，没有达到懒加载的效果。如果从始至终
   *      都没有使用过这个实例，则会造成内存浪费。
   * 类装载：在这个单例模式的情况下，大多是时候类装载发生的原因是调用getInstance
   *        方法。但是导致类装载的原因有很多种，因此不能确定有其他的方式
   *       （或其他的静态方法）导致类装载。
   * 结论：这种单例模式可用，可能会造成内存浪费。
  */
  public class SingletonTest01 {
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
  
      private final static Singleton instance = new Singleton();
  
      public static Singleton getInstance() {
          return instance;
      }
  }
  ```
  
  * 饿汉式（静态代码块）
  ```java
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
  ```
  
  * 懒汉式（线程不安全）
  ```java
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
  ```
  
  * 懒汉式（线程安全，同步方法）
  ```java
  public class SingletonTest04 {
      public static void main(String[] args) {
          Singleton instance1 = Singleton.getInstance();
          Singleton instance2 = Singleton.getInstance();
          System.out.println(instance1 == instance2);  // true
          System.out.println(instance1.hashCode());
          System.out.println(instance2.hashCode());
      }
  
  }
  
  /**
   * 优缺点说明：
   * 1）解决了线程不安全问题
   * 2）效率太低。每个线程在想获得类的实例的时候，执行getInstance方法都要进行同步。
   *    而其实这个方法只执行一次实例化代码就够了，后面的线程想获取该实例，直接return
   *    就行了。方法进行同步效率太低。
   * 结论：在实际开发中，不推荐使用这种方式。
   */
  class Singleton {
  
      private static  Singleton instance;
  
      private Singleton() {
  
      }
  
      // 加入synchronized,解决线程安全问题，
      public static synchronized Singleton getInstance() {
          if (instance == null) {
              instance = new Singleton();
          }
          return instance;
      }
  }
  ```
  
  * 懒汉式（线程不安全，同步代码块）
  ```java
  /**
   * 这样写不能达到线程安全的目的，实际开发不能使用。
  */
  class Singleton {
    
        private static  Singleton instance;
    
        private Singleton() {
    
        }
          
        public static Singleton getInstance() {
            if (instance == null) {
                synchronized(Singleton.class) {
                    instance = new Singleton();
                }       
            }
            return instance;
        }
    }
  ```
  
  * 双重检查
  ```java
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
  ```
  
  * 静态内部类
  ```java
  /**
   * 静态内部类
   * 优缺点说明：
   * 1）这种方式采用了类装载的机制来保证初始化实例时只有一个线程。
   * 2）静态内部类在Singleton类被装载时并不会立即实例化，而是在需要实例化时，调用getInstance方法，才会
   *    装载SingletonInstance类，从而完成Singleton的实例化。
   * 3）类的静态属性只会在第一次加载类的时候初始化，所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，
   *    别的线程无法进入。
   * 结论：推荐使用。
   */
  class Singleton {
  
      private static volatile Singleton instance;
  
      private Singleton() {
  
      }
  
      private static class SingletonInstance {
          private static final Singleton INSTANCE = new Singleton();
      }
  
      public static synchronized Singleton getInstance() {
          return SingletonInstance.INSTANCE;
      }
  }
  ```
  
  * 枚举方式
  ```java
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
  ```
* 单例模式的应用
  * 在JDK中的使用, 用了饿汉式（静态常量）的实现方式   
  ```java
  public class Runtime {
      private static Runtime currentRuntime = new Runtime();
  
      /**
       * Returns the runtime object associated with the current Java application.
       * Most of the methods of class <code>Runtime</code> are instance
       * methods and must be invoked with respect to the current runtime object.
       *
       * @return  the <code>Runtime</code> object associated with the current
       *          Java application.
       */
      public static Runtime getRuntime() {
          return currentRuntime;
      }  
      /** Don't let anyone else instantiate this class */
      private Runtime() {} 
      /* ... */
   }
  
  ```
* 单例模式使用的场景：需要频繁地进行创建和销毁的对象、创建对象时耗时过多或耗费资源过多（即重量级对象），但又经常用到的对象
工具类对象、频繁访问数据库或文件的对象（比如数据源、session工厂等）。  


### 2. 工厂模式 （依赖倒转原则、开闭原则的体现）
* 简单工厂模式（静态工厂模式）
  * 简单工厂模式是属于创建型模式，是工厂模式的一种。简单工厂模式是由一个工厂对象决定创建出哪一种产品类的实例。简单工厂模式是工厂模式家族中最简单实用的模式。
  * 简单工厂模式：定义了一个创建对象的类，由这个类来封装实例化对象的行为。
  * 在软件开发中，当我们会用到大量的创建某种、某类或者谋批对象时，就会用到工厂模式。
* 工厂方法模式：定义了一个创建对象的抽象方法，由子类决定要实例化的类。工厂方法模式将对象的实例化推迟到子类。
* 抽象工厂模式
  * 抽象工厂模式：定义了一个interface用于创建相关或有依赖关系的对象簇，而无需指明具体的类。
  * 抽象工厂模式可以将简单工厂模式和工厂方法模式进行整合。
  * 从设计层面看，抽象工厂模式就是对简单工厂模式的改进（或者称为进一步的抽象）。
  * 将工厂抽象成两层，AbsFactory（抽象工厂）和具体实现的工厂子类。程序员可以根据创建对象类型使用对应的工厂子类。这样将单个的简单工厂类变成了工厂簇，更利于代码的维护和扩展。
* 工厂模式在JDK中的使用： Calendar 类

### 3. 原型模式
* 原型模式使用原型实例指定创建对象的种类，并且通过拷贝原型对象创建新的对象。  
* Prototype模式提供了一个通过已存在对象进行新对象创建的接口（clone）， clone()实现和具体的语言相关，在C++中通过拷贝构造函数实现。
* 原型模式实际上就是从一个对象再创建另外一个可定制的对象，而且不需要知道任何创建的细节。在初始化的信息不发生变化的情况下，克隆是最好的办法，既隐藏了对象创建的细节，又大大提高了性能。因为如果不用clone，每次new都需要执行一次构造函数，如果构造函数的执行时间很长，那么多次的执行初始化操作就太低效了。
* 原型模式实现clone接口的时候必须使用**深拷贝**。
* 原型模式重点在从自身赋值自己创建新的类对象，隐藏创建的细节。
* 原型模式角色
  * 抽象原型（Prototype）角色：规定了具体原型对象必须实现的接口（如果要提供深拷贝，则必须具有实现clone的规定）
  * 具体原型（ConcretePrototype）：从抽象原型派生而来，是客户程序使用的对象，即被复制的对象，需要实现抽象原型角色所要求的接口。
  * 客户（Client）角色：使用原型对象的客户程序
* 优点
  * 原型模式对客户隐藏了具体的产品类
  * 运行时刻增加和删除产品： 原型模式允许只通过客户注册原型实例就可以将一个新的具体产品类并入系统。
  * 改变值以指定新对象： 高度动态的系统允许通过对象复合定义新的行为。如通过为一个对象变量指定值并且不定义新的类。通过实例化已有类并且将实例注册为客户对象的原型，就可以有效定义新类别的对象。客户可以将职责代理给原型，从而表现出新的行为。
  * 改变结构以指定新对象：许多应用由部件和子部件来创建对象。
  * 减少子类的构造，Prototype模式克隆一个原型而不是请求一个工厂方法去产生一个新的对象。
  * 用类动态配置应用 一些运行时刻环境允许动态将类装载到应用中。
  * 使用原型模式创建对象比直接new一个对象在性能上要好的多，因为Object类的clone方法是一个本地方法，直接操作内存中的二进制流，特别是复制大对象时，性能的差别非常明显。
  * 使用原型模式的另一个好处是简化对象的创建，使得创建对象很简单。
* 缺点
  * 原型模式的主要缺陷是每一个抽象原型Prototype的子类都必须实现clone操作，实现clone函数可能会很困难。当所考虑的类已经存在时就难以新增clone操作，当内部包括一些不支持拷贝或有循环引用的对象时，实现克隆可能也会很困难的。
* 原型模式使用场景
  * 当一个系统应该独立于它的产品创建、构成和表示时，要使用原型模式
  * 当要实例化的类是在运行时刻指定时，如通过动态装载
  * 为了避免创建一个与产品类层次平行的工厂类层次时
  * 当一个类的实例只能有几个不同状态组合中的一种时。建立相应数目的原型并克隆    原型可能比每次用合适的状态手工实例化原型类更方便一些。
* [浅拷贝与深拷贝](https://www.cnblogs.com/ysocean/p/8482979.html)
* 原型模式在Spring中的应用：创建bean时选择scope=\"prototype\"。

### 4. 建造者模式
* [参考博客1](https://zhuanlan.zhihu.com/p/58093669)
* [参考博客2](http://m.biancheng.net/view/1354.html)
* 建造者模式在StringBuilder中的使用，不太符合标准的建造者模式，但都是正常的，每个人对设计模式的理解都有偏差。

### 5. 适配器模式
* [参考博客](http://m.biancheng.net/view/1361.html)
* 适配器模式在Spring MVC中的HandlerAdapter的使用。

### 6. 桥接模式
* [参考博客](http://m.biancheng.net/view/1364.html)
* 桥接模式在JDBC中的使用。

### 7. 装饰者模式