package designPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元模式
 * Created by Xu on 2017/4/3.
 */
public class FlyweightPattern {
    public static void main(String[] args) {
        List<Character> compositeState = new ArrayList<>();
        compositeState.add('a');
        compositeState.add('b');
        compositeState.add('c');
        compositeState.add('a');
        compositeState.add('b');

        FlyweightFactory flyFactory = new FlyweightFactory();
        Flyweight compositeFly1 = flyFactory.factory(compositeState);
        Flyweight compositeFly2 = flyFactory.factory(compositeState);
        compositeFly1.operation("Composite Call");

        System.out.println("---------------------------------");
        System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

        Character state = 'a';
        Flyweight fly1 = flyFactory.factory(state);
        Flyweight fly2 = flyFactory.factory(state);
        System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
    }
}

interface Flyweight {
    void operation(String state);
}

class ConcreteFlyweight implements Flyweight {

    private Character intrinsicState = null;

    public ConcreteFlyweight(Character state) {
        this.intrinsicState = state;
    }

    @Override
    public void operation(String state) {
        System.out.println("Intrinsic State = " + intrinsicState);
        System.out.println("Extrinsic State = " + state);
    }
}

class ConcreteCompositeFlyweight implements Flyweight {

    private Map<Character, Flyweight> files = new HashMap<>();

    public void add(Character key, Flyweight fly) {
        files.put(key, fly);
    }

    @Override
    public void operation(String state) {
        Flyweight fly = null;
        for (Character character : files.keySet()) {
            fly = files.get(character);
            fly.operation(state);
        }
    }
}

class FlyweightFactory {
    private Map<Character, Flyweight> files = new HashMap<>();

    public Flyweight factory(Character state) {
        return files.computeIfAbsent(state, ConcreteFlyweight::new);
    }

    public Flyweight factory(List<Character> compositeState) {
        ConcreteCompositeFlyweight compositeFlyweight = new ConcreteCompositeFlyweight();
        for (Character state : compositeState) {
            compositeFlyweight.add(state, factory(state));
        }
        return compositeFlyweight;
    }
}
