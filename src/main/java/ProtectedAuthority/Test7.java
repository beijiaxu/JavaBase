package ProtectedAuthority;

import ProtectedAuthority.AnotherPackage.MyObject7;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test7 extends MyObject7{
    public static void main(String[] args) {
        /*
        同一个包中，父类实例的clone方法在子类中依然不可见，
        原理同示例1，就是因为父类的clone方法，实际上来自java.lang.Object。
         */
        MyObject7 myObject7 = new MyObject7();
//        myObject7.clone();  //compile error
    }
}
