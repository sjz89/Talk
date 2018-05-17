package me.daylight.talk.presenter;

import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import me.daylight.talk.customview.CommonItemView;
import me.daylight.talk.customview.GroupListView;
import me.daylight.talk.model.FunctionModel;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.view.FunctionView;

public class FunctionPresenter extends BasePresenter<FunctionView,FunctionModel> {
    public void initGroupListView(GroupListView groupListView){
        CommonItemView userInfoItem=new CommonItemView(getView().getCurContext(),QMUICommonListItemView.VERTICAL,
                QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);

        getModel().getUser().observe(getView().getCurContext(),user -> {
            if (user!=null){
                userInfoItem.setImageDrawable(GlobalField.url+"user/headImage/"+user.getPhone()+"?time="+
                        user.getUpdatetime(),56,56);
                userInfoItem.setText(user.getNicname());
                userInfoItem.setDetailText("手机号:"+user.getPhone());
            }
        });

        GroupListView.newSection(getView().getCurContext())
                .addItemView(userInfoItem,v -> {
                    getModel().setUserPhone();
                    getView().toInfoFragment();
                })
                .addTo(groupListView);
    }
}
