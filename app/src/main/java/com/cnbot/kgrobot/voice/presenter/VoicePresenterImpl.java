package com.cnbot.kgrobot.voice.presenter;


import android.content.Context;
import android.util.Log;

import com.cnbot.kgrobot.base.BasePresenter;
import com.cnbot.kgrobot.bean.BaseVoiceBean;
import com.cnbot.kgrobot.voice.model.VoiceModelImpl;
import com.cnbot.kgrobot.voice.view.VoiceView;

import androidx.annotation.NonNull;


/**
 * Copyright (c) 2016--2019/4/12  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 语义结果P
 * @FileName: VoicePresenterImpl.java
 * @author: dc
 * @date: 2019/4/12 11:42
 * @version: 1.0
 */

public class VoicePresenterImpl extends BasePresenter<VoiceView, VoiceModelImpl> implements VoiceView.IVoiceResultListener {


    public VoicePresenterImpl(Context context, @NonNull VoiceView view) {
        super(context, view);

        VoiceModelImpl model = new VoiceModelImpl(context);
        setModel(model);
    }

    /**
     * 发送语义分析
     * @param ask
     */
    public void sendVoiceAsk(String ask){

        getModel().customerAskResult(ask, this);
    }

    @Override
    public void voiceResult(BaseVoiceBean bean) {
        getView().onSuccess(bean);
    }

    @Override
    public void httpNotDataResult() {
        Log.e("AiEngineService", "无数据");
        getView().getByPlaceCondNotData();
    }

    @Override
    public void httpFail(String str) {
        getView().requestFail(str);
    }
}