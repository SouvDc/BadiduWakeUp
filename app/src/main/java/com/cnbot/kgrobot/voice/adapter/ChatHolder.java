package com.cnbot.kgrobot.voice.adapter;

import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnbot.kgrobot.R;

import androidx.recyclerview.widget.RecyclerView;


/**
 * @author :   ww
 * @项目名： EcologyRobot4
 * @包名： com.cnbot.ecologyrobot2.emotion.model
 * @文件名: ChatHolder
 * @创建时间: 2019/1/24 16:47
 * @描述： 闲聊适配器ViewHolder基类
 */
public class ChatHolder extends RecyclerView.ViewHolder {

    public TextView tvTime;
    public TextView tvContent;
    public ImageView ivImg;
    public WebView webView;
    public TextView tvTitle;
    public ImageView ivImgStandard;
    public TextView tvDes;
    public ImageView ivImgPlayer;
    public TextView tvTitlePlayer;

    public ChatHolder(View itemView) {
        super(itemView);
        tvTime = itemView.findViewById(R.id.tv_time_chat);

    }
}
