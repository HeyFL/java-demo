package com.example.test.demo.design.patterns.build.builder;

public class BuilderTest {
    public static void main(String[] args) throws InterruptedException {
        Classmate classmate = Classmate.ClassmateBuilder.aClassmate().withName("tom").withAge(12).build();
        System.out.println(classmate);
    }
}
