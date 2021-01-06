package com.cnbot.kgrobot.base;


/**
 * Copyright (c) 2016--2019/3/1  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 封装请求接口，固定方法
 * @FileName: IBaseResultListener.java
 * @author: dc
 * @date: 2019/3/1 18:09
 * @version: 1.0
 */

public interface IBaseResultListener<T> {


    /**
     * 没有查到数据
     */
    void httpNotDataResult();

    /**
     * 查询错误
     * @param msg
     */
    void httpFail(String msg);

}
