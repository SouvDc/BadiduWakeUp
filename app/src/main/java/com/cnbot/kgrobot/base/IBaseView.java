package com.cnbot.kgrobot.base;


/**
 * Copyright (c) 2016-a  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 视图基类
 * @FileName: IBaseView.java
 * @author: dc
 * @date: 2019/1/21 14:22
 * @version: 1.0
 */

public interface IBaseView<T> {


    /**
     * 请求数据成功
     *
     * @param tData 数据类型
     */
    void onSuccess(T tData);


    /**
     * 没有查到数据
     */
    void getByPlaceCondNotData();

    /**
     * 请求异常
     */
    void requestFail(String msg);



}
