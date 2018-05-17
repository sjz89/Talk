package me.daylight.talk.presenter;

import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import java.util.ArrayList;
import java.util.List;

import me.daylight.talk.adapter.CommonAdapter;
import me.daylight.talk.bean.CommonData;
import me.daylight.talk.bean.User;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.model.FriendModel;
import me.daylight.talk.utils.DialogUtil;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.view.FriendView;

public class FriendPresenter extends BasePresenter<FriendView,FriendModel> {
    private List<User> friends;

    public void initFriendList() {
        CommonAdapter adapter = new CommonAdapter(getView().getCurContext(), QMUICommonListItemView.VERTICAL);
        adapter.setOnCommonItemClickListener((v, position) -> {
            getModel().setFriend(friends.get(position));
            getModel().cleanMsgNumber();
            getView().toChatFragment();
        });
        getModel().getFriends().observe(getView().getCurContext(),users -> {
            if (users != null && users.size() != 0) {
                friends = users;
                List<CommonData> data = new ArrayList<>();
                for (User user : users) {
                    CommonData commonData = new CommonData(user.getNicname(), user.getSignature());
                    commonData.setImage(GlobalField.url+"user/headImage/"+user.getPhone()+"?time="+user.getUpdatetime());
                    commonData.setId(user.getId());
                    data.add(commonData);
                }
                adapter.setData(data);
            }
        });
        getModel().loadFriendList(new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {

            }

            @Override
            public void onFailed(String errorMsg) {
                DialogUtil.showTipDialog(getView().getCurContext(), QMUITipDialog.Builder.ICON_TYPE_FAIL,
                        errorMsg,true);
            }
        });
        getView().initRecyclerView(adapter);
    }

    public void swipeToRefresh() {
        getModel().loadFriendList(new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                getView().hideRefresh();
                DialogUtil.showToast(getView().getCurContext(),"刷新成功");
            }

            @Override
            public void onFailed(String errorMsg) {
                DialogUtil.showTipDialog(getView().getCurContext(), QMUITipDialog.Builder.ICON_TYPE_FAIL,
                        errorMsg,true);
            }
        });
    }
}
