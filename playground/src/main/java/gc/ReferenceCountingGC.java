package gc;

/**
 * 测试java是否使用引用计数来垃圾收集
 * 答案 否
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC referenceCountingGC1 = new ReferenceCountingGC();
        ReferenceCountingGC referenceCountingGC2 = new ReferenceCountingGC();
        referenceCountingGC1.instance = referenceCountingGC2;
        referenceCountingGC2.instance = referenceCountingGC1;

        referenceCountingGC1 = null;
        referenceCountingGC2 = null;

        System.gc();
    }
}
