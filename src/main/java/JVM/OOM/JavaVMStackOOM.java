package JVM.OOM;

/**
 * 弱国虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常
 * ! ! ! ! ! ! ! ! ! ! ! ! ! !
 * ! ! ! 别在windows运行 ! ! !
 * ! ! ! ! ! ! ! ! ! ! ! ! !
 * VM Args: -Xss2M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(this::dontStop);
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
