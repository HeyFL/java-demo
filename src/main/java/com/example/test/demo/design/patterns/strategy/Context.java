package com.example.test.demo.design.patterns.strategy;

/**
 * 环境角色
 */
public class Context {

    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int doOperation(int a, int b) {
        return strategy.doOperation(a, b);
    }
}
