package com.cnbot.kgrobot.http.api;


import com.cnbot.dchttpsdk.bean.BaseBean;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Copyright (c) 2016-a  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin http请求接口
 * @FileName: IHttpInterface.java
 * @author: dc
 * @date: 2019/1/18 11:13
 * @version: 1.0
 */

public interface IHttpInterface {


    /**
     * 根据机场起始地机场查询航班(今日航班)
     * http://192.168.40.30:8081/airportrobot/rest/flt2/getByPlaceCond?pageNo=1&pageSize=10&airportCode=CKG&aord=A&queryDate=2019-01-15
     * @param hardwareSn （机器硬件编码）
     * @param pageNo 页数
     * @param pageSize 每页数据数
     * @param airportCode
     * @param aord
     * @param queryDate
     * @return
     */
    @POST("rest/flt/getByPlaceCond")
    Observable<BaseBean> getByPlaceCond(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("pageNo") int pageNo, @Query("pageSize") int pageSize, @Query("airportCode") String airportCode
            , @Query("aord") String aord, @Query("queryDate") String queryDate);


    /**
     * 根据航班号查询
     * http://192.168.40.30:8081/airportrobot/rest/flt2/getByFltNo
     * @param hardwareSn （机器硬件编码）
     * @param fltNo  航班号（支持模糊查询）
     * @param queryDate 查询的航班日期（只支持三天内的查询：昨天、今天、明天）
     * @param aord 航班进出港标识(A或D，A为进港，D为出港)
     * @return
     */
    @POST("rest/flt/getByFltNo")
    Observable<BaseBean> getByFltNo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("fltNo") String fltNo,
                                    @Query("aord") String aord, @Query("queryDate") String queryDate);


    /**
     * 获取航班详情
     * 访问URL	http://192.168.40.30:8081/airportrobot/rest/flt/getFlightDetail
     * @param hardwareSn （机器硬件编码）
     * @param fltId  航班唯一标识
     * @param aord 航班进出港标识，A表示进港、D表示出港
     * @return
     */
    @POST("rest/flt/getFlightDetail")
    Observable<BaseBean> getFlightDetailInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("ffid") String fltId,
                                             @Query("aord") String aord);

    /**
     * 获取机场基础数据
     * http://192.168.40.30:8081/airportrobot/rest/flt2/basAirport
     * @param hardwareSn （机器硬件编码）
     * @return
     */
    @GET("rest/flt/basAirport")
    Observable<BaseBean> getBasAirport(@Header("X-AUTH-TOKEN") String hardwareSn);

    /**
     * 获取基础航空公司数据
     * http://192.168.40.30:8081/airportrobot/rest/flt2/getBaseAirline
     * @param hardwareSn （机器硬件编码）
     * @return
     */
    @GET("rest/flt/getBaseAirline")
    Observable<BaseBean> getBaseAirline(@Header("X-AUTH-TOKEN") String hardwareSn);

    /**
     * 获取热门机场
     * 访问URL	/airportrobot/rest/flt/getHotAirport
     * @param hardwareSn （机器硬件编码）
     * @return
     */
    @GET("rest/flt/getHotAirport")
    Observable<BaseBean> getHotAirport(@Header("X-AUTH-TOKEN") String hardwareSn);


    /**
     * 获取热门航空公司
     * 访问URL	/airportrobot/rest/flt/getHotAirline
     * @param hardwareSn （机器硬件编码）
     * @return
     */
    @GET("rest/flt/getHotAirline")
    Observable<BaseBean> getHotAirline(@Header("X-AUTH-TOKEN") String hardwareSn);


    /**
     * 行李查询
     * http://192.168.40.30:8081/airportrobot/rest/storage/getStorage
     * @param hardwareSn （机器硬件编码）
     * @param requestBody
     * @return
     */
    @POST("rest/storage/getStorage")
    Observable<BaseBean> getStorageInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody requestBody);



    /**
     * 机场交通大巴车线路查询
     * http://192.168.40.30:8081/airportrobot/rest/traffic/getTrafficBusRoute
     * @param hardwareSn （机器硬件编码）
     * @param routeName 线路名，支持模糊搜索
     * @param routCode 线路编码
     * @return
     */
    @POST("rest/traffic/getTrafficBusRoute")
    Observable<BaseBean> getTrafficBusRouteInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("routeName") String routeName, @Query("routCode") String routCode);


    /**
     * 机场交通轨道基础线路查询
     * 访问URL	/airportrobot/rest/traffic/getTrackLine
     * @param hardwareSn （机器硬件编码）
     * @param lineName 线路中文名称
     * @param lineCode 大兴线//线路编码
     * @return
     */
    @POST("rest/traffic/getTrackLine")
    Observable<BaseBean> getTrackLineInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("lineName") String lineName, @Query("lineCode") String lineCode);




    /**
     * 机场交通停车场实时车位数量查询
     * http://192.168.40.30:8081/airportrobot/rest/traffic/getParkData
     * @param hardwareSn （机器硬件编码）
     * @param parkingCode  停车场编号
     * @return
     */
    @POST("rest/traffic/getParkData")
    Observable<BaseBean> getParkDataInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("parkingCode") String parkingCode);



    /**
     * 机场交通大巴车站点查询
     * http://192.168.40.30:8081/airportrobot/rest/traffic/getTrafficBusStation
     * @param hardwareSn （机器硬件编码）
     * @param stationName  站点名称
     * @param routCode 站点编号
     * @return
     */
    @POST("rest/traffic/getTrafficBusStation")
    Observable<BaseBean> getTrafficBusStationInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("stationName") String stationName, @Query("routCode") String routCode);

    /**
     * 机场交通高铁班次查询
     * http://192.168.40.30:8081/airportrobot/rest/traffic/getSchedule
     * @param hardwareSn （机器硬件编码）
     * @param lineName   线路中文名称
     * @param lineCode  站点编码
     * @return
     */
    @POST("rest/traffic/getSchedule")
    Observable<BaseBean> getScheduleInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("lineName") String lineName, @Query("lineCode") String lineCode);

    /**
     * 机场交通地铁班次查询
     * http://192.168.40.30:8081/airportrobot/rest/traffic/getSubway
     */
    @POST("rest/traffic/getSubway")
    Observable<BaseBean> getSubway(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("scheduleCode") String scheduleCode, @Query("lineCode") String lineCode);


    /**
     * 获取基础建筑物楼层列表
     * http://192.168.40.30:8081/airportrobot/rest/business/getFloorAndArea
     * @param hardwareSn （机器硬件编码）
     * @param menuId   菜单类型
     * @return
     */
    @POST("rest/business/getFloorAndArea")
    Observable<BaseBean> getFloorAndAreaInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("stationName") String menuId);


    /**
     * 获取店铺信息查询
     * 访问URL	/airportrobot/rest/business/shopinfo
     * @param hardwareSn （机器硬件编码）
     * @param requestBody 请求json
     * @return
     */
    @POST("rest/business/shopinfo")
    Observable<BaseBean> getShopinfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody requestBody);

    /**
     * 失物查询
     * http://192.168.40.30:8081/airportrobot/rest/lost/queryLostAndFound?receiveDate1=2019-01-15&receiveDate2=2019-01-15&pageNo=1&pageSize=10&lostType=A&lostBuild=T3
     * @param hardwareSn （机器硬件编码）
     * @param receiveDate1 登记起始日期
     * @param receiveDate2 登记截止日期
     * @param pageNo    当前页
     * @param pageSize 页面大小
     * @param lostType 丢失物品类别
     * @param lostBuild 拾取地所在建筑物编号
     * @return
     */
    @GET("rest/lost/queryLostAndFound")
    Observable<BaseBean> queryLostAndFoundInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("receiveDate1") String receiveDate1, @Query("receiveDate2") String receiveDate2, @Query("pageNo") int pageNo
            , @Query("pageSize") int pageSize, @Query("lostType") String lostType, @Query("lostBuild") String lostBuild);


    /**
     * 活体检测
     * http://192.168.40.30:8081/airportrobot/rest/cnbotsRAppApi/checkLiveness'
     * @param hardwareSn
     * @param body
     * @return
     */
    @POST("rest/face/app/checkLiveness")
    Observable<BaseBean> checkLivenessData(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody body);


    /**
     * 搜索人脸
     * http://192.168.40.30:8081/airportrobot/rest/cnbotsRAppApi/searchFace?robotsId=123456
     * @param hardwareSn
     * @param robotsId
     * @param body
     * @return
     */
    @POST("rest/face/app/searchFace")
    Observable<BaseBean> searchFace(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("robotsId") String robotsId, @Body RequestBody body);


    /**
     * 身份证识别
     * http://192.168.40.30:8081/airportrobot/rest/cnbotsRAppApi/getIdentityInfo
     * @param hardwareSn
     * @param body
     * @return
     */
    @POST("rest/ocr/app/getIdentityInfo")
    Observable<BaseBean> getIdentityInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody body);

    /**
     * 登录
     * @param suuid
     * @return
     */
    @POST("rest/robot/app/login")
    Observable<BaseBean> login(@Query("suuid") String suuid);


    /**
     * 智能问答
     * /airportrobot/rest/customer/customerAsk
     * @param hardwareSn
     * @param askMsg
     * @return
     */
//    @POST("rest/customer/customerAsk")
//    Observable<BaseBean> customerAsk(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("askMsg") String askMsg);

    /**
     * 智能问答
     * /airportrobot/rest/customer/customerAsk
     * @param hardwareSn
     * @param askMsg
     * @return
     */
    @POST("rest/customer/baidu")
    Observable<BaseBean> customerAsk(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("robotsId") String robotsId, @Query("askMsg") String askMsg);

    /**
     * 获取高级设置密码
     * /rest/cnbotsRAppApi/getSetPwd
     * @param hardwareSn
     * @return
     */
    @GET("rest/robot/app/getSetPwd")
    Observable<BaseBean> getSetPwd(@Header("X-AUTH-TOKEN") String hardwareSn);

    /**
     * 平板获取机器人配置
     * http://192.168.40.30:8081/airportrobot/rest/config/app/getConfig
     * @param hardwareSn
     * @return
     */
    @POST("rest/config/app/getConfig")
    Observable<BaseBean> getConfig(@Header("X-AUTH-TOKEN") String hardwareSn);

    /**
     * 平板更新机器人配置
     * POST /rest/config/app/updateConfig
     * @param hardwareSn
     * @return
     */
    @POST("rest/config/app/updateConfig")
    Observable<BaseBean> updateConfig(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody requestBody);


    /**
     * POST /rest/map/app/saveMap
     * 平板保存地图 /airportrobot/rest/map/app/saveMap
     * @param hardwareSn
     * @param body
     * @return
     */
    @POST("rest/map/app/saveMap")
    Observable<BaseBean> saveMap(@Header("X-AUTH-TOKEN") String hardwareSn, @Body RequestBody body);

    /**
     * 获取SLAM地图
     * /airportrobot/rest/map/app/getMaps
     * @param hardwareSn
     * @param updateTime 更新时间
     * @return
     */
    @POST("rest/map/app/getMaps")
    Observable<BaseBean> getMaps(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("updateTime") String updateTime);

    /**
     * 上传机器日志
     * 访问URL	/airportrobot/rest/log/app/uploadLog
     * @param hardwareSn
     * @param body
     * @param logType
     * @return
     */
    @POST("rest/log/app/uploadLog")
    Observable<BaseBean> uploadLog(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("logType") String logType, @Body RequestBody body);

    /**
     * 上传机器日志(多文件上传）
     * 访问URL	/airportrobot/rest/log/app/uploadLog
     * @param hardwareSn
     * @param body
     * @param logType
     * @return
     */
    @POST("rest/log/app/uploadLog")
    Observable<BaseBean> uploadMultipartLog(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("logType") String logType, @Body MultipartBody body);






    /**
     * POST /rest/version/app/checkUpdate
     * 检查版本升级
     * http://192.168.40.30:8081/airportrobot/rest/version/app/checkUpdate?appName=com.cnbot.airport&versionCode=1
     * @param hardwareSn
     * @param appName
     * @param versionCode
     * @return
     */
    @POST("rest/version/app/checkUpdate")
    Observable<BaseBean> checkUpdate(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("appName") String appName, @Query("versionCode") String versionCode);


    /**
     *  POST /rest/weather/getWeather
     *  获取天气
     *  http://192.168.40.30:8081/airportrobot/rest/weather/getWeather?airportCode=PEK&type=1
     * @param hardwareSn
     * @param airportCode
     * @param type
     * @return
     */
    @POST("rest/weather/getWeather")
    Observable<BaseBean> getWeather(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("airportCode") String airportCode, @Query("type") String type);

    /**
     *  GET /rest/media/getMediaList
     *  获取音视频列表
     *  http://192.168.40.30:8081/airportrobot/rest/media/getMediaList
     * @param hardwareSn
     * @return
     */
    @GET("rest/media/getMediaList")
    Observable<BaseBean> getMediaList(@Header("X-AUTH-TOKEN") String hardwareSn);


    /**
     *  /airportrobot/rest/flt/getPassengerInfo
     *  旅客信息查询
     *  /airportrobot/rest/flt/getPassengerInfo
     * @param hardwareSn
     * @param idNO 证件号码
     * @param idType 证件类型（PP：护照，NI：身份证，ID：其它）
     * @return
     */
    @POST("rest/flt/getPassengerInfo")
    Observable<BaseBean> getPassengerInfo(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("idNO") String idNO, @Query("idType") String idType);


    /**
     * 下载文件
     * @param filePath
     * @return
     */
    @Streaming
    @GET
    Observable<ResponseBody> downloadApk(@Url String filePath);

    /**
     * 获取首页提示语
     * http://192.168.40.30:8081/airportrobot/rest/robot/app/getPromptList
     * @param hardwareSn 硬件编码（robotId）
     * @param scenes 场景（暂用normal）
     * @param size 需要返回的提示语条数（一般为6）
     * @return
     */
    @POST("rest/robot/app/getPromptList")
    Observable<BaseBean> getHomeTips(@Header("X-AUTH-TOKEN") String hardwareSn, @Query("scenes") String scenes, @Query("size") String size);

    /**
     * 上报扫描到的数据
     */
    @POST("rest/checker/app/uploadInventoryData")
    Observable<BaseBean> uploadInventoryData(@Header("X-AUTH-TOKEN") String hardwareSn,
                                             @Body RequestBody body);

    /**
     * 结束巡检
     */
    @POST("rest/checker/app/checkerTaskEnd")
    Observable<BaseBean> checkerTaskEnd(@Header("X-AUTH-TOKEN") String hardwareSn,
                                        @Body RequestBody body);


}
