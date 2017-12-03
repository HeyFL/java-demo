/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo;

public class StringAppendTest {

    private static int tryNum = 9999999;

    public static void main(String[] args) {
//        testString();
//        testStringBuilder();
//        testStringBuffer();
    }

    private static void testStringBuffer() {
        StringBuffer str = new StringBuffer("");
        for (int i = 0; i < tryNum; i++) {
            str.append("1");
        }
    }

    private static void testStringBuilder() {
        StringBuilder str = new StringBuilder("");
        for (int i = 0; i < tryNum; i++) {
            str.append("1");
        }
    }

    private static void testString() {
        String str = "";
        for (int i = 0; i < tryNum; i++) {
            str += "1";
        }
    }
}
