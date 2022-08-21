package com.example.test.demo.nio.http.utils;


import com.example.test.demo.nio.http.exception.AssertionException;

import java.io.IOException;

/**
 * @author caojiancheng
 * @date 2021/5/8
 * @description
 */
public class Assert {
    public static void isTrue(boolean test, String message) {
        if (!test) {
            throw new AssertionException(message);
        }
    }

    public static void isTrue(boolean test, Throwable e) {
        if (!test) {
            throw new AssertionException(e);
        }
    }

    public static void isIoTrue(boolean test, String message) throws IOException {
        if (!test) {
            throw new IOException(message);
        }
    }
}
