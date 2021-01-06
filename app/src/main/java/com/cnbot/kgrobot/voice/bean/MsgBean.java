package com.cnbot.kgrobot.voice.bean;


/**
 * Created by deng jia on 2018/7/25.
 * @author deng jia
 */

public class MsgBean {
	public static final int TEXT_CARD = 0;
	public static final int STANDARD_CARD = 1;
	public static final int LIST_CARD = 2;
	public static final int IMAGE_LIST_CARD = 3;
	public static final int INPUT_TYPE = 4;
	public static final int OUTPUT_TYPE = 5;
	/**
	 * 音频播放
	 */
	public static final int AUDIO_PLAYER = 6;
	private String content;
	/**
	 * 数据结构类型
	 */
	private int type;
	/**
	 * 时间
	 */
	private String chatTime;

	/**
	 * output
	 * @param ctx
	 */
	public MsgBean(String ctx) {
		this.content = ctx;
	}

	/**
	 * input
	 * @param ctx
	 * @param t
	 */
	public MsgBean(String ctx, int t) {
		this.content = ctx;
		this.type = t;
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getContent() {
		return content == null ? "" : content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getChatTime() {
		return chatTime == null ? "" : chatTime;
	}

	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}


}
