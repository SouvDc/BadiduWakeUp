package com.cnbot.kgrobot.constant;

/**
 * Copyright (c) 2016--2019/1/16  Hunan Cnbot Co., Ltd. All Rights Reserved.
 **/

import android.os.Environment;

import java.io.File;

/**
 *@descriptoin 全局的系统常量
 *@FileName: Constant.java
 *@author: dc
 *@date: 2019/1/16 15:15
 *@version: 1.0
 */

public class RobotConstant {

    /**
     * 需要root的dada文件夹
     */
    public static final String DATA = "/data/";
    /**
     *  需要root的dada文件夹
     */
    public static final String DATA_D = "/data/data";

    /**
     * 需要root的设置包名文件夹
     */
    public static final String SET_DATA_DIR = "/data/data/com.android.providers.settings/";

    /**
     * 静态IP数据库路径
     */
    public static final String DB_NAME = "/data/data/com.android.providers.settings/databases/settings.db";

    /**
     * 静态IP数据库路径
     */
    public static final String DB_JOURNAL_NAME = "/data/data/com.android.providers.settings/databases/settings.db-journal";

    /**
     * 根目录
     */
    public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "RefuseRobot" + File.separator;

    /**
     * 人脸和活体检测图片保存路径
     */
    public static final String IMG_PATH = BASE_PATH + "FaceImage";

    /**
     * 地图和升级文件保存路径
     */
    public static final String FILE_PATH = BASE_PATH + "File";

    /**
     * 视觉身份证文件保存路径
     */
    public static final String IDCARD_PATH = BASE_PATH + "Idcard";

    /**
     * 日志保存路径
     */
    public static final String LOG_PATH = BASE_PATH + "Log";

    /**
     * 运行日志
     */
    public static final String RUN_LOG_PATH = BASE_PATH + "zlog";

    /**
     * 身份证文件
     */
    public static final String IDCARD_NAME = "BJtemp.jpg";

    /**
     * 地图文件
     */
    public static final String MAP_NAME = "map.tar";

    /**
     * 升级文件
     */
    public static final String ROBOT_NAME = "devel.tar";

    /**
     * tts离线文件
     */
    public static final String BAIDU_TTS = "TTS_ONLINE";
    /**
     * 地图文件名称（不变）
     */
    public static final String MAP_FILE_PATH_NAME = FILE_PATH +  File.separator + MAP_NAME;

    /**
     * 平板升级包
     */
    public static final String PAD_FILE_PATH_NAME = FILE_PATH +  File.separator + "update.apk";

    /**
     * 主控升级包名称（可能会变）
     */
    public static final String ROBOT_FILE_PATH_NAME = FILE_PATH +  File.separator + ROBOT_NAME;

    /**
     * 身份证拍照图片保存路径
     */
    public static final String ROBOT_IDCARD_PATH_NAME = IDCARD_PATH +  File.separator + IDCARD_NAME;


    /**
     * tts离线文件
     */
    public static final String ROBOT_BAIDU_TTS_NAME = BASE_PATH +  File.separator + BAIDU_TTS;

    /**
     * CRASH日志目录
     */
    public static final String ROBOT_CRASH_PATH = BASE_PATH + "crash";
}
