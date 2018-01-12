package dam.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class MethodResultCache {
    private Cache methodResultCache;

    public MethodResultCache(long size, long duration) {
        methodResultCache = CacheBuilder.newBuilder()
                .concurrencyLevel(16)
                .maximumSize(size)
                .expireAfterWrite(duration, TimeUnit.SECONDS)
                .build();
    }

    public void put(String key, Object value) {
        methodResultCache.put(key, value);
    }


    public Object get(String key, final Callable<Object> createIfNotPresent) throws ExecutionException {
        return methodResultCache.get(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return createIfNotPresent.call();
            }
        });
    }
}
