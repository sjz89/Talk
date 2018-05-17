package me.daylight.talk.view;

import me.daylight.talk.fragment.InfoFragment;

public interface InfoView extends BaseView{
    void showEditButton();

    void hideEditButton();

    void changeButtonStatus(boolean editable);

    InfoFragment getFragment();
}
