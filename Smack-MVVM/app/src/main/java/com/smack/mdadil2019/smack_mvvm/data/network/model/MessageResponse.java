package com.smack.mdadil2019.smack_mvvm.data.network.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class MessageResponse {



    @Expose
    @SerializedName("messageBody")
    private String messageBody;

    @Expose
    @SerializedName("channelId")
    private String channelId;

    @Expose
    @SerializedName("userName")
    private String userName;

    @Expose
    @SerializedName("userAvatar")
    private String userAvatar;

    @Expose
    @SerializedName("userAvatarColor")
    private String avatarColor;

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("timeStamp")

    @NonNull
    @PrimaryKey
    private String timeStamp;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getAvatarColor() {
        return avatarColor;
    }

    public void setAvatarColor(String avatarColor) {
        this.avatarColor = avatarColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
