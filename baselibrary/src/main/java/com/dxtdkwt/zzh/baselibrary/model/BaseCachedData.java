package com.dxtdkwt.zzh.baselibrary.model;

import java.io.Serializable;

/**
 * 缓存的实体类
 * @param <T>
 */
public class BaseCachedData<T> implements Serializable {
    public long updateTimeInMills;
    public T data;
}
