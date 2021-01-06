package com.cnbot.kgrobot.base;


/**
 * Copyright (c) 2016-a  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 网络请求的响应回调
 * @FileName: IResponse.java
 * @author: dc
 * @date: 2019/1/21 14:23
 * @version: 1.0
 */

public interface IResponse<T> {

    /**
     * 成功的回调
     * @param t
     */
    void onSuccess(T t);

    /**
     *错误的回调
     * @param throwable
     */
    void onError(Throwable throwable);

}
