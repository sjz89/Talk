package me.daylight.talk.view;

import android.support.v7.widget.RecyclerView;

public interface ChatView extends BaseView{
    void initTopBar(String nicName);
    RecyclerView initRecyclerView();
    void toInfoFragment();
}
