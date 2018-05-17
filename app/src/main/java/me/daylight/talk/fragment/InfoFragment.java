package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.qmuiteam.qmui.arch.QMUIFragmentActivity;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.zhihu.matisse.Matisse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.daylight.talk.R;
import me.daylight.talk.presenter.InfoPresenter;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;
import me.daylight.talk.customview.GroupListView;
import me.daylight.talk.view.InfoView;

public class InfoFragment extends BaseFragment<InfoPresenter> implements InfoView {
    @BindView(R.id.topbar_info) QMUITopBarLayout topBar;
    @BindView(R.id.groupList_info) GroupListView groupListView;
    @BindView(R.id.edit_button) TextView editBtn;
    private Unbinder unbinder;

    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getBaseFragmentActivity()).inflate(R.layout.fragment_info, null);
        unbinder=ButterKnife.bind(this,view);
        initView();
        getPresenter().initGroupListView(groupListView);
        if (SharedPreferencesUtil.getInt(getContext(), GlobalField.USER,GlobalField.STATE)==-100)
            changeButtonStatus(true);
        return view;
    }

    private void initView(){
        topBar.setTitle(R.string.title_info);
        topBar.addLeftBackImageButton().setOnClickListener(view->popBackStack());
        showEditButton();
    }

    @Override
    protected InfoPresenter createPresenter() {
        return new InfoPresenter();
    }

    @Override
    public QMUIFragmentActivity getCurContext() {
        return getBaseFragmentActivity();
    }

    @Override
    public void showEditButton() {
        editBtn.setVisibility(View.VISIBLE);
        editBtn.setOnClickListener(v -> {
            if (((TextView)v).getText().toString().equals(getString(R.string.edit))) {
                getPresenter().editable(true);
            }else if (((TextView)v).getText().toString().equals(getString(R.string.save))) {
                getPresenter().saveInfo();
            }
        });
    }

    @Override
    public InfoFragment getFragment() {
        return this;
    }

    @Override
    public void hideEditButton() {
        editBtn.setVisibility(View.GONE);
    }

    @Override
    public void changeButtonStatus(boolean editable) {
        if (editable)
            editBtn.setText(R.string.save);
        else
            editBtn.setText(R.string.edit);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GlobalField.REQUEST_CODE_CHOOSE && resultCode == Activity.RESULT_OK) {
            getPresenter().callback.onActivityResult(Matisse.obtainResult(data));
        }
    }

    public interface OnActivityResultCallback{
        void onActivityResult(List<Uri> uris);
    }
}
