package com.cnbot.kgrobot.helper;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

/**
 * Copyright (c) 2016--2019/1/16  Hunan Cnbot Co., Ltd. All Rights Reserved.
 **/

/**
 *@descriptoin 提供application级别的上下文和全局的Handler
 *@FileName: AppHelper.java
 *@author: ww
 *@date: 2019/1/16 16:26
 *@version: 1.0
 */

public class AppHelper {
	private static final String TAG = AppHelper.class.getSimpleName();
	private static Context sContext;
	private static Handler sHandler;
	private static Handler sWorkHandler;
	/**
	 * please init it in your custom application
	 * @param context
	 */
	public static void init(Context context) {
		sContext = context;
		sHandler = new Handler();

		initWorkHandler();

	}

	/**
	 * 初始化子线程handler
	 */
	private static void initWorkHandler() {
		HandlerThread handlerThread = new HandlerThread("work") {

			@Override
			protected void onLooperPrepared() {
				sWorkHandler = new Handler();

			}
		};

		handlerThread.start();
	}

	private static void checkNotNull(Object obj, String msg) {
		if (obj == null){
			throw new NullPointerException(msg);
		}

	}

	private static void checkNotNull(Object obj) {
		if (obj == null) {
			throw new NullPointerException("you must init the util in your application");
		}
	}

	public static Context getContext() {
		checkNotNull(sContext);
		return sContext;
	}

	public static Handler getHandler() {
		checkNotNull(sHandler);
		return sHandler;
	}

	/**
	 * work thread -->  main thread
	 * @param r
	 */
	public static void post(Runnable r) {
		checkNotNull(sHandler);
		sHandler.post(r);

	}

	/**
	 * 延时发送
	 * work thread -->  main thread
	 * @param r
	 * @param delay
	 */
	public static void postDelayed(Runnable r, long delay) {
		checkNotNull(sHandler);
		sHandler.postDelayed(r, delay);
	}

	/**
	 *  main thread-->  work thread
	 * @param r
	 */
	public static void postWork(Runnable r) {
		checkNotNull(sHandler);
		sWorkHandler.post(r);

	}

	/**
	 * 延时发送
	 * main thread-->  work thread
	 * @param r
	 * @param delay
	 */
	public static void postWorkDelayed(Runnable r, long delay) {
		checkNotNull(sHandler);
		sWorkHandler.postDelayed(r, delay);
	}

	/**
	 * 移除消息队列中的某个任务
	 * @param r
	 */
	public static void removeWork(Runnable r) {
		checkNotNull(sHandler);
		sWorkHandler.removeCallbacks(r);

	}

	/**
	 * 移除消息队列中的某个任务
	 * @param r
	 */
	public static void remove(Runnable r) {
		checkNotNull(sHandler);
		sHandler.removeCallbacks(r);

	}

	/**
	 * 移除消息队列中的所有的任务
	 */
	public static void remove() {
		checkNotNull(sHandler);
		sHandler.removeCallbacksAndMessages(null);
		sWorkHandler.removeCallbacksAndMessages(null);

	}




}
