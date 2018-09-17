package com.example.test.demo.jvm;



/**
 * jdk6返回2个 false
 * jdk7、8返回true&false
 *
 * 因为 jdk6的 String.intern() 是把字符复制到常量池
 * 而jdk7、8  是把该（堆里的）String对象 的引用复制到常量池
 */
public class StringInternTest {
    public static void main(String[] args)  {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        //java在初始化的时候早就把 java 这个词缓存到常量池 所以这里返回false
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }
}
