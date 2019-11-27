package com.github.q742972035.tools;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 构建一个ThreadFactory构造器，对应的name的序列号全局唯一且递增
 *
 * @program: tools
 * @description:
 * @author: 张忆
 * @create: 2019-11-17 22:33
 **/
public class ThreadFactoryBuild {

    /**
     * 根据名字前缀的全局递增器
     */
    private static final Map<String, AtomicLong> NAME_AUTO_INCREMENNT = new ConcurrentHashMap<String, AtomicLong>();

    /**
     * @param namePrefix 名字前缀
     * @return ThreadFactory
     */
    public static ThreadFactory build(String namePrefix) {
        String correctName = getCorrectName(namePrefix);
        AtomicLong atomicLong = NAME_AUTO_INCREMENNT.computeIfAbsent(correctName, k -> new AtomicLong());
        return r -> {
            String name = correctName + atomicLong.getAndIncrement();
            return new Thread(r, name);
        };
    }

    /**
     * @param namePrefix 名字前缀
     * @return 以-结尾的名字
     */
    public static String getCorrectName(String namePrefix) {
        while (namePrefix.endsWith("-")) {
            namePrefix = namePrefix.substring(0, namePrefix.length() - 1);
            if (!namePrefix.endsWith("-")) {
                break;
            }
        }
        return namePrefix + "-";
    }
}
