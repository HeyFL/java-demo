package com.example.test.demo.design.patterns.builder;

import lombok.Data;

@Data
public class Classmate {
    private String name;
    private boolean sex;
    private int age;

    private Classmate() {
    }

    public static class ClassmateBuilder {
        private String name;
        private boolean sex;
        private int age;

        private ClassmateBuilder() {
        }

        public static ClassmateBuilder aClassmate() {
            return new ClassmateBuilder();
        }

        public ClassmateBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ClassmateBuilder withSex(boolean sex) {
            this.sex = sex;
            return this;
        }

        public ClassmateBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public Classmate build() {
            Classmate classmate = new Classmate();
            classmate.setName(name);
            classmate.setSex(sex);
            classmate.setAge(age);
            return classmate;
        }
    }
}
