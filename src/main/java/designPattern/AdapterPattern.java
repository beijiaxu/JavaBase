package designPattern;

/**
 * 适配器模式
 * Created by Xu on 2017/3/26.
 */
public class AdapterPattern {
}

/**
 * 类适配器模式
 */
interface Adapter {
    /**
     * 这是源类Adaptee也有的方法
     */
    void sampleOperation1();
    /**
     * 这是源类Adapteee没有的方法
     */
    void sampleOperation2();
}

class Adaptee {
    public void sampleOperation1(){}
}

class ClassAdapter extends Adaptee implements Adapter {

    @Override
    public void sampleOperation2() {

    }
}

/**
 * 对象适配器模式
 */
class ObjectAdapter {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    /**
     * 源类Adaptee有方法sampleOperation1
     * 因此适配器类直接委派即可
     */
    public void sampleOperation1() {
        this.adaptee.sampleOperation1();
    }

    public void sampleOperation2() {
        //some code
    }

}