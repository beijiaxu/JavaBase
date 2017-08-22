package ProtectedAuthority;

/**
 * Created by Xu on 2017/3/26.
 */
public class Test2 {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        MyObject与Test在同一个包中，受保护的clone方法来自MyObject本身，所以它对Test而言是可见的.
        （在相同包时,protected和public是一样的）另外在这个示例中，还说明了super关键字对于基类受保护成员的调用是个语言设计之中的“例外”。
         */
        MyObject2 myObject2 = new MyObject2();
        myObject2.clone();
    }
}

class MyObject2 {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
