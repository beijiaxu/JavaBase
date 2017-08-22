package InnerClass;

/**
 * Created by Xu on 2017/3/26.
 */
public class OuterClass9 {
    public static String str = "outer";

    public class InnerClass {
        private String str = "inner";
        public void out() {
            String str = "method";
            System.out.println(str);
            System.out.println(this.str);
            System.out.println(OuterClass9.str);
        }
    }

    public static void main(String[] args) {
        new OuterClass9().new InnerClass().out();
    }
}
