package me.daylight.talk.bean;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(indices = {@Index(value = "phone"),@Index(value = "friendPhone"),@Index(value = "uniqueId",unique = true)},
    foreignKeys = {@ForeignKey(entity = User.class,parentColumns = "phone",childColumns = "phone"),
                    @ForeignKey(entity = User.class,parentColumns = "phone",childColumns = "friendPhone")})
public class MessageList {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String phone;
    private String friendPhone;
    private String friendNicName;
    private long time;
    private String lastMsg;
    private String uniqueId;
    private int msgNumber;

    public long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getFriendPhone() {
        return friendPhone;
    }

    public long getTime() {
        return time;
    }

    public String getLastMsg() {
        return lastMsg;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFriendPhone(String friendPhone) {
        this.friendPhone = friendPhone;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setLastMsg(String lastMsg) {
        this.lastMsg = lastMsg;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFriendNicName() {
        return friendNicName;
    }

    public void setFriendNicName(String friendNicName) {
        this.friendNicName = friendNicName;
    }

    public int getMsgNumber(){
        return msgNumber;
    }

    public void setMsgNumber(int msgNumber) {
        this.msgNumber = msgNumber;
    }

    public void cleanMsgNumber(){
        msgNumber=0;
    }

}
