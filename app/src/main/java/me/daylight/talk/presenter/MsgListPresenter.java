package me.daylight.talk.presenter;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import java.util.ArrayList;
import java.util.List;

import me.daylight.talk.adapter.CommonAdapter;
import me.daylight.talk.bean.CommonData;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.model.MsgListModel;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.view.MsgListView;

public class MsgListPresenter extends BasePresenter<MsgListView,MsgListModel> {
    private List<MessageList> messageList;

    public void loadMessageList() {
        CommonAdapter adapter = new CommonAdapter(getView().getCurContext(),
                QMUICommonListItemView.VERTICAL, QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        adapter.setOnCommonItemClickListener((v, position) -> {
            getModel().setFriend(messageList.get(position).getFriendPhone());
            messageList.get(position).cleanMsgNumber();
            getModel().saveMsgList(messageList.get(position));
            getView().toChatFragment();
        });
        getModel().loadMessageList().observe(getView().getCurContext(), list -> {
            if (list != null && list.size() != 0) {
                messageList = list;
                List<CommonData> data = new ArrayList<>();
                for (MessageList message : list) {
                    CommonData commonData = new CommonData(message.getFriendNicName(), message.getLastMsg());
                    commonData.setTime(message.getTime());
                    commonData.setImage(GlobalField.url+"user/headImage/"+message.getFriendPhone()
                            +"?time="+ getModel().getUserUpdateTime(message.getFriendPhone()));
                    commonData.setId(message.getId());
                    commonData.setNumber(message.getMsgNumber());
                    data.add(commonData);
                }
                adapter.setData(data);
            }
        });
        getView().initRecyclerView(adapter);
    }
}
