package com.github.q742972035.tools;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadFactory;

import static org.assertj.core.api.Assertions.*;

public class ThreadFactoryBuildTest {

    @Test
    public void getCorrectName() {
        String prefixName = "aaaa------";
        assertThat(ThreadFactoryBuild.getCorrectName(prefixName)).isEqualTo("aaaa-");
        prefixName = "aaaa";
        assertThat(ThreadFactoryBuild.getCorrectName(prefixName)).isEqualTo("aaaa-");
        prefixName = "aaaa-";
        assertThat(ThreadFactoryBuild.getCorrectName(prefixName)).isEqualTo("aaaa-");
    }

    @Test
    public void build() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        CountDownLatch latch1 = new CountDownLatch(10);
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        latch.await();

                        String name = "testthread";
                        ThreadFactory build = ThreadFactoryBuild.build(name);
                        Thread thread = build.newThread(null);
                        String threadName = thread.getName();
                        assertThat(name).isEqualTo(threadName.split("-")[0]);
                        list.add(Integer.parseInt(threadName.split("-")[1]));
                        latch1.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }


        Thread.sleep(500);
        latch.countDown();
        latch1.await();

        Collections.sort(list);

        assertThat("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]").isEqualTo(list.toString());
    }


}