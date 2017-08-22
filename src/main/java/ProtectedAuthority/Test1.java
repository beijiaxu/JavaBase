package ProtectedAuthority;

/**
 * protected 修饰的类和属性,对于自己、本包和其子类可见
 * 对于protected的成员或方法，要分子类和超类是否在同一个包中。
 * 与基类不在同一个包中的子类，只能访问自身从基类继承而来的受保护成员，
 * 而不能访问基类实例本身的受保护成员。在相同包时,protected和public是一样的
 * Created by Xu on 2017/3/26.
 */
public class Test1 {
    public static void main(String[] args) {
        /*
        虽然MyObject与Test属于同一个包，但受保护的clone方法来自Java.lang.Object类型，
        而在Test中，其基类Object的受保护方法是不可见的。
         */
        MyObject myObject = new MyObject();
//        myObject.clone();   //compile error
    }
}
class MyObject{}
