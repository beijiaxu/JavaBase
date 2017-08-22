package ProtectedAuthority;

import ProtectedAuthority.AnotherPackage.MyObject3;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test3 extends MyObject3{
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        企图跨越不同的包,从子类中调用基类实例的受保护方法。明显不行。
         */
        MyObject3 myObject3 = new MyObject3();
//        myObject3.clone();  //compile error
        Test3 test3 = new Test3();
        test3.clone();  //compile OK
    }
}
