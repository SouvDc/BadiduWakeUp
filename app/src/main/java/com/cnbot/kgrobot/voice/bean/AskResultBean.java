package com.cnbot.kgrobot.voice.bean;

/**
 * Copyright (c) 2016--2019/4/12  Hunan Cnbot Co., Ltd. All Rights Reserved.
 *
 * @descriptoin 智慧图返回语义分析数据内容，可能需要修改
 * @FileName: AskResultBean.java
 * @author: dc
 * @date: 2019/4/12 11:28
 * @version: 1.0
 */

public class AskResultBean {

    /**
     * action : flt
     * text : 航班动态查询
     * url : /WECHAT-PKX/standard/official/flight/portal.html
     * textArea : 小兴推荐您使用$1
     */

    private String action;
    private String text;
    private String url;
    private String textArea;

    private String content;

    public String getAction() {
        return action == null ? "" : action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getText() {
        return text == null ? "无返回内容" : text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url == null ? "" : url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // TODO: 2019/4/12 此处获取数据内容修改，之后数据全了之后看是否可以去掉;

    public String getTextArea() {
        return textArea == null ? getText() : textArea;
    }

    public void setTextArea(String textArea) {
        this.textArea = textArea;
    }

    public String getContent() {
        return content == null ? getTextArea() : content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
