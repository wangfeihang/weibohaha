package com.example.administrator.weibo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.administrator.weibo.R;
import com.example.administrator.weibo.entity.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/4/6.
 */
public class MessageListAdpater  extends MyBaseAdapter {
    private List<Message> mMessageList;
    private TextView tvUserName;
    private TextView tvMessage;
    private TextView tvCard;
    private Context mContext;
    private ImageButton imCardArrow;
    private ImageButton imAvatar;
    public MessageListAdpater(Context context, OnItemClickListener onClickListener,
                             OnItemLongClickListener onLongClickListener) {
        this.mMessageList=new ArrayList<Message>();
        this.mContext = context;
        this.mOnClickListener = onClickListener;
        this.mOnLongClickListener = onLongClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_messagelist, parent, false);
        MyViewHolder vh = new MyViewHolder(v, mOnClickListener,
                mOnLongClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        View v=holder.v;
        initViews(v,position);
        initData(mMessageList, position);
        v.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mMessageList.size()+3;
    }

    private void initViews(View v,int position) {
        tvUserName=(TextView)v.findViewById(R.id.tv_username);
        tvMessage=(TextView)v.findViewById(R.id.tv_message);
        tvCard=(TextView)v.findViewById(R.id.tv_card);
        imCardArrow=(ImageButton)v.findViewById(R.id.im_message_card_arrow);
        imAvatar=(ImageButton)v.findViewById(R.id.im_avatar);
        switch (position)
        {
            case 0:
            case 1:
            case 2:
                tvUserName.setVisibility(View.INVISIBLE);
                tvMessage.setVisibility(View.INVISIBLE);
                break;
            default:
                imCardArrow.setVisibility(View.INVISIBLE);
                tvCard.setVisibility(View.INVISIBLE);
        }
    }
    private void initData(List<Message> messageList,int position) {
        switch (position)
        {
            case 0:
                tvCard.setText("@我的");
                imAvatar.setBackgroundResource(R.drawable.messagescenter_at);
                break;
            case 1:
                tvCard.setText("评论");
                imAvatar.setBackgroundResource(R.drawable.messagescenter_comments);
                break;
            case 2:
                tvCard.setText("赞");
                imAvatar.setBackgroundResource(R.drawable.messagescenter_good);
                break;
            default:
                tvMessage.setText(messageList.get(position-3).getText());
                tvUserName.setText(messageList.get(position-3).getSenderScreenName());
        }
    }
    public void setData(List<Message> messageList) {
    //    Log.d("statusList.size", String.format("%d", statusList.size()));
        this.mMessageList=messageList;
    }
}
