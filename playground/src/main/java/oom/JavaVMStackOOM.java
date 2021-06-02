package oom;

/**
 * VM Args:-Xss2M (这时候不妨设大些，请在32位系统下运行) 栈内存
 *
 * 操作系统分配给每个进程的内存是有限制的，譬如32位Windows的单个进程 最大内存限制为2GB。
 * HotSpot虚拟机提供了参数可以控制Java堆和方法区这两部分的内存的最大值，
 * 那剩余的内存即为2GB(操作系统限制)减去最大堆容量，再减去最大方法区容量，
 * 由于程序计数器 消耗内存很小，可以忽略掉，如果把直接内存和虚拟机进程本身耗费的内存也去掉的话，
 * 剩下的内存 就由虚拟机栈和本地方法栈来分配了。因此为每个线程分配到的栈内存越大，
 * 可以建立的线程数量自然就越少，建立线程时就越容易把剩下的内存耗尽，
 */
public class JavaVMStackOOM {

    private void dontStop() {
        while(true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(()->{
                dontStop();
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
