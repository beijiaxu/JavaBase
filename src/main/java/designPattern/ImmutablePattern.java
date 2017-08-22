package designPattern;

/**
 * 不变模式
 * 强不变：java.lang.String
 * 弱不变：子类状态可变
 * Created by Xu on 2017/4/4.
 */
public class ImmutablePattern {
    public static void main(String[] args) {
        new Aoo().out();
    }
}

final class Aoo {
    private final String a = "aoo";
    final void out() {
        System.out.println(a);
    }
}
