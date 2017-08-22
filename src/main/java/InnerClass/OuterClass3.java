package InnerClass;


/**
 * 方法局部内部类
 * Created by Xu on 2017/3/24.
 */
public class OuterClass3 {
    public Destination destination(String str) {
        class PDestination implements Destination {
            private String label;

            public PDestination(String whereTo) {
                this.label = whereTo;
            }
        }
        return new PDestination(str);
    }

    public static void main(String[] args) {
        OuterClass3 outer = new OuterClass3();
        Destination d = outer.destination("chenssy");
    }
}

interface Destination {

}
