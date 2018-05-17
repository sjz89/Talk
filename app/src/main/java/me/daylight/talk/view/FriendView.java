package me.daylight.talk.view;

import me.daylight.talk.adapter.CommonAdapter;

public interface FriendView extends BaseView{
    void initRecyclerView(CommonAdapter adapter);

    void hideRefresh();

    void toChatFragment();
}
