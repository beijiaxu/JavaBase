package JVM.classLoading;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWROLD = "hello world";
}
