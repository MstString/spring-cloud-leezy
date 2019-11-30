package com.leezy.concurrency;

import com.leezy.concurrency.annoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @program: spring-cloud-leezy
 * @description: 测试并发
 * @author: LEEZY
 * @create: 2019-11-30 16:03
 **/

@Slf4j
@NotThreadSafe
// count:4962 结果不准确，线程不安全
public class ConcurrencyTest {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public static int count = 0;

    private static void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 信号量以及并发线程数
        final Semaphore semaphore = new Semaphore(threadTotal);
        // 计数器-闭锁 放入请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    // 根据并发的限制数量, 判断当前线程是否允许被执行
                    semaphore.acquire();
                    add();
                    // 释放线程
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                // 执行一次就进行releaseShared
                countDownLatch.countDown();
            });
        }
        // 这个方法可以保证 countDown 减为0
        countDownLatch.await();
        // 关闭线程池
        executorService.shutdown();
        log.info("count:" + count);

    }

}
