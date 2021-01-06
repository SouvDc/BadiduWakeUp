package com.cnbot.kgrobot.http.api;

import android.text.TextUtils;

import com.cnbot.dchttpsdk.api.ApiEngine;
import com.cnbot.dchttpsdk.helper.DownFileProgressListener;

import rx.Observable;
import rx.Subscriber;

import static com.cnbot.kgrobot.constant.HttpConstant.HTTPSERVICEIP;


/**
 * Copyright (c) 2016-a  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin http请求管理接口类
 * @FileName: ApiHelper.java
 * @author: dc
 * @date: 2019/1/19 20:49
 * @version: 1.0
 */

public class ApiHelper {

    public static IHttpInterface getDownloadService(DownFileProgressListener l, String path) {

        return (IHttpInterface) ApiEngine.build().getApiService(HTTPSERVICEIP, l, path);

    }



    public static IHttpInterface getApiService() {
        // TODO: 2019/3/29 非null判断
        ApiEngine apiEngine = ApiEngine.build();
        IHttpInterface iHttpInterface = null;
        if(apiEngine != null){
            iHttpInterface = (IHttpInterface) apiEngine.getApiService(HTTPSERVICEIP);
        }
        return iHttpInterface;

    }

    /**
     * 登录
     *
     * @param subscriber
     */
    public static void login(Subscriber subscriber, String suuid) {
        if (!TextUtils.isEmpty(suuid)) {
            if(getApiService() != null) {
                Observable observable = getApiService().login(suuid);
                ApiEngine.build().toSubscribe(observable, subscriber);
            }
        }
    }

    /**
     * 语义请求
     *
     * @param subscriber
     */
    public static void customerAskResult(Subscriber subscriber, String ask) {
        if (!TextUtils.isEmpty(ask)) {
            Observable observable = getApiService().customerAsk("token","4028a81f756e853e01756ead76f10022", ask);
            ApiEngine.build().toSubscribe(observable, subscriber);
        }
    }

}
