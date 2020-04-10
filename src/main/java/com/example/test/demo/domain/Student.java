/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo.domain;

import lombok.*;

@Data
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Student {
    private String name;
    private int age;
    private Integer ageInteger;
    private String sex;
    private boolean aBoolean;
    private Boolean bBoolean;

    public Student(){}

    public Student(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student);
    }
}
