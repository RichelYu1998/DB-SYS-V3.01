package cn.tedu.common.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SimpleCache {
    private Map<Object,Object> cache=new ConcurrentHashMap<>();

    public boolean putObject(Object key,Object value) {
        cache.put(key, value);
        return true;
    }
    public Object getObject(Object key) {
        return cache.get(key);
    }
    public void clearObject() {
        cache.clear();
    }
    //.......
}