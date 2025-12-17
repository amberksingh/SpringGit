package com.example.SpringGit.memoryLeak;

import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MapCache {

    //🔧 Fixes
//✔ Fix 1 — Use LRU cache (evicts old items)
    private static final int MAX_SIZE = 1000;

    private static final Map<String, Object> cache1 =
            new LinkedHashMap<>(MAX_SIZE, 0.75f, true) {
                protected boolean removeEldestEntry(Map.Entry eldest) {
                    return size() > MAX_SIZE;
                }
            };
    LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(MAX_SIZE, 0.75f, false);

//✔ Fix 2 — Use proper cache library
//    Caffeine (recommended):
//    Cache<String, Object> cache = Caffeine.newBuilder()
//            .maximumSize(500)
//            .expireAfterWrite(10, TimeUnit.MINUTES)
//            .build();

//✔ Fix 3 — Periodically clear
    @Scheduled(fixedRate = 60000)
    public void cleanup() {
        cache.clear();
//        MDC.put("1", "hello");
//        MDC.put("2", "world");
//        MDC.get("1");
//        MDC.get("2");
    }


    //
    private static Map<String, Object> cache = new HashMap<>();

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {

        MDC.put("1", "hello");
        MDC.put("2", "world");
        System.out.println(MDC.get("1"));
        System.out.println(MDC.get("2"));

        MapCache.put("A", new byte[5 * 1024 * 1024]); // 5MB
        MapCache.put("B", new byte[5 * 1024 * 1024]);
        MapCache.put("C", new byte[5 * 1024 * 1024]);



    }
}
