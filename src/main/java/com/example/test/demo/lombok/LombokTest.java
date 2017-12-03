/*
* Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
* Use, Copy is subject to authorized license.
*/
package com.example.test.demo.lombok;

import com.example.test.demo.domain.Student;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

//@Log4j2
public class LombokTest {
    public static void main(String[] args) {
        //Student student = new Student("张三",11,"男");
        Student student = new Student();
        student.setAge(11);
        student.setName("张三");
        student.setSex("男");
        System.out.println(student.toString());
        //log.println("asdasd");需要相关logger的jar
    }
}
