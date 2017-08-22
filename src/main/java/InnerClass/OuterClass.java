package InnerClass;

import lombok.Data;

/**
 * Created by Xu on 2017/3/24.
 */
@Data
public class OuterClass {
    private String name ;
    private int age;

    /**省略getter和setter方法**/

    public class InnerClass{
        public InnerClass(){
            name = "chenssy";
            age = 23;
        }

        public void display(){
            System.out.println("name：" + getName() +"   ;age：" + getAge());
        }
    }

    public static void main(String[] args) {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        innerClass.display();
    }
}
