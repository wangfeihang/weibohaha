package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Status implements Serializable {
    private static final long serialVersionUID = -6919461967497580385L;
    private String text;
    private String source;
    private User user;
    private Long id;
    @SerializedName("created_at")
    private String createTime;//微博创建时间
    private Long mid;//微博MID
    private String idstr;//字符串型的微博ID
    private boolean favorited;//是否已收藏，true：是，false：否
    private boolean truncated;//是否被截断，true：是，false：否
    @SerializedName("in_reply_to_status_id")
    private String replytoStatusID;//（暂未支持）回复ID
    @SerializedName("in_reply_to_user_id")
    private String replytoUserID;//（暂未支持）回复人UID
    @SerializedName("in_reply_to_screen_name")
    private String replytoScreenName;//（暂未支持）回复人昵称
    @SerializedName("thumbnail_pic")
    private String thumbnailPic;//缩略图片地址，没有时不返回此字段
    @SerializedName("bmiddle_pic")
    private String bmiddlePic;//中等尺寸图片地址，没有时不返回此字段
    @SerializedName("original_pic")
    private String originalPic;//原始图片地址，没有时不返回此字段 @SerializedName("pic_ids")
    private List<String> picIDs;//微博配图ID。多图时返回多图ID，用来拼接图片url。用返回字段thumbnail_pic的地址配上该返回字段的图片ID，即可得到多个图片url。

    @SerializedName("reposts_count")
    private int repostsCount;
    @SerializedName("comments_count")
    private int commentsCount;
    @SerializedName("attitudes_count")
    private int attitudesCount;

    public String getText() {
        return text;
    }

    public String getSource() {
        return source;
    }

    public User getUser() {
        return user;
    }

    public int getRepostsCount() {
        return repostsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public int getAttitudesCount() {
        return attitudesCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRepostsCount(int repostsCount) {
        this.repostsCount = repostsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public void setAttitudesCount(int attitudesCount) {
        this.attitudesCount = attitudesCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCreateTime() {
        return createTime;
    }

    public Long getMid() {
        return mid;
    }

    public String getIdstr() {
        return idstr;
    }

    public boolean isFavorited() {
        return favorited;
    }

    public boolean isTruncated() {
        return truncated;
    }

    public String getReplytoStatusID() {
        return replytoStatusID;
    }

    public String getReplytoUserID() {
        return replytoUserID;
    }

    public String getReplytoScreenName() {
        return replytoScreenName;
    }

    public String getThumbnailPic() {
        return thumbnailPic;
    }

    public String getBmiddlePic() {
        return bmiddlePic;
    }

    public String getOriginalPic() {
        return originalPic;
    }



    public List<String> getPicIDs() {
        return picIDs;
    }



    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public void setTruncated(boolean truncated) {
        this.truncated = truncated;
    }

    public void setReplytoStatusID(String replytoStatusID) {
        this.replytoStatusID = replytoStatusID;
    }

    public void setReplytoUserID(String replytoUserID) {
        this.replytoUserID = replytoUserID;
    }

    public void setReplytoScreenName(String replytoScreenName) {
        this.replytoScreenName = replytoScreenName;
    }

    public void setThumbnailPic(String thumbnailPic) {
        this.thumbnailPic = thumbnailPic;
    }

    public void setBmiddlePic(String bmiddlePic) {
        this.bmiddlePic = bmiddlePic;
    }

    public void setOriginalPic(String originalPic) {
        this.originalPic = originalPic;
    }
    public void setPicIDs(List<String> picIDs) {
        this.picIDs=picIDs;
    }


    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(text);
        dest.writeString(source);
        dest.writeValue(user);
        dest.writeInt(repostsCount);
        dest.writeInt(commentsCount);
        dest.writeInt(attitudesCount);
    }
    public static final Parcelable.Creator<Status> CREATOR = new Creator<Status>() {

        @Override
        public Status createFromParcel(Parcel source) {
            Status police = new Status();
            police.id = source.re();
            police.workTime = source.readInt();
            return police;
        }

        @Override
        public Status[] newArray(int size) {
            return new Status[size];
        }
    };
    */
}
