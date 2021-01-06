package com.cnbot.kgrobot.base;

import android.content.Context;

import com.cnbot.dchttpsdk.base.BaseModel;
import com.orhanobut.logger.Logger;

import androidx.annotation.NonNull;

/**
 * Copyright (c) 2016-a  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin MVP的桥梁presenter基类，对RxJava的注销作了封装
 * @FileName: BasePresenter.java
 * @author: dc
 * @date: 2019/1/21 14:25
 * @version: 1.0
 */

public class BasePresenter<V extends IBaseView, M extends BaseModel> implements IResponse {
    private V mView;
    private M mModel;
    /**
     * P 的名称，主要用于一个Act存在多个P时，根据此字段来查询P 对象
     */
    private String pName;

    public BasePresenter(Context context, @NonNull V view) {
        Logger.i("创建P M");
        mView = view;
    }

    public BasePresenter(@NonNull V view) {
        mView = view;
    }

    protected V getView() {
        return mView;
    }

    protected void setView(@NonNull V view) {
        mView = view;
    }

    protected M getModel() {
        return mModel;
    }

    protected void setModel(@NonNull M model) {
        mModel = model;
    }


    public String getpName() {
        return pName == null ? "" : pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * 注销，防止内存泄漏,一般在Activity、Fragment、Dialog的基类中的销毁的方法中调用
     * @notice 必须调用！！！
     */
    public void unSubscribe() {
        if (mView != null) {
            mView = null;
        }

        if (mModel != null) {
            Logger.i("P M 进行销毁");
            mModel.unSubscribe();
        }

    }

    @Override
    public void onSuccess(Object bean) {
        if (getView() != null) {
            getView().onSuccess(bean);
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }

}
