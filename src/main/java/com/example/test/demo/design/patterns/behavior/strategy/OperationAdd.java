package com.example.test.demo.design.patterns.behavior.strategy;

public class OperationAdd implements Strategy {
    @Override
    public int doOperation(int a,int b){
        return a+b;
    }
}
