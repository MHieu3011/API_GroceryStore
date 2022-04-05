package com.vcc.apigrocerystore.cache.local;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.vcc.apigrocerystore.cache.ICache;
import com.vcc.apigrocerystore.global.ConfigInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component("responseLocalCache")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ResponseLocalCache implements ICache<String, Object> {

    private static final Logger eLogger = LogManager.getLogger("CacheError");
    private static final Logger cLogger = LogManager.getLogger("CacheLog");

    private static final int LOCAL_CACHE_MAX_SIZE = ConfigInfo.RESPONSE_CACHE_INFO_SIZE;
    private static final int LOCAL_CACHE_EXPIRE_TIME = ConfigInfo.RESPONSE_CACHE_INFO_EXPIRE;

    private LoadingCache<String, Object> cache =
            CacheBuilder.newBuilder()
                    .maximumSize(LOCAL_CACHE_MAX_SIZE)
                    .expireAfterWrite(LOCAL_CACHE_EXPIRE_TIME, TimeUnit.MINUTES)
                    .build(
                            new CacheLoader<String, Object>() {
                                @Override
                                public Object load(String key) throws Exception {
                                    return getFromSource(key);
                                }
                            }
                    );


    @Override
    public void clear() {
        cache.invalidateAll();
    }

    @Override
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public Object get(Object key) throws Exception {
        try {
            return this.cache.get((String) key);
        } catch (Exception e) {
            eLogger.error("Error: {}", e.getMessage());
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Object put(String key, Object value) {
        cache.put(key, value);
        return null;
    }

    @Override
    public Object remove(Object key) {
        cache.invalidate(key);
        return null;
    }

    @Override
    public int size() {
        return (int) cache.size();
    }

    private Object getFromSource(String key) {
        return null;
    }
}
