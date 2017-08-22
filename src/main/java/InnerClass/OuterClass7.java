package InnerClass;

import lombok.Data;

/**
 * 匿名内部类
 * Created by Xu on 2017/3/24.
 */
@Data
abstract class Bird {
    private String name;
    public abstract int fly();
}
public class OuterClass7 {
    public void test(Bird bird) {
        System.out.println(bird.getName() + "能够飞" + bird.fly() + "米");
    }

    public static void main(String[] args) {
        OuterClass7 outer = new OuterClass7();
        outer.test(new Bird() {
            @Override
            public int fly() {
                return 10000;
            }

            @Override
            public String getName() {
                return "大雁";
            }
        });
    }
}
