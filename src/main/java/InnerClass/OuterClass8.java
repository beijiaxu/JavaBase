package InnerClass;

/**
 * Created by Xu on 2017/3/24.
 */
public class OuterClass8 {

    abstract class InnerClass8{
        abstract int getAge();
        abstract String getName();
    }

    public InnerClass8 getInnerClass(final int age, final String name) {
        return new InnerClass8() {
            int age_;
            String name_;
            /*
             *构造代码块
             */
            {
                if(0 < age && age < 200) {
                    age_ = age;
                    name_ = name;
                }
            }

            public int getAge() {
                return age_;
            }

            public String getName() {
                return name_;
            }
        };
    }

    public static void main(String[] args) {
        OuterClass8 outer = new OuterClass8();
        InnerClass8 inner1 = outer.getInnerClass(201, "chenssy1");
        System.out.println(inner1.getName());
        InnerClass8 inner2 = outer.getInnerClass(23, "chenssy2");
        System.out.println(inner2.getName());
    }
}
