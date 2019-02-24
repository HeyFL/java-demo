package com.example.test.demo.jvm.sugar;

/**
 * 自动装箱测试
 */
public class AutoboxingTest {
    public AutoboxingTest(){
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println("c == d :"+(c == d));
        System.out.println("e == f :"+(e == f));
        System.out.println("c == (a + b) :"+(c == (a + b)));
        System.out.println("c.equals(a + b) :"+c.equals(a + b));
        System.out.println("g == (a + b) :"+(g == (a + b)));
        System.out.println("g.equals(a + b) :"+g.equals(a + b));
    }
}
