package me.daylight.talk.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = "phone")},
        foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "phone",childColumns = "phone")})
public class FriendList {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String phone;
    private String friendPhone;
    //好友状态码   -100:等待通过 -200:已拒绝  100:已通过
    private long state;
    private long time;

    @Ignore
    public static final int WAIT=-100;
    @Ignore
    public static final int ACCEPT=100;

    public FriendList() {
    }

    @Ignore
    public FriendList(String phone, String friendPhone, long state) {
        this.phone = phone;
        this.friendPhone = friendPhone;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getFriendPhone() {
        return friendPhone;
    }

    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }


    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
