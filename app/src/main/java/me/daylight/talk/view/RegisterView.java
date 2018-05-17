package me.daylight.talk.view;


public interface RegisterView extends BaseView {
    void showTipDialog(int type,String msg,boolean delayDismiss);

    void hideTipDialog();

    void toLogin();
}
