package com.cnbot.kgrobot.http;


/**
 * Copyright (c) 2016--2019/1/18  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *@descriptoin http常量参数
 *@FileName: HttpConstant.java
 *@author: dc
 *@date: 2019/1/18 10:25
 *@version: 1.0
 */
public interface HttpConstant {

     /**
     * 机场itc服务器地址。外网不可访问，机场内网访问
     */
    String ITC_BASE_URL = "10.130.17.111:80";
    /**
     * 正式运行服务器地址。机场外联网区域服务器，外网可访问
     *
     */
    String RELEASE_BASE_URL = "120.246.125.168";

    /**
     * debug运行服务器地址。家里开发服务器，内网访问。
     * 192.168.40.206:8080  test.frp.hncnbot.com      test.guduke.cn
     */
    String DEBUG_BASE_URL = "test.frp.hncnbot.com";


}
