package me.daylight.talk.presenter;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.internal.entity.CaptureStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.daylight.talk.R;
import me.daylight.talk.app.GlideApp;
import me.daylight.talk.bean.User;
import me.daylight.talk.fragment.InfoFragment;
import me.daylight.talk.http.OnHttpCallBack;
import me.daylight.talk.http.RetResult;
import me.daylight.talk.model.InfoModel;
import me.daylight.talk.utils.DialogUtil;
import me.daylight.talk.utils.GlideEngine;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.customview.GroupListView;
import me.daylight.talk.view.InfoView;

public class InfoPresenter extends BasePresenter<InfoView,InfoModel> {
    private User user;
    private List<EditText> editTextList;
    private ImageView headView;
    private QMUICommonListItemView gender;

    public void initGroupListView(GroupListView groupListView) {
        user=getModel().loadUserInfo();
        initCustomView();

        QMUICommonListItemView headImage = groupListView.createItemView("头像");
        headImage.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        headImage.addAccessoryCustomView(headView);

        QMUICommonListItemView phone=groupListView.createItemView("手机号码");
        phone.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        phone.setDetailText(user.getPhone());

        QMUICommonListItemView name=groupListView.createItemView("姓名");
        name.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        name.addAccessoryCustomView(editTextList.get(0));

        QMUICommonListItemView nicName=groupListView.createItemView("昵称");
        nicName.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        nicName.addAccessoryCustomView(editTextList.get(1));

        gender=groupListView.createItemView("性别");
        gender.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_NONE);
        switch (user.getGender()){
            case 0:
                gender.setDetailText("请选择");
                break;
            case 1:
                gender.setDetailText("男");
                break;
            case 2:
                gender.setDetailText("女");
                break;
        }

        QMUICommonListItemView signature=groupListView.createItemView("个性签名");
        signature.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        signature.addAccessoryCustomView(editTextList.get(2));

        QMUICommonListItemView address=groupListView.createItemView("地址");
        address.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
        address.addAccessoryCustomView(editTextList.get(3));

        GroupListView.newSection(getView().getCurContext())
                .addItemView(headImage, v ->
                        Matisse.from(getView().getFragment())
                        .choose(MimeType.ofAll())
                        .countable(false)
                        .maxSelectable(1)
                        .gridExpectedSize(QMUIResHelper.getAttrDimen(getView().getCurContext(),R.dimen.grid_expected_size))
                        .spanCount(3)
                        .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                        .thumbnailScale(0.85f)
                        .imageEngine(new GlideEngine())
                        .capture(true)
                        .captureStrategy(new CaptureStrategy(true,"me.daylight.talk.fileprovider"))
                        .forResult(GlobalField.REQUEST_CODE_CHOOSE))
                .addItemView(phone,null)
                .addItemView(name,null)
                .addItemView(nicName,null)
                .addItemView(gender,v -> {
                    String[] items=new String[]{"请选择","男","女"};
                    new QMUIDialog.CheckableDialogBuilder(getView().getCurContext())
                            .setCheckedIndex(user.getGender())
                            .addItems(items,((dialog, which) -> {
                                user.setGender(which);
                                gender.setDetailText(items[which]);
                                dialog.dismiss();
                            }))
                            .create().show();
                })
                .addItemView(signature,null)
                .addItemView(address,null)
                .addTo(groupListView);

        editable(false);
        if (!user.getPhone().equals(SharedPreferencesUtil.getString(getView().getCurContext(),GlobalField.USER,GlobalField.ACCOUNT))){
            headImage.setClickable(false);
            getView().hideEditButton();
        }
    }

    public void saveInfo() {
        user.setName(editTextList.get(0).getText().toString());
        user.setNicname(editTextList.get(1).getText().toString());
        user.setSignature(editTextList.get(2).getText().toString());
        user.setAddress(editTextList.get(3).getText().toString());
        user.setUpdatetime(new Date().getTime());
        getModel().saveUserInfo(user, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                DialogUtil.showTipDialog(getView().getCurContext(), QMUITipDialog.Builder.ICON_TYPE_SUCCESS,
                        retResult.getMsg(),true);
                editable(false);
                SharedPreferencesUtil.putValue(getView().getCurContext(),GlobalField.USER,GlobalField.STATE,100);
            }

            @Override
            public void onFailed(String errorMsg) {
                DialogUtil.showTipDialog(getView().getCurContext(),QMUITipDialog.Builder.ICON_TYPE_FAIL,
                        errorMsg,true);
            }
        });
    }

    public void editable(boolean editable) {
        getView().changeButtonStatus(editable);
        for (int i = 0; i < 4; i++) {
            editTextList.get(i).setFocusable(editable);
            editTextList.get(i).setFocusableInTouchMode(editable);
        }
        gender.setClickable(editable);
    }

    private EditText createEditText(Context context){
        EditText editText=new EditText(context);
        editText.setWidth(QMUIDisplayHelper.dp2px(context,120));
        editText.setGravity(Gravity.END);
        editText.setTextSize(15);
        editText.setTextColor(QMUIResHelper.getAttrColor(getView().getCurContext(),R.attr.qmui_config_color_gray_5));
        editText.setBackgroundColor(context.getResources().getColor(R.color.transparent));
        return editText;
    }

    private void initCustomView(){
        editTextList=new ArrayList<>();
        for (int i=0;i<4;i++){
            editTextList.add(createEditText(getView().getCurContext()));
        }
        editTextList.get(0).setText(user.getName());
        editTextList.get(1).setText(user.getNicname());
        editTextList.get(2).setText(user.getSignature());
        editTextList.get(3).setText(user.getAddress());

        headView=new ImageView(getView().getCurContext());
        setHeadImage(GlobalField.url+"user/headImage/"+user.getPhone()+"?time="+ user.getUpdatetime());
    }

    private void setHeadImage(String imageUrl){
        GlideApp.with(getView().getCurContext()).load(imageUrl)
                .override(QMUIDisplayHelper.dpToPx(48), QMUIDisplayHelper.dpToPx(48)).circleCrop().into(headView);
    }

    public InfoFragment.OnActivityResultCallback callback=uris-> getModel().uploadHeadImage(uris.get(0), new OnHttpCallBack<RetResult>() {
        @Override
        public void onSuccess(RetResult retResult) {
            DialogUtil.showTipDialog(getView().getCurContext(),QMUITipDialog.Builder.ICON_TYPE_SUCCESS,retResult.getMsg(),true);
            getModel().updateTime(user);
            setHeadImage(GlobalField.url+"user/headImage/"+user.getPhone()+"?time="+new Date().getTime());
        }

        @Override
        public void onFailed(String errorMsg) {
            DialogUtil.showTipDialog(getView().getCurContext(),QMUITipDialog.Builder.ICON_TYPE_FAIL,errorMsg,true);
        }
    });
}
