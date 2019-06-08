package com.libingyi.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class DemoApplication {
    // 每100毫秒钟创建100线程，每个线程创建一个1M的对象，即每100ms申请100M堆空间
    public static void main(String[] args) {
        // java -Xms200m -Xmx200m -Xmn100m -verbose:gc -XX:+PrintGCDetails -Xloggc:./gc.log -XX:+PrintGCDateStamps -XX:-UseAdaptiveSizePolicy -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=10 -jar demo-0.0.1-SNAPSHOT.jar
        // java -Xms200m -Xmx200m -Xmn100m -verbose:gc -XX:+PrintGCDetails -Xloggc:./gc.log -XX:+PrintGCDateStamps -XX:MaxTenuringThreshold=10 -jar demo-0.0.1-SNAPSHOT.jar
        // XX:MaxTenuringThreshold = 5
        SpringApplication.run(DemoApplication.class, args);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            for (int i = 0; i < 100; i++) {
                new Thread(() -> {
                    try {
                        //  申请512kb
                        byte[] temp = new byte[1024 * 1024];
                        Thread.sleep(new Random().nextInt(1000)); // 随机睡眠1秒以内
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }, 1000, 100, TimeUnit.MILLISECONDS);
    }

}
