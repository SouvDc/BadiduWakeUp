package com.cnbot.kgrobot.bean;

/**
 * 描述：
 * 作者：dc on 2020/12/8 20:16
 * 邮箱：597210600@qq.com
 */
public class Voice {

    /**
     * type : 8
     * content : 湿垃圾包括剩菜剩饭、骨头、菜根菜叶、果皮等食品类废物。湿垃圾经生物技术就地处理堆肥，每吨可生产0.6~0.7吨有机肥料。
     * similarity : 1
     */

    private int type;
    private String content;
    private int similarity;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSimilarity() {
        return similarity;
    }

    public void setSimilarity(int similarity) {
        this.similarity = similarity;
    }
}
