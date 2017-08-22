package ProtectedAuthority;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        因为MyObject的clone方法继承自Test，而Test做为相对于Object的子类，
        是可以访问继承而来的属于它自己的受保护方法的。
         */
        MyObject4 myObject4 = new MyObject4();
        myObject4.clone();
    }
}
class MyObject4 extends Test4{}
