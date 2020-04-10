package com.example.test.demo.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 查看foreach语法糖编译后代码
 * 及
 * 快速失败FailFast例子
 */
public class IteratorFailFastTest {

    /**
     * 抛出 FailFast异常
     * 因为增强型foreach为java语法糖,实际上是用itertor实现的,
     * for (String userName : userNames) 这里每次都会去调用itertor.next(),这里会去调用checkForComodification()方法  然后发现modCount != expectedModCount 抛出异常
     * <p>
     * 为什么会modCount != expectedModCount ?
     * 因为list.add()和list.remove()等不通过Iterator的操作,不会修改expectedModCount
     *
     * @return
     */
    public static List test1() {
        List<String> userNames = new ArrayList<String>() {{
            add("test1");
            add("test12");
            add("test13");
            add("test14");
        }};
        for (String userName : userNames) {
            if (userName.equals("test12")) {
                userNames.remove(userName);
            }
        }

        return userNames;
    }

    /**
     * 正常操作
     *
     * @return
     */
    public static List test2() {
        List<String> userNames = new ArrayList<String>() {{
            add("test1");
            add("test12");
            add("test13");
            add("test14");
        }};

        Iterator<String> iterator = userNames.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.equals("test12")) {
                iterator.remove();
            }
        }
        return userNames;
    }

    public static void pirintList(List<String> userNames) {
        for (String userName : userNames) {
            System.out.println(userName);
        }
    }

    public static void main(String[] args) {
        //抛出fail-fast异常
        pirintList(test1());
        //正常操作
        pirintList(test2());
    }
}
