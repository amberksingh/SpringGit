package com.example.SpringGit.memoryLeak;

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
    }


    //
    private static Map<String, Object> cache = new HashMap<>();

    public static void put(String key, Object value) {
        cache.put(key, value);
    }

    public static void main(String[] args) {

        MapCache.put("A", new byte[5 * 1024 * 1024]); // 5MB
        MapCache.put("B", new byte[5 * 1024 * 1024]);
        MapCache.put("C", new byte[5 * 1024 * 1024]);



    }
}
