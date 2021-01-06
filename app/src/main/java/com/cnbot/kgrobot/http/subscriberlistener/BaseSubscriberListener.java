package com.cnbot.kgrobot.http.subscriberlistener;

import com.cnbot.dchttpsdk.bean.BaseBean;
import com.cnbot.dchttpsdk.constant.HttpCode;
import com.cnbot.dchttpsdk.helper.OnSubscriberListener;

/**
 * 该类到时候代替OnSubscriberListener接口.
 *
 * 统一判断HTTP请求登录超时.
 *
 * 暂时未启用接口为统一
 * @author dc
 * @param <T>
 */
public abstract class BaseSubscriberListener<T> implements OnSubscriberListener<T> {
    @Override
    public void onNext(T o) {
        BaseBean baseBean = (BaseBean) o;
        if(baseBean != null && baseBean.getCode().equals(HttpCode.EXPIRE)){
        }
    }
}
