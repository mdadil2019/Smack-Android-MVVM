package com.smack.mdadil2019.smack_mvvm.adapter;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.anonymanager.mdadil2019.smack_mvvm.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.smack.mdadil2019.smack_mvvm.data.network.model.MessageResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyChatViewHolder> {

    ArrayList<MessageResponse> messages;
    Context mContext;
    public ChatAdapter(ArrayList<MessageResponse> data, Context context){
        messages = data;
        mContext = context;

    }
    @NonNull
    @Override
    public MyChatViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.message_layout,viewGroup,false);
        return new MyChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChatViewHolder holder, int i) {
        holder.userNameTv.setText(messages.get(i).getUserName());
        holder.timeTv.setText(messages.get(i).getTimeStamp());
        holder.messageTv.setText(messages.get(i).getMessageBody());
        if(messages.get(i).getUserAvatar()!=null && !messages.get(i).getUserAvatar().equals("")){

            String avatarPath= "@drawable/" + messages.get(i).getUserAvatar().replace(".png","");

            int avatarRes = mContext.getResources().getIdentifier(avatarPath,null,mContext.getPackageName());
            Drawable drawable = mContext.getResources().getDrawable(avatarRes);
            holder.avatarIv.setImageDrawable(drawable);
        }

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class MyChatViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textViewUserName)
        TextView userNameTv;

        @BindView(R.id.textViewTime)
        TextView timeTv;

        @BindView(R.id.textViewMessage)
        TextView messageTv;

        @BindView(R.id.imageViewMessage)
        CircularImageView avatarIv;
        public MyChatViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
