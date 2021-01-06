package com.cnbot.kgrobot.voice.adapter;

import android.view.View;

import com.cnbot.kgrobot.R;


/**
 * @author :   ww
 * @项目名： EcologyRobot4
 * @包名： com.cnbot.ecologyrobot2.emotion.model
 * @文件名: UserHolder
 * @创建时间: 2019/1/24 16:17
 * @描述： 用户语音输入
 */
public class TextHolder extends ChatHolder {


    public TextHolder(View itemView) {
        super(itemView);

        tvContent = itemView.findViewById(R.id.tv_content_output_text);

    }
}
