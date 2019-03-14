package com.example.test.demo.design.patterns.behavior.strategy;

public class OperationMultiply implements Strategy {
    @Override
    public int doOperation(int a,int b){
        return a*b;
    }
}
