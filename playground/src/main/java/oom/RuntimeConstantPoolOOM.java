package oom;

import java.util.HashSet;
import java.util.Set;

/**
 * VM Args: -XX:PermSize=6M -XX:MaxPermSize=6M
 * 只有jdk1.6 OOM PermGen space
 * jdk1.7之后 原本存放在永久代的字符串常量池被移至java堆之中，这时候使用-Xmx参数限制最大堆6MB
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {


        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);

        Set<String> set = new HashSet<>();
        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }
    }
}
