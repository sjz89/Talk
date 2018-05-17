package me.daylight.talk.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;

import java.util.List;

import me.daylight.talk.bean.MessageList;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.viewmodel.ChatViewModel;
import me.daylight.talk.viewmodel.UserViewModel;

public class MsgListModel extends BaseModel {
    private ChatViewModel chatViewModel;
    private UserViewModel userViewModel;

    @Override
    public void init() {
        chatViewModel= ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(ChatViewModel.class);
        userViewModel=ViewModelProviders.of((QMUIFragmentActivity)getContext()).get(UserViewModel.class);
        chatViewModel.loadMsgList(SharedPreferencesUtil.getString(getContext(), GlobalField.USER, GlobalField.ACCOUNT));
    }

    public LiveData<List<MessageList>> loadMessageList() {
        return chatViewModel.getMessageList();
    }

    public void setFriend(String friendPhone) {
        chatViewModel.setFriend(userViewModel.loadUserByPhone(friendPhone));
    }

    public void saveMsgList(MessageList messageList) {
        chatViewModel.saveMsgList(messageList);
    }

    public long getUserUpdateTime(String phone) {
        return userViewModel.getUserUpdateTime(phone);
    }
}
