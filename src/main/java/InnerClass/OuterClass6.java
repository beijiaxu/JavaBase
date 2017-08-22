package InnerClass;

/**
 * 静态内部类
 * Created by Xu on 2017/3/24.
 */
public class OuterClass6 {
    private String sex;
    public static String name = "chenssy";

    static class InnerClass1 {
        /* 在静态内部类中可以存在静态成员 */
        public static String name1 = "chenssy_static";

        /*
         *静态内部类只能访问外围类的静态成员变量和方法
         * 不能访问外围类的非静态成员变量和方法
         */
        public void display() {
            System.out.println("OutClass name:" + name);
        }
    }

    class InnerClass2 {
        /* 非静态内部类中不能存在静态成员 */
        public String name2 = "chenssy_inner";

        /* 非静态内部类中可以调用外围类的任何成员,不管是静态的还是非静态的 */
        public void display() {
            System.out.println("OuterClass name:" + name);
        }
    }

    public void display() {
        System.out.println(InnerClass1.name1);
        new InnerClass1().display();
        /* 非静态内部的创建需要依赖于外围类 */
        OuterClass6.InnerClass2 inner2 = new OuterClass6().new InnerClass2();
        /* 方位非静态内部类的成员需要使用非静态内部类的实例 */
        System.out.println(inner2.name2);
        inner2.display();
    }

    public static void main(String[] args) {
        OuterClass6 outer = new OuterClass6();
        outer.display();
    }
}
