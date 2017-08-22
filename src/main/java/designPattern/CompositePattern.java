package designPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 合成模式
 * Created by Xu on 2017/3/26.
 */
public class CompositePattern {
    public static void main(String[] args) {
        Composite1 root = new Composite1("服装");
        Composite1 c1 = new Composite1("男装");
        Composite1 c2 = new Composite1("女装");

        Leaf1 leaf1 = new Leaf1("衬衫");
        Composite1 c3 = new Composite1("夹克");
        Leaf1 leaf3 = new Leaf1("裙子");
        Leaf1 leaf4 = new Leaf1("套装");
        Leaf1 leaf5 = new Leaf1("黑夹克");
        Leaf1 leaf6 = new Leaf1("褐夹克");

        root.addChild(c1);
        root.addChild(c2);
        c1.addChild(leaf1);
        c1.addChild(c3);
        c2.addChild(leaf3);
        c2.addChild(leaf4);
        c3.addChild(leaf5);
        c3.addChild(leaf6);

        root.printStruct("");





        Component2 root2 = new Composite2("服装");
        Component2 c21 = new Composite2("男装");
        Component2 c22 = new Composite2("女装");

        Component2 leaf21 = new Leaf2("衬衫");
        Component2 leaf22 = new Leaf2("夹克");
        Component2 leaf23 = new Leaf2("裙子");
        Component2 leaf24 = new Leaf2("套装");

        root2.addChild(c21);
        root2.addChild(c22);
        c21.addChild(leaf21);
        c21.addChild(leaf22);
        c22.addChild(leaf23);
        c22.addChild(leaf24);

        root.printStruct("");
    }
}

/**
 * 安全式合成模式
 */
interface Component1 {
    /**
     * 输出组建自身的名称
     */
    void printStruct(String preStr);
}

class Composite1 implements Component1 {
    /**
     * 用来存储组合对象中包含的子组件对象
     */
    private List<Component1> childComponents = new ArrayList<>();
    /**
     * 组合对象的名字
     */
    private String name;

    public Composite1(String name) {
        this.name = name;
    }

    public void addChild(Component1 child) {
        childComponents.add(child);
    }

    public void removeChild(int index) {
        childComponents.remove(index);
    }

    public List<Component1> getChild() {
        return childComponents;
    }

    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr + "" + this.name);
        if(!childComponents.isEmpty()) {
            preStr += "  ";
            for (Component1 component : childComponents) {
                component.printStruct(preStr);
            }
        }
    }
}

class Leaf1 implements Component1 {

    private String name;

    public Leaf1(String name) {
        this.name = name;
    }

    /**
     * 输出叶子对象的结构，叶子对象没有子对象，也就是输出叶子对象的名字
     * @param preStr 前缀，主要是按照层级拼接的空格，实现向后缩进
     */
    @Override
    public void printStruct(String preStr) {
        System.out.println(preStr + "-" + name);
    }
}

/**
 * 透明式合成模式
 */
abstract class Component2 {
    abstract void printStruct(String preStr);
    /**
     * 聚集管理方法，增加一个子构件对象
     * @param child 子构件对象
     */
    public void addChild(Component2 child){
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException("对象不支持此功能");
    }
    /**
     * 聚集管理方法，删除一个子构件对象
     * @param index 子构件对象的下标
     */
    public void removeChild(int index){
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException("对象不支持此功能");
    }

    /**
     * 聚集管理方法，返回所有子构件对象
     */
    public List<Component2> getChild(){
        /**
         * 缺省实现，抛出异常，因为叶子对象没有此功能
         * 或者子组件没有实现这个功能
         */
        throw new UnsupportedOperationException("对象不支持此功能");
    }
}

class Composite2 extends Component2 {

    private List<Component2> childComponents = new ArrayList<>();
    private String name;

    public Composite2(String name) {
        this.name = name;
    }

    public void addChild(Component2 child){
        childComponents.add(child);
    }
    /**
     * 聚集管理方法，删除一个子构件对象
     * @param index 子构件对象的下标
     */
    public void removeChild(int index){
        childComponents.remove(index);
    }
    /**
     * 聚集管理方法，返回所有子构件对象
     */
    public List<Component2> getChild(){
        return childComponents;
    }
    /**
     * 输出对象的自身结构
     * @param preStr 前缀，主要是按照层级拼接空格，实现向后缩进
     */
    @Override
    public void printStruct(String preStr) {
        // 先把自己输出
        System.out.println(preStr + "+" + this.name);
        //如果还包含有子组件，那么就输出这些子组件对象
        if(this.childComponents != null){
            //添加两个空格，表示向后缩进两个空格
            preStr += "  ";
            //输出当前对象的子对象
            for(Component2 c : childComponents){
                //递归输出每个子对象
                c.printStruct(preStr);
            }
        }

    }
}

class Leaf2 extends Component2 {
    /**
     * 叶子对象的名字
     */
    private String name;
    /**
     * 构造方法，传入叶子对象的名称
     * @param name 叶子对象的名字
     */
    public Leaf2(String name){
        this.name = name;
    }
    /**
     * 输出叶子对象的结构，叶子对象没有子对象，也就是输出叶子对象的名字
     * @param preStr 前缀，主要是按照层级拼接的空格，实现向后缩进
     */
    @Override
    public void printStruct(String preStr) {
        // TODO Auto-generated method stub
        System.out.println(preStr + "-" + name);
    }

}
