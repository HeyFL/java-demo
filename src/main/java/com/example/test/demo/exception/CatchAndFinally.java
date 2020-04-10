
package com.example.test.demo.exception;

import java.io.IOException;

/**
 * @author caizq
 * @date 2018/11/16
 * @since v1.0.0
 */

public class CatchAndFinally {

    public static void main(String[] args) {
        try {
            System.out.println(test());
            System.out.println(testThrow());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String test() throws IOException {
        try {
            //return "A";
            throw new Exception("1");
        } catch (Exception e) {
            return "B";
            //throw new Exception("2");
        } finally {
            return "c";
            //System.out.print("C");
            //throw new IOException("3");
        }
    }

    private static String testThrow1() throws Exception {
        try {
            throw new Exception("1");
        } catch (Exception e) {
            throw new Exception("2");
        } finally {
            return "3";
            //throw new IOException("3");
        }
    }

    private static String testThrow() throws Exception {
        try {
            throw new Exception("1");
        } catch (Exception e) {
            throw new Exception("2");
        } finally {
            return "3";
            //throw new IOException("3");
        }
    }
}
