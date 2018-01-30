package test;

import com.example.test.demo.EnumSingleton.SingletonEnum;
import org.junit.Test;

/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
public class SingletonTest {

    @Test
    public void testEnum(){
        SingletonEnum single = SingletonEnum.INSTANCE;
        single.printMethod();

        //Enum 不能创建实例
        //SingletonEnum single1 = new SingletonEnum();
        //SingletonEnum.class.newInstance();
    }
}
