package com.cnbot.kgrobot;

import android.app.Application;

import com.cnbot.dchttpsdk.api.ApiEngine;
import com.cnbot.dchttpsdk.utils.NetUtil;
import com.cnbot.kgrobot.helper.AppHelper;
import com.cnbot.kgrobot.http.api.IHttpInterface;
import com.cnbot.kgrobot.utils.Toaster;

import static com.cnbot.kgrobot.constant.HttpConstant.HTTPSERVICEIP;
import static com.cnbot.kgrobot.constant.RobotConstant.BASE_PATH;

/**
 * 描述：
 * 作者：dc on 2020/12/7 11:02
 * 邮箱：597210600@qq.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppHelper.init(this.getApplicationContext());
        //吐司初始化
        Toaster.init(getApplicationContext());
        initHTTP();
    }

    private void initHTTP() {
        //网络请求
        new ApiEngine.Builder<IHttpInterface>().baseUrl(HTTPSERVICEIP, true, BASE_PATH).apiService(IHttpInterface.class).build();
        NetUtil.init(this);
    }
}