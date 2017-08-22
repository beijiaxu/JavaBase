package designPattern;

import java.util.HashMap;
import java.util.Map;

/**
 * 原型模式
 * Created by Xu on 2017/3/26.
 */
public class PrototyePattern {
    public static void main(String[] args) {
        /*
        简单原型模式
         */
        Prototype1 prototype = new ConcretePrototype1();
        Client1 client = new Client1(prototype);
        client.operation(prototype);
        client.operation(prototype);

        /*
        登记形式的原型模式
         */
        try{
            Prototype2 p1 = new ConcretePrototype3();
            PrototypeManager.setPrototype("p1", p1);
            //获取原型来创建对象
            Prototype2 p3 = PrototypeManager.getPrototype("p1").clone();
            p3.setName("张三");
            System.out.println("第一个实例：" + p3);
            //有人动态的切换了实现
            Prototype2 p2 = new ConcretePrototype4();
            PrototypeManager.setPrototype("p1", p2);
            //重新获取原型来创建对象
            Prototype2 p4 = PrototypeManager.getPrototype("p1").clone();
            p4.setName("李四");
            System.out.println("第二个实例：" + p4);
            //有人注销了这个原型
            PrototypeManager.removePrototype("p1");
            //再次获取原型来创建对象
            Prototype2 p5 = PrototypeManager.getPrototype("p1").clone();
            p5.setName("王五");
            System.out.println("第三个实例：" + p5);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


interface Prototype1 {
    Prototype1 clone();
}

class ConcretePrototype1 implements Prototype1 {

    @Override
    public Prototype1 clone() {
        Prototype1 prototype = new ConcretePrototype1();
        return prototype;
    }
}

class ConcretePrototype2 implements Prototype1 {
    public Prototype1 clone(){
        //最简单的克隆，新建一个自身对象，由于没有属性就不再复制值了
        Prototype1 prototype = new ConcretePrototype2();
        return prototype;
    }
}

class Client1 {
    private Prototype1 prototype;

    public Client1(Prototype1 prototype) {
        this.prototype = prototype;
    }
    public void operation(Prototype1 example) {
        Prototype1 copyPrototype = prototype.clone();
        System.out.println(copyPrototype);
    }
}

abstract class Prototype2 {
    public String name;
    public abstract Prototype2 clone();
    public abstract String getName();
    public abstract void setName(String name);
}

class ConcretePrototype3 extends Prototype2 {

    @Override
    public Prototype2 clone() {
        ConcretePrototype3 prototype = new ConcretePrototype3();
        prototype.setName(name);
        return prototype;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Now in Prototype3, name = " + name;
    }
}

class ConcretePrototype4 extends Prototype2 {

    @Override
    public Prototype2 clone() {
        ConcretePrototype4 prototype = new ConcretePrototype4();
        prototype.setName(name);
        return prototype;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Now in Prototype4, name = " + name;
    }
}

class PrototypeManager {
    /**
     * 用来记录原型的编号和原型实例的对应关系
     */
    private static Map<String, Prototype2> map = new HashMap<>();
    private PrototypeManager(){}

    /**
     * 向原型管理器里面添加或是修改某个原型注册
     * @param prototypeId 原型编号
     * @param prototype    原型实例
     */
    public synchronized static void setPrototype(String prototypeId, Prototype2 prototype) {
        map.put(prototypeId, prototype);
    }

    /**
     * 从原型管理器里面删除某个原型注册
     * @param prototypeId 原型编号
     */
    public synchronized static void removePrototype(String prototypeId) {
        map.remove(prototypeId);
    }

    public synchronized static Prototype2 getPrototype(String prototypeId) throws Exception {
        Prototype2 prototype = map.get(prototypeId);
        if(prototype == null)
            throw new Exception("您希望能够获取的原型还没有注册或已被销毁");
        return prototype;
    }

}

