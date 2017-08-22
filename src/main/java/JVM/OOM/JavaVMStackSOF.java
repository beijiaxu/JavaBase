package JVM.OOM;

/**
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常
 * VM Args: -Xss128k
 */
public class JavaVMStackSOF {
    private int stackLength = 0;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args)  {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
