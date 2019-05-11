package com.gp12713.mybatis.v2.executor;

import com.gp12713.mybatis.v2.cache.CacheKey;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 带缓存的执行器，用于装饰基本执行器
 */
@Slf4j
public class CachingExecutor implements Executor {
    private Executor delegate;
    private static final Map<Integer, Object> cache = new HashMap<>();

    public CachingExecutor(Executor delegate) {
        this.delegate = delegate;
    }

    @Override
    public <T> T query(String statement, Object[] parameter, Class pojo) {
        //计算Cachekey
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(statement);
        cacheKey.update(joinStr(parameter));
        //是否命中缓存
        if (cache.containsKey(cacheKey.getCode())) {
            //命中缓存
            log.info("【命中缓存】");
            return (T) cache.get(cacheKey.getCode());
        } else {
            //没有的话调用被装饰的SimpleExecutor从数据库查询
            Object obj = delegate.query(statement, parameter, pojo);
            cache.put(cacheKey.getCode(), obj);
            return (T) obj;
        }
    }

    /**
     * 为了命中缓存，把Object[]转换成逗号拼接的字符串，因为对象的HashCode都不一样
     *
     * @param objs
     * @return
     */
    private Object joinStr(Object[] objs) {
        StringBuffer sb = new StringBuffer();
        if (null != objs && objs.length > 0) {
            for (Object obj : objs) {
                sb.append(obj.toString() + ",");
            }
        }
        int len = sb.length();
        if (len > 0) {
            //去掉最后一个逗号
            sb.deleteCharAt(len - 1);
        }
        return sb.toString();
    }
}
