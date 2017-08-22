package designPattern;

/**
 * 单例模式
 * Created by Xu on 2017/3/23.
 */
public class SinglePattern {
    public static SinglePattern getInstance() {
        return SingleHolder.instance;
    }
    private SinglePattern(){
        System.out.println("实例化...");
    }
    private static class SingleHolder {
         private static SinglePattern instance = new SinglePattern();
    }

    public static void main(String[] args) {
        SinglePattern.getInstance();
        SinglePattern.getInstance();
    }
}
