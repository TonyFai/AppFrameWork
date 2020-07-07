package com.dxtdkwt.zzh.baselibrary.model;

import java.io.Serializable;

/**
 * 缓存的实体类
 *
 * @param <T>
 */
public class BaseCachedData<T> implements Serializable {
    /**
     * 更新时间
     * 用于，一周一加载，或，一月一加载的操作
     */
    public long updateTimeInMills;
    /**
     * 数据源
     */
    public T data;
}
