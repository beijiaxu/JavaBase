package JVM.classLoading;

/**
 * 被动引用类加载
 */
public class NotInitialization {
    public static void main(String[] args) {
//        System.out.println(SubClass.value);
//        SuperClass[] superClasses = new SuperClass[10];
        System.out.println(ConstClass.HELLOWROLD);
    }
}
