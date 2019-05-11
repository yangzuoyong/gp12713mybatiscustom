package com.gp12713.mybatis.v2.cache;

/**
 * 缓存
 */
public class CacheKey {
    //默认哈希值
    private static final int DEFAULT_HASHCODE=17;
    //倍数
    private static final int DEFAULT_MULTIPLIER=37;
    private int hashCode;
    private int count;
    private int multiplier;

    public CacheKey() {
        this.hashCode = DEFAULT_HASHCODE;
        this.count=0;
        this.multiplier=DEFAULT_MULTIPLIER;
    }

    /**
     * 返回CacheKey的值
     * @return
     */
    public int getCode(){
        return hashCode;
    }

    /**
     * 计算CacheKey中的HashCode
     * @param object
     */
    public void update(Object object){
        int bashHashCode = object==null?1:object.hashCode();
        count++;
        bashHashCode*=count;
        hashCode=multiplier*hashCode+bashHashCode;
    }
}
