package ProtectedAuthority;

import ProtectedAuthority.AnotherPackage.MyObject6;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test6 {
    public static void main(String[] args) {
        /*
        不同包中子类本身的受保护方法当然不可能对父类可见
         */
        MyObject6 myObject6 = new MyObject6();
//        myObject6.clone();  //compile error
    }
}
