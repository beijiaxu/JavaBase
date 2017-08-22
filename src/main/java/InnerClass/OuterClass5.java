package InnerClass;

/**
 * 匿名内部类
 * Created by Xu on 2017/3/24.
 */
public class OuterClass5 {
    public InnerClass getInnerClass(final int num, String str) {
        return new InnerClass() {
            int number = num + 3;
            @Override
            public int getNumber() {
                return number;
            }
        };
    }

    public InnerClass getInnerClass2(InnerClass innerClass) {
        //innerClass 处理
        return innerClass;
    }

    public static void main(String[] args) {
        OuterClass5 outer = new OuterClass5();
        InnerClass inner = outer.getInnerClass(2, "chenssy");
        System.out.println(inner.getNumber());

        OuterClass5 outer2 = new OuterClass5();
        InnerClass inner2 = outer2.getInnerClass2(() -> 0);
        System.out.println(inner2.getNumber());
    }
}

interface InnerClass{
    int getNumber();
}
