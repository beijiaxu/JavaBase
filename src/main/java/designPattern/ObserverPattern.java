package designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * Created by Xu on 2017/4/4.
 */
public class ObserverPattern {
    public static void main(String[] args) {
        //创建主题对象
        ConcreteSubject subject = new ConcreteSubject();
        //创建观察者对象
        Observer observer = new ConcreteObserver();
        Observer observer12 = new ConcreteObserver();
        //将观察者对象登记到主题对象上
        subject.attach(observer);
        subject.attach(observer12);
        //改变主题对象的状态
        subject.change("new state");


        //创建主题对象
        ConcreteSubject2 subject2 = new ConcreteSubject2();
        //创建观察者对象
        Observer2 observer2 = new ConcreteObserver2();
        Observer2 observer22 = new ConcreteObserver2();
        //将观察者对象登记到主题对象上
        subject2.attach(observer2);
        subject2.attach(observer22);
        //改变主题对象的状态
    }
}

interface Observer {
    /**
     * 更新接口
     * @param state    更新的状态
     */
    void update(String state);
}

class ConcreteObserver implements Observer {
    //观察者的状态
    private String observerState;

    @Override
    public void update(String state) {
        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        observerState = state;
        System.out.println("状态为："+observerState);
    }

}

abstract class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    private List<Observer> list = new ArrayList<>();
    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(Observer observer){

        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer observer){

        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(String newState){

        for(Observer observer : list){
            observer.update(newState);
        }
    }
}

class ConcreteSubject extends Subject{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers(state);
    }
}


/**
 *  拉模式
 */
interface Observer2 {
    /**
     * 更新接口
     * @param subject 传入主题对象，方面获取相应的主题对象的状态
     */
    public void update(Subject2 subject);
}

class ConcreteObserver2 implements Observer2 {
    //观察者的状态
    private String observerState;

    @Override
    public void update(Subject2 subject) {
        /**
         * 更新观察者的状态，使其与目标的状态保持一致
         */
        observerState = ((ConcreteSubject2)subject).getState();
        System.out.println("观察者状态为："+observerState);
    }

}

abstract class Subject2 {
    /**
     * 用来保存注册的观察者对象
     */
    private    List<Observer2> list = new ArrayList<>();
    /**
     * 注册观察者对象
     * @param observer    观察者对象
     */
    public void attach(Observer2 observer){

        list.add(observer);
        System.out.println("Attached an observer");
    }
    /**
     * 删除观察者对象
     * @param observer    观察者对象
     */
    public void detach(Observer2 observer){

        list.remove(observer);
    }
    /**
     * 通知所有注册的观察者对象
     */
    public void nodifyObservers(){

        for(Observer2 observer : list){
            observer.update(this);
        }
    }
}

class ConcreteSubject2 extends Subject2{

    private String state;

    public String getState() {
        return state;
    }

    public void change(String newState){
        state = newState;
        System.out.println("主题状态为：" + state);
        //状态发生改变，通知各个观察者
        this.nodifyObservers();
    }
}