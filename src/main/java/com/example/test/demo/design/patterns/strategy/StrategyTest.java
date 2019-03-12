package com.example.test.demo.design.patterns.strategy;

public class StrategyTest {

    public static void main(String[] args) throws InterruptedException {
        int a=1;
        int b=5;
        Context context = new Context(new OperationAdd());
        int result = context.doOperation(a,b);
        System.out.println(result);
    }
}
