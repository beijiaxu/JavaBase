package designPattern;

/**
 * 代理模式
 * Created by Xu on 2017/3/31.
 */
public class ProxyPattern {
    public static void main(String[] args) {
        AbstractObject object = new ProxyObject();
        object.operation();
    }
}

abstract class AbstractObject {
    abstract void operation();
}
class RealObject extends AbstractObject {

    @Override
    void operation() {
        System.out.println("一些操作");
    }
}

class ProxyObject extends AbstractObject {
    RealObject realObject = new RealObject();

    @Override
    void operation() {
        System.out.println("before");
        realObject.operation();
        System.out.println("after");
    }
}