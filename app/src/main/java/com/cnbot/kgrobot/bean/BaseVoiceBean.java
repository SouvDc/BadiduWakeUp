package com.cnbot.kgrobot.bean;

/**
 * Copyright (c) 2016--2019/4/12  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 语音返回结果实体
 * @FileName: BaseVoiceBean.java
 * @author: dc
 * @date: 2019/4/12 15:22
 * @version: 1.0
 */

public class BaseVoiceBean {

    /**
     * action : flt
     * type : 101
     * map : [{"ffid":"MU211A-2019-07-06-D-D","fltNo":"MU211A","sdtTime":"2019-05-31 06:50:00","mDori":"D","fltId":1410488445,"fltShareNo":"","fltAllNo":"MU211A,","fltDate":"2019-07-06","dori":"D","aord":"D","iata":"MU","airlineName":"东方航空","planeRegno":"MU003","planeType":"A330","taskType":"正班","allRoute":"PKX-XIY","orig":"PKX","depAirportName":"大兴机场","depAirportCity":"","depCityWeather":"","depTemperature":"","depSchTime":"2019-07-06 18:40:00","depEstTime":"2019-07-06 18:40:00","depActTime":"","depTerm":"T1","depPark":"","dest":"XIY","arrAirportName":"西安","arrAirportCity":"西安","arrCityWeather":"晴","arrTemperature":"11","arrSchTime":"","arrEstTime":"","arrActTime":"","arrTerm":"","arrPark":"","fltAbnStatusCode":"","fltAbnStatusDesc":"","route":"PKX-XIY","passRoute":"","counterDisp":"B01 - B32","gateDisp":"A11","beltDisp":"","chtmEtd":"2019-07-06 18:40:00","boardingStartTime":"","boardingEndTime":"","urgeBoardingTime":"","ckiStatus":"P","gateStatus":"P","bagtStatus":"","preAirportSdt":"","preAirportEst":"","preAirportAct":"","nextAirportSdt":"","nextAirportEst":"","nextAirportAct":"","startAirportDepTime":"","destAirportArrTime":"","airlineList":[{"iata":"MU","airlineName":"东方航空","fltNo":"MU211A"}],"airportRoadStatus":"","securityWaitTime":"","onTime":"","status":"计划"}]
     */

    private String action;
    private int type;
    private String content;
    private String item;
    public Object getMap() {
        return map;
    }

    public void setMap(Object map) {
        this.map = map;
    }

    private Object map;

    public BaseVoiceBean(int typeNotData, String s, String action) {
        this.type = typeNotData;
        this.content = s;
        this.action = action;
    }

    public String getContent() {
        return content == null ? "" : content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAction() {
        return action == null ? "" : action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getItem() {
        return item == null ? "" : item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
