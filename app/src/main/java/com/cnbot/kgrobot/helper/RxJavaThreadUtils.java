package com.cnbot.kgrobot.helper;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Copyright (c) 2016--2019/8/26  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin rxjava, 简单的线程调度器
 * @FileName: RxJavaThreadUtils.java
 * @author: dc
 * @date: 2019/8/26 12:04
 * @version: 1.0
 */

public class RxJavaThreadUtils {

    /**
     * 主线程做操作
     * @param uiTask
     */
    public static void doOnUIThread(UITask uiTask){
        Observable.just(uiTask)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(uiTask1 -> uiTask.doOnUI());

    }

    /**
     * 主线程做操作
     * @param uiTask
     */
    public static void doOnUIThread1(UITask uiTask){
        Observable.just(uiTask)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<? super UITask>) o -> {
                    uiTask.doOnUI();
                });

    }



    /**
     * 主线程做操作, 带延迟方法
     * @param deley 延时时间，单位为ms
     * @param uiTask
     */
    public static void doOnUIDeleyThread(int deley, UITask uiTask){
        Observable.just(uiTask).delay(deley, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((Consumer<? super UITask>) o -> {
                    uiTask.doOnUI();
                });
    }


    /**
     * io线程做操作
     * @param threadTask
     */
    public static void doOnThread(ThreadTask threadTask){
        Observable.just(threadTask)
                .observeOn(Schedulers.io())
                .subscribe(threadTask1 -> threadTask1.doOnThread());
    }

    /**
     * 主线程做操作, 带延迟方法
     * @param deley 延时时间，单位为ms
     * @param threadTask
     */
    public static void doOnDeleyThread(int deley, ThreadTask threadTask){
        Observable.just(threadTask).delay(deley, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .subscribe(threadTask1 -> threadTask1.doOnThread());
    }

    public interface ThreadTask{
        /**
         * 在子线程中执行
         */
        void doOnThread();
    }

    public interface UITask{
        /**
         * 在ui线程执行
         */
        void doOnUI();
    }
}
