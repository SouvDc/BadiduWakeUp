package com.cnbot.kgrobot.utils;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import androidx.annotation.StringRes;

/**
 * @project KgRobot
 * @author ww
 * @date 2018/5/2
 * @description   支持上、中、下位置的吐司
 * @version 1.0.0
 */

public class Toaster {

	private Toaster() {}

	private static Context sContext;
	private static Toast sToast = null;

	public static void init(Context context) {
		checkContext(context);

	}

	private static void checkContext() {
		if (sContext == null) {
			throw new NullPointerException("you must init toaster in application");
		}
		if (sContext instanceof Activity) {
			sContext = sContext.getApplicationContext();
		}

	}

	private static void checkContext(Context context) {
		if (context == null) {
			throw new NullPointerException("you must init toaster in application");
		}
		if (context instanceof Activity) {
			context = context.getApplicationContext();
		}
		sContext = context;
	}

	public static void showToast(String msg) {

		if (sToast == null) {
			sToast = Toast.makeText(sContext, msg, Toast.LENGTH_SHORT);
			sToast.setGravity(Gravity.CENTER, 0, 0);
		} else {
			sToast.setText(msg);
		}
		sToast.show();
	}

	public static void showBottomToast(String msg) {

		if (sToast == null) {
			sToast = Toast.makeText(sContext, msg, Toast.LENGTH_SHORT);
		}
		sToast.setGravity(Gravity.BOTTOM, 0, 80);
		sToast.setText(msg);
		sToast.show();
	}

	public static void showTopToast(String msg) {

		if (sToast == null) {
			sToast = Toast.makeText(sContext, msg, Toast.LENGTH_SHORT);
		}
		sToast.setGravity(Gravity.TOP, 0, 80);
		sToast.setText(msg);
		sToast.show();
	}

	public static void showCenterToast(String msg) {

		if (sToast == null) {
			sToast = Toast.makeText(sContext, msg, Toast.LENGTH_LONG);
		}
		sToast.setGravity(Gravity.CENTER, 0, 0);
		sToast.setText(msg);
		sToast.show();
	}

	/**
	 * @see #showCenterToast(String) 与它相比只需要传入资源id
	 * @author ww
	 * @param resId
	 */
	public static void showCenterToast(@StringRes int resId) {
		showCenterToast(sContext.getString(resId));
	}

	/**
	 * 顶部显示toast
	 * @param resId
	 */
	public static void showTopToast(@StringRes int resId) {

		showTopToast(sContext.getString(resId));
	}

	/**
	 * 底部显示toast
	 * @param resId
	 */
	public static void showBottomToast(@StringRes int resId) {
		showBottomToast(sContext.getString(resId));
	}
}
