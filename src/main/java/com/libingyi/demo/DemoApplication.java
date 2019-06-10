package com.libingyi.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//        gcTest();
//        cpuTest();
        lockTest();
    }

    private static void gcTest() {
        // 每100毫秒钟创建100线程，每个线程创建一个1M的对象，即每100ms申请100M堆空间
        // java -Xms200m -Xmx200m -Xmn100m -verbose:gc -XX:+PrintGCDetails -Xloggc:./gc.log -XX:+PrintGCDateStamps -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=10 -jar demo-0.0.1-SNAPSHOT.jar
        // java -Xms200m -Xmx200m -Xmn100m -verbose:gc -XX:+PrintGCDetails -Xloggc:./gc.log -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=10 -jar demo-0.0.1-SNAPSHOT.jar
        // XX:MaxTenuringThreshold = 5
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    try {
                        //  申请1M
                        byte[] temp = new byte[1024 * 1024];
                        Thread.sleep(new Random().nextInt(1000)); // 随机睡眠1秒以内
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }

    private static void cpuTest() {
        new Thread(() -> {
            while (true) {
                new Object();
            }
        }, "CPU-100").start();
        while (true) {
            for (int i = 0; i < 10; i++) {
                new Thread(() -> {
                    try {
                        new Object();
                        long random = new Random().nextInt(200);
                        Thread.sleep(random);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void lockTest() {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("deadLock-1");
                }
            }
        },"deadLock-1").start();
        new Thread(() -> {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("deadLock-2");
                }
            }
        },"deadLock-2").start();
    }

}
