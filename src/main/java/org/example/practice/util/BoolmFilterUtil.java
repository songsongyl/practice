package org.example.practice.util;

import cn.hutool.bloomfilter.BitMapBloomFilter;

//使用单例模式创造布隆模式工具类
public class BoolmFilterUtil {
    private static BoolmFilterUtil instance = new BoolmFilterUtil();
    private static final BitMapBloomFilter filter = new BitMapBloomFilter(10);

    public static BoolmFilterUtil getInstance() {
        return instance;
    }

    public void add(String str) {
        filter.add(str);
    }

    public boolean contains(String str) {
        return filter.contains(str);
    }
}
