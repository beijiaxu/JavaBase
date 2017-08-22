package designPattern;

/**
 * 迭代子模式
 * Created by Xu on 2017/4/9.
 */
public class IteratorPattern {
    public void operation(){
        Object[] objArray = {"One","Two","Three","Four","Five","Six"};
        //创建聚合对象
        Aggregate1 agg1 = new ConcreteAggregate1(objArray);
        Aggregate2 agg2 = new ConcreteAggregate2(objArray);
        //循环输出聚合对象中的值
        Iterator1 it1 = agg1.createIterator();
        Iterator2 it2 = agg2.createIterator();
        while(!it1.isDone()){
            System.out.println("it1:" + it1.currentItem());
            it1.next();
            System.out.println("it2:" + it2.currentItem());
            it2.next();
        }
    }
    public static void main(String[] args) {

        IteratorPattern client = new IteratorPattern();
        client.operation();
    }

}

/**
 * 白箱迭代子
 */
abstract class Aggregate1 {
    /**
     * 工厂方法，创建相应迭代子对象的接口
     */
    public abstract Iterator1 createIterator();
}

class ConcreteAggregate1 extends Aggregate1 {

    private Object[] objArray = null;
    /**
     * 构造方法，传入聚合对象的具体内容
     */
    public ConcreteAggregate1(Object[] objArray){
        this.objArray = objArray;
    }

    @Override
    public Iterator1 createIterator() {

        return new ConcreteIterator1(this);
    }
    /**
     * 取值方法：向外界提供聚集元素
     */
    public Object getElement(int index){

        if(index < objArray.length){
            return objArray[index];
        }else{
            return null;
        }
    }
    /**
     * 取值方法：向外界提供聚集的大小
     */
    public int size(){
        return objArray.length;
    }
}

interface Iterator1 {
    /**
     * 迭代方法：移动到第一个元素
     */
    public void first();
    /**
     * 迭代方法：移动到下一个元素
     */
    public void next();
    /**
     * 迭代方法：是否为最后一个元素
     */
    public boolean isDone();
    /**
     * 迭代方法：返还当前元素
     */
    public Object currentItem();
}

class ConcreteIterator1 implements Iterator1 {
    //持有被迭代的具体的聚合对象
    private ConcreteAggregate1 agg;
    //内部索引，记录当前迭代到的索引位置
    private int index = 0;
    //记录当前聚集对象的大小
    private int size = 0;

    public ConcreteIterator1(ConcreteAggregate1 agg){
        this.agg = agg;
        this.size = agg.size();
        index = 0;
    }
    /**
     * 迭代方法：返还当前元素
     */
    @Override
    public Object currentItem() {
        return agg.getElement(index);
    }
    /**
     * 迭代方法：移动到第一个元素
     */
    @Override
    public void first() {

        index = 0;
    }
    /**
     * 迭代方法：是否为最后一个元素
     */
    @Override
    public boolean isDone() {
        return (index >= size);
    }
    /**
     * 迭代方法：移动到下一个元素
     */
    @Override
    public void next() {

        if(index < size)
        {
            index ++;
        }
    }

}

/**
 * 黑箱迭代子
 */

abstract class Aggregate2 {
    public abstract Iterator2 createIterator();
}

interface Iterator2 {
    void first();
    void next();
    boolean isDone();
    Object currentItem();
}

class ConcreteAggregate2 extends Aggregate2 {

    private Object[] objArray = null;

    public ConcreteAggregate2(Object[] objArray) {
        this.objArray = objArray;
    }

    @Override
    public Iterator2 createIterator() {
        return new ConcreteIterator();
    }

    private class ConcreteIterator implements Iterator2 {

        private int index = 0;
        private int size = 0;

        public ConcreteIterator() {
            this.index = 0;
            this.size = objArray.length;
        }

        @Override
        public void first() {
            index = 0;
        }

        @Override
        public void next() {
            if(index < size)
                index++;
        }

        @Override
        public boolean isDone() {
            return index == objArray.length - 1;
        }

        @Override
        public Object currentItem() {
            return objArray[index];
        }
    }
}
