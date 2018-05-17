package me.daylight.talk.fragment;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.daylight.talk.R;
import me.daylight.talk.presenter.BasePresenter;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.utils.SharedPreferencesUtil;

public class MainFragment extends BaseFragment {
    @BindView(R.id.tabs)
    QMUITabSegment tabSegment;
    @BindView(R.id.pager)
    ViewPager mViewPager;
    private CharSequence[] titles;
    private HashMap<Pager,BaseFragment> mPages;
    @SuppressLint("InflateParams")
    @Override
    protected View onCreateView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_main, null);
        ButterKnife.bind(this,view);
        initTitles();
        initTabs();
        initPagers();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (SharedPreferencesUtil.getInt(getContext(), GlobalField.USER,GlobalField.STATE)==-100)
            startFragment(new InfoFragment());
    }

    private void initTitles(){
        titles=new String[3];
        titles[0]=getResources().getString(R.string.title_message);
        titles[1]=getResources().getString(R.string.title_friend);
        titles[2]=getResources().getString(R.string.title_more);
    }
    @SuppressWarnings("ConstantConditions")
    private void initTabs() {
        int normalColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_gray_6);
        int selectColor = QMUIResHelper.getAttrColor(getActivity(), R.attr.qmui_config_color_blue);
        tabSegment.setDefaultNormalColor(normalColor);
        tabSegment.setDefaultSelectedColor(selectColor);

        QMUITabSegment.Tab message = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_message),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_message_selected),
                titles[0], false
        );

        QMUITabSegment.Tab friend = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_friend),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_friend_selected),
                titles[1], false
        );
        QMUITabSegment.Tab more = new QMUITabSegment.Tab(
                ContextCompat.getDrawable(getContext(), R.drawable.ic_more),
                ContextCompat.getDrawable(getContext(), R.drawable.ic_more_selected),
                titles[2], false
        );
        tabSegment.addTab(message)
                .addTab(friend)
                .addTab(more);
    }

    private void initPagers() {
        mPages=new HashMap<>();
        BaseFragment messageFragment=new MessageFragment();
        mPages.put(Pager.MESSAGE,messageFragment);
        BaseFragment friendFragment=new FriendFragment();
        mPages.put(Pager.FRIEND,friendFragment);
        BaseFragment moreFragment=new FunctionFragment();
        mPages.put(Pager.MORE,moreFragment);

        FragmentPagerAdapter mPageAdapter=new FragmentPagerAdapter(getFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mPages.get(Pager.getPagerFromPosition(position));
            }

            @Override
            public int getCount() {
                return mPages.size();
            }
        };

        mViewPager.setAdapter(mPageAdapter);
        mViewPager.setCurrentItem(1);
        tabSegment.setupWithViewPager(mViewPager,false);

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    enum Pager {
        MESSAGE, FRIEND, MORE;

        public static Pager getPagerFromPosition(int position) {
            switch (position) {
                case 0:
                    return MESSAGE;
                case 1:
                    return FRIEND;
                case 2:
                    return MORE;
                default:
                    return FRIEND;
            }
        }
    }

    @Override
    public TransitionConfig onFetchTransitionConfig() {
        return QMUIFragment.SCALE_TRANSITION_CONFIG;
    }

    @Override
    protected boolean canDragBack() {
        return false;
    }

}
