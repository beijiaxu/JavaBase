package designPattern;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录模式
 * Created by Xu on 2017/4/15.
 */
public class MementoPattern {

    public static void main(String[] args) {
        Originator o = new Originator();
        Caretaker c = new Caretaker();
        o.setState("On");
        c.saveMemento(o.createMemento());
        o.setState("Off");
        o.restoreMemento(c.retrieveMemento());
        System.out.println(o.getState());



        Originator o1 = new Originator();
        Caretaker c1 = new Caretaker();
        //改变负责人对象的状态
        o1.setState("On");
        //创建备忘录对象，并将发起人对象的状态存储起来
        c1.saveMemento1(o1.createMemento1());
        //修改发起人对象的状态
        o1.setState("Off");
        //恢复发起人对象的状态
        o1.restoreMemento1(c1.retrieveMemento1());



        //多重检查
        Originator2 o2 = new Originator2();
        Caretaker2 c2 = new Caretaker2(o2);
        //改变状态
        o2.setState("state 0");
        //建立一个检查点
        c2.createMemento();
        //改变状态
        o2.setState("state 1");
        //建立一个检查点
        c2.createMemento();
        //改变状态
        o2.setState("state 2");
        //建立一个检查点
        c2.createMemento();
        //改变状态
        o2.setState("state 3");
        //建立一个检查点
        c2.createMemento();
        //打印出所有检查点
        o2.printStates();
        System.out.println("-----------------恢复检查点-----------------");
        //恢复到第二个检查点
        c2.restoreMemento(2);
        //打印出所有检查点
        o2.printStates();



        Originator3 o3 = new Originator3();
        //修改状态
        o3.changeState("state 0");
        //创建备忘录
        MementoIF memento3 = o3.createMemento();
        //修改状态
        o3.changeState("state 1");
        //按照备忘录恢复对象的状态
        o3.restoreMemento(memento3);
    }

    //发起人角色类，发起人角色利用一个新创建的备忘录对象将自己的内部状态存储起来。
    private static class Originator {
        private String state;

        public Memento createMemento() {
            return new Memento(state);
        }

        public void restoreMemento(Memento memento) {
            this.state = memento.getState();
        }

        public void restoreMemento1(MementoIF memento) {
            this.setState(((Memento1)memento).getState());
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
            System.out.println("当前状态：" + this.state);
        }

        /**
         * 工厂方法，返还一个新的备忘录对象
         */
        public MementoIF createMemento1(){
            return new Memento1(state);
        }

        private class Memento1 implements MementoIF{

            private String state;
            /**
             * 构造方法
             */
            private Memento1(String state){
                this.state = state;
            }

            private String getState() {
                return state;
            }
            private void setState(String state) {
                this.state = state;
            }
        }
    }

    //备忘录角色类，备忘录对象将发起人对象传入的状态存储起来。
    private static class Memento {
        @Getter @Setter
        private String state;

        public Memento(String state) {
            this.state = state;
        }

    }

    //负责人角色类，负责人角色负责保存备忘录对象，但是从不修改（甚至不查看）备忘录对象的内容。
    private static class Caretaker {
        private Memento memento;

        public Memento retrieveMemento() {
            return this.memento;
        }

        public void saveMemento(Memento memento){
            this.memento = memento;
        }

        private MementoIF memento1;
        /**
         * 备忘录取值方法
         */
        public MementoIF retrieveMemento1(){
            return memento1;
        }
        /**
         * 备忘录赋值方法
         */
        public void saveMemento1(MementoIF memento){
            this.memento1 = memento;
        }
    }
}

//窄接口MementoIF，这是一个标识接口，因此它没有定义出任何的方法。
interface MementoIF {}


/**
 * 多重检查点
 */
class Originator2 {

    private List<String> states;
    //检查点指数
    private int index;
    /**
     * 构造函数
     */
    public Originator2(){
        states = new ArrayList<>();
        index = 0;
    }
    /**
     * 工厂方法，返还一个新的备忘录对象
     */
    public Memento2 createMemento(){
        return new Memento2(states , index);
    }
    /**
     * 将发起人恢复到备忘录对象记录的状态上
     */
    public void restoreMemento(Memento2 memento){
        states = memento.getStates();
        index = memento.getIndex();
    }
    /**
     * 状态的赋值方法
     */
    public void setState(String state){
        states.add(state);
        index++;
    }
    /**
     * 辅助方法，打印所有状态
     */
    public void printStates(){

        for(String state : states){
            System.out.println(state);
        }
    }
}

class Memento2 {

    private List<String> states;
    private int index;
    /**
     * 构造函数
     */
    public Memento2(List<String> states , int index){
        this.states = new ArrayList<>(states);
        this.index = index;
    }
    public List<String> getStates() {
        return states;
    }
    public int getIndex() {
        return index;
    }

}

class Caretaker2 {

    private Originator2 o;
    private List<Memento2> mementos = new ArrayList<>();
    private int current;
    /**
     * 构造函数
     */
    public Caretaker2(Originator2 o){
        this.o = o;
        current = 0;
    }
    /**
     * 创建一个新的检查点
     */
    public int createMemento(){
        Memento2 memento = o.createMemento();
        mementos.add(memento);
        return current++;
    }
    /**
     * 将发起人恢复到某个检查点
     */
    public void restoreMemento(int index){
        Memento2 memento = mementos.get(index);
        o.restoreMemento(memento);
    }
    /**
     * 将某个检查点删除
     */
    public void removeMemento(int index){
        mementos.remove(index);
    }
}

//发起人角色同时还兼任负责人角色，也就是说它自己负责保持自己的备忘录对象。
class Originator3 {

    public String state;
    /**
     * 改变状态
     */
    public void changeState(String state){
        this.state = state;
        System.out.println("状态改变为：" + state);
    }
    /**
     * 工厂方法，返还一个新的备忘录对象
     */
    public Memento3 createMemento(){
        return new Memento3(this);
    }
    /**
     * 将发起人恢复到备忘录对象所记录的状态上
     */
    public void restoreMemento(MementoIF memento){
        Memento3 m = (Memento3)memento;
        changeState(m.state);
    }

    private class Memento3 implements MementoIF{

        private String state;
        /**
         * 构造方法
         */
        private Memento3(Originator3 o){
            this.state = o.state;
        }
        private String getState() {
            return state;
        }

    }
}