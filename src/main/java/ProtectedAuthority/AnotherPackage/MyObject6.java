package ProtectedAuthority.AnotherPackage;

import ProtectedAuthority.Test6;

/**
 * Created by Xu on 2017/3/26.
 */
public class MyObject6 extends Test6 {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
