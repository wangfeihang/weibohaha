package com.example.administrator.weibo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/30.
 */
public class User implements Serializable {
    private static final long serialVersionUID = -6919461967497580385L;

    private Long id;
    private String idstr;
    @SerializedName("screen_name")
    private String screenName;
    private String name;
    private int province;
    private int city;
    private String location;
    private String description;
    private String url;
    @SerializedName("profile_image_url")
    private String profileImageURL;
    @SerializedName("profile_url")
    private String profileURL;
    private String domain;
    private String weihao;
    private String gender;
    @SerializedName("followers_count")
    private int followersCount;
    @SerializedName("friends_count")
    private int friendsCount;
    @SerializedName("statuses_count")
    private int statusesCount;
    @SerializedName("favourites_count")
    private int favouritesCount;
    @SerializedName("created_at")
    private String createdat;
    private boolean following;
    @SerializedName("allow_all_act_msg")
    private boolean allowAllActMsg;
    @SerializedName("geo_enabled")
    private boolean geoEnabled;
    private boolean verified;
    @SerializedName("verified_type")
    private int verifiedType;
    private String remark;
    private Status status;
    @SerializedName("allowAllComment")
    private boolean allow_all_comment;
    @SerializedName("avatar_large")
    private String avatarLarge;
    @SerializedName("avatar_hd")
    private String avatarHd;
    @SerializedName("verified_reason")
    private String verifiedReason;
    @SerializedName("follow_me")
    private boolean followMe;
    @SerializedName("online_status")
    private int onlineStatus;
    @SerializedName("bi_followers_count")
    private int biFollowersCount;
    private String lang;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getIdstr() {
        return idstr;
    }

    public String getScreenName() {
        return screenName;
    }

    public String getName() {
        return name;
    }

    public int getProvince() {
        return province;
    }

    public int getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public String getProfileURL() {
        return profileURL;
    }

    public String getDomain() {
        return domain;
    }

    public String getWeihao() {
        return weihao;
    }

    public String getGender() {
        return gender;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFriendsCount() {
        return friendsCount;
    }

    public int getStatusesCount() {
        return statusesCount;
    }

    public int getFavouritesCount() {
        return favouritesCount;
    }

    public String getCreatedat() {
        return createdat;
    }

    public boolean isFollowing() {
        return following;
    }

    public boolean isAllowAllActMsg() {
        return allowAllActMsg;
    }

    public boolean isGeoEnabled() {
        return geoEnabled;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getVerifiedType() {
        return verifiedType;
    }

    public String getRemark() {
        return remark;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isAllow_all_comment() {
        return allow_all_comment;
    }

    public String getAvatarLarge() {
        return avatarLarge;
    }

    public String getAvatarHd() {
        return avatarHd;
    }

    public String getVerifiedReason() {
        return verifiedReason;
    }

    public boolean isFollowMe() {
        return followMe;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public int getBiFollowersCount() {
        return biFollowersCount;
    }

    public String getLang() {
        return lang;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdstr(String idstr) {
        this.idstr = idstr;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public void setProfileURL(String profileURL) {
        this.profileURL = profileURL;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setWeihao(String weihao) {
        this.weihao = weihao;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public void setFriendsCount(int friendsCount) {
        this.friendsCount = friendsCount;
    }

    public void setStatusesCount(int statusesCount) {
        this.statusesCount = statusesCount;
    }

    public void setFavouritesCount(int favouritesCount) {
        this.favouritesCount = favouritesCount;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public void setAllowAllActMsg(boolean allowAllActMsg) {
        this.allowAllActMsg = allowAllActMsg;
    }

    public void setGeoEnabled(boolean geoEnabled) {
        this.geoEnabled = geoEnabled;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public void setVerifiedType(int verifiedType) {
        this.verifiedType = verifiedType;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setAllow_all_comment(boolean allow_all_comment) {
        this.allow_all_comment = allow_all_comment;
    }

    public void setAvatarLarge(String avatarLarge) {
        this.avatarLarge = avatarLarge;
    }

    public void setAvatarHd(String avatarHd) {
        this.avatarHd = avatarHd;
    }

    public void setVerifiedReason(String verifiedReason) {
        this.verifiedReason = verifiedReason;
    }

    public void setFollowMe(boolean followMe) {
        this.followMe = followMe;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public void setBiFollowersCount(int biFollowersCount) {
        this.biFollowersCount = biFollowersCount;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
