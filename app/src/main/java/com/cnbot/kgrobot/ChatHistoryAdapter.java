package com.cnbot.kgrobot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnbot.kgrobot.bean.Msg;
import com.cnbot.kgrobot.voice.adapter.ChatHolder;
import com.cnbot.kgrobot.voice.adapter.TextHolder;
import com.cnbot.kgrobot.voice.adapter.UserHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by deng jia on 2018/7/25.
 */

public class ChatHistoryAdapter extends RecyclerView.Adapter<ChatHolder>{
    //定义变量
    private Context mContext;
    private List<Msg> mHistory = new ArrayList<>();

    public ChatHistoryAdapter(Context context, List<Msg> history) {
        mContext = context;
        mHistory = history;
    }
    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {
            return new UserHolder(LayoutInflater.from(mContext).inflate(R.layout.item_ai_user_chat, parent, false));
//            textView.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
//            textView.setTextColor(mContext.getResources().getColor(android.R.color.holo_green_light));
        } else {
            return new TextHolder(LayoutInflater.from(mContext).inflate(R.layout.item_ai_speech_chat_text, parent, false));
//            textView.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
//            textView.setTextColor(mContext.getResources().getColor(android.R.color.black));
        }
//        return new ChatHistoryItem(textView);
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        Msg msg = mHistory.get(position);
//        holder.tvTime.setText(msg.);
        switch (msg.getType()) {
            case Msg.INPUT_TYPE:
                holder.tvContent.setText(msg.getMessage());
            case Msg.OUTPUT_TYPE:

                holder.tvContent.setText(msg.getMessage());

                break;

        }
    }

    @Override
    public int getItemViewType(int position) {
        return mHistory.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return mHistory.size();
    }

    class ChatHistoryItem extends RecyclerView.ViewHolder {
        TextView textView;

        public ChatHistoryItem(TextView itemView) {
            super(itemView);
            this.textView = itemView;
        }
    }

    /**
     * 在列表末尾加入元素
     *
     * @param t
     */
    public void insertEnd(Msg t) {
        insert(getItemCount(), t);
    }

    /**
     * 在某个item位置 添加单个数据
     *
     * @param position 单个数据的位置
     * @param t        例如，添加到第2个item的位置，position就是2，而list.add(2,object)）
     */
    public void insert(int position, Msg t) {
        mHistory.add(position, t);
        notifyItemInserted(position);
    }
}
