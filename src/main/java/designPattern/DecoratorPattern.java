package designPattern;

/**
 * 装饰模式
 * Created by Xu on 2017/3/26.
 */
public class DecoratorPattern {
}
interface Component {
    void sampleOperation();
}

class ConcreteComponent implements Component {

    @Override
    public void sampleOperation() {
        //some code
    }
}

class Decorator implements Component {

    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void sampleOperation() {
        component.sampleOperation();
    }
}


class ConcreteDecoratorA extends Decorator {

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //some code
    }
}

class ConcreteDecoratorB extends Decorator {

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    @Override
    public void sampleOperation() {
        super.sampleOperation();
        //some code
    }
}