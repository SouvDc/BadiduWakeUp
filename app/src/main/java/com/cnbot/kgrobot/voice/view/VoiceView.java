package com.cnbot.kgrobot.voice.view;


import com.cnbot.kgrobot.base.IBaseResultListener;
import com.cnbot.kgrobot.base.IBaseView;
import com.cnbot.kgrobot.bean.BaseVoiceBean;

/**
 * Copyright (c) 2016--2019/5/23  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 语音交互V
 * @FileName: VoiceView.java
 * @author: dc
 * @date: 2019/5/23 8:47
 * @version: 1.0
 */

public class VoiceView implements IBaseView<BaseVoiceBean> {

    @Override
    public void onSuccess(BaseVoiceBean tData) {

    }

    @Override
    public void getByPlaceCondNotData() {

    }

    @Override
    public void requestFail(String msg) {

    }


    public interface IVoiceResultListener extends IBaseResultListener {
        /**
         * 语义返回结果
         * @param bean
         */
        void voiceResult(BaseVoiceBean bean);
    }
}