package com.example.test.demo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author caizq
 * @date 2018/6/5
 * @since v1.0.0
 */
public class CallableTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadDemo demo = new ThreadDemo(100);
        FutureTask futureTask = new FutureTask(demo);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }



    static class ThreadDemo implements Callable{

        private int num;

        public ThreadDemo(int num){
            this.num = num;
        }
        @Override
        public Object call() throws Exception {
            for (int i = 0; i < 5; i++) {
                num++;
                Thread.sleep(1000);
            }
            return num;
        }
    }
}
