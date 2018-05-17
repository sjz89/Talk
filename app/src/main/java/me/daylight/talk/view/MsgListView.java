package me.daylight.talk.view;


import me.daylight.talk.adapter.CommonAdapter;

public interface MsgListView extends BaseView {
    void initRecyclerView(CommonAdapter adapter);

    void toChatFragment();
}
