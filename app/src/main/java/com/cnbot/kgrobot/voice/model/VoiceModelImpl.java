package com.cnbot.kgrobot.voice.model;


import android.content.Context;
import android.util.Log;

import com.cnbot.dchttpsdk.base.BaseModel;
import com.cnbot.dchttpsdk.helper.OnSubscriberListener;
import com.cnbot.dchttpsdk.rx.DefaultSubscriber;
import com.cnbot.kgrobot.R;
import com.cnbot.kgrobot.bean.BaseVoiceBean;
import com.cnbot.kgrobot.http.api.ApiHelper;
import com.cnbot.kgrobot.utils.Toaster;
import com.cnbot.kgrobot.voice.view.VoiceView;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Copyright (c) 2016--2019/4/12  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 语音语义分析M
 * @FileName: VoiceModelImpl.java
 * @author: dc
 * @date: 2019/4/12 11:39
 * @version: 1.0
 */

public class VoiceModelImpl extends BaseModel {
    private final String TAG = VoiceModelImpl.class.getSimpleName();
    public VoiceModelImpl(Context context) {
        super(context);
    }

    public void customerAskResult(String ask, final VoiceView.IVoiceResultListener listener){
        Log.e(TAG, "语义分析的内容=" + ask);
        Subscriber subscriber = new DefaultSubscriber(new OnSubscriberListener<BaseVoiceBean>() {
            @Override
            public void onNext( BaseVoiceBean baseVoiceBean) {
                    if(baseVoiceBean == null) {
                        listener.httpNotDataResult();
                        return;
                    }
                    listener.voiceResult(baseVoiceBean);
            }

            @Override
            public void onError(Throwable e) {
                Log.v(TAG, "onError " + e.getMessage());
                if (e instanceof SocketTimeoutException) {
                    Toaster.showCenterToast(mContext.getString(R.string.net_timeout_exception));
                } else if (e instanceof ConnectException) {
                    Toaster.showCenterToast(mContext.getString(R.string.net_connect_exception));
                } else if (e instanceof HttpException) {
                    Toaster.showCenterToast(mContext.getString(R.string.http_request_exception));
                } else if (e instanceof JsonSyntaxException) {

                    Toaster.showCenterToast(mContext.getString(R.string.http_request_exception));
                }  else if (e instanceof ClassCastException) {
                    Toaster.showCenterToast(mContext.getString(R.string.http_request_exception));
                } else {
                    Toaster.showCenterToast(e.getMessage());
                }
                listener.httpFail(e.getMessage());
            }

            @Override
            public void onFail(String msg) {
                listener.httpFail(msg);
            }
        }, mContext);
        ApiHelper.customerAskResult(subscriber, ask);
        addSubscribe(subscriber);
    }
}