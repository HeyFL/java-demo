/*
 * Copyright (c) 201 3 , FPX and/or its affiliates. All rights reserved.
 * Use, Copy is subject to authorized license.
 */
package com.example.test.demo.redisson;
import org.redisson.Redisson;
import org.redisson.RedissonRedLock;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author caizq
 * @param  * @param null
 * @date 2018/1/26
 * @since v1.0.0
 */
public class RedissionTest {
    private static Config config = new Config();
    private static RedissonClient redisson;
    static {
        config.useClusterServers().addNodeAddress("redis://127.0.0.1:7000", "redis://127.0.0.1:7001","redis://127.0.0.1:7002",
                "redis://127.0.0.1:7003","redis://127.0.0.1:7004", "redis://127.0.0.1:7005");
        redisson = Redisson.create(config);
    }
    private static ExecutorService pool = Executors.newFixedThreadPool(50);
    private static AtomicInteger count = new AtomicInteger(0);
    public void testRedLock(){
        //多把锁是为了让锁加在不同节点上,理论上  这里的锁越多分布的节点越是均匀,容灾效果越好
        RLock lock1 = redisson.getLock("lock" + "1");
        RLock lock2 = redisson.getLock("lock" + "2");
        RLock lock3 = redisson.getLock("lock" + "3");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        //RedissonRedLock lock = new RedissonRedLock(lock1);
        try {
            // 同时加锁：lock1 lock2 lock3, 红锁在大部分加锁成功就算成功。
            //lock.unlock();
            //lock.lock();

            // 尝试加锁，最多等待1秒，上锁以后10秒自动解锁  对于竞争激烈,处理时间长的,可以多次try或死循环try,不建议增加等待时间
            boolean res = lock.tryLock(1, 10, TimeUnit.SECONDS);
            if (res){
                RAtomicLong atomicLong = redisson.getAtomicLong("myLong");
                System.out.println("myLong = "+atomicLong.incrementAndGet());
            }else{
                System.out.println("获取锁失败次数 = "+count.incrementAndGet());
            }
            //Thread.sleep(10000);


            System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111111111111111"+res);
            //boolean res1 = lock.tryLock(100, 10, TimeUnit.SECONDS);
            //System.out.println("222222222222222222222222222222222222222222222222222222222222222222222222222222222"+res1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            //redisson.shutdown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            pool.submit(new Thread(()->{
                new RedissionTest().testRedLock();
            }));
        }
        Thread.sleep(1000 );
        //RedissionTest.redisson.shutdown();

    }
}
