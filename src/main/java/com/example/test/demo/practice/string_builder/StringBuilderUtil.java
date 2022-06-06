package com.example.test.demo.practice.string_builder;

public class StringBuilderUtil {
    private static final ThreadLocal<StringBuilderHelper> threadLocalStringBuilderHelper = new ThreadLocal<StringBuilderHelper>() {
        @Override
        protected StringBuilderHelper initialValue() {
            return new StringBuilderHelper();
        }
    };

    public static final StringBuilder getStringBuilder() {
        return threadLocalStringBuilderHelper.get().getStringBuilder();
    }

    static final class StringBuilderHelper {
        final StringBuilder sb;

        StringBuilderHelper() {
            sb = new StringBuilder();
        }

        StringBuilder getStringBuilder() {
            sb.setLength(0);
            return sb;
        }
    }

}
