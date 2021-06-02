package gc.act;

import java.util.ArrayList;
import java.util.HashMap;

public class TestAllocation {


    private static final int _1MB = 1024*1024;

    /**
     * VM args:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -Xmn 新生代大小
     * 新生代10MB eden 8MB survivor 1MB
     * 老年代10MB
     */
    public static void testAllocation() {

        byte[] allocation1, allocation2, allocation3, allocation4;
//        byte[] allocation5;
//        byte[] allocation6;
//        byte[] allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
//        allocation5 = new byte[2 * _1MB];
//        allocation6 = new byte[4 * _1MB];

    }

    public static void main(String[] args) {
        TestAllocation.testAllocation();
    }
}
