package ProtectedAuthority;

import ProtectedAuthority.AnotherPackage.MyObject5;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test5 {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        虽然处于不同的包，但子类的受保护方法实际上继承自父类，
        父类的自己的受保护方法对自己可见，其原理同示例4
         */
        MyObject5 myObject5 = new MyObject5();
        myObject5.clone();
    }
}
