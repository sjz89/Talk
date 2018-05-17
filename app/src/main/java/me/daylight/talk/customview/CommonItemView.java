package me.daylight.talk.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import me.daylight.talk.app.GlideApp;


/**
 * Created by Daylight on 2018/1/21.
 */

public class CommonItemView extends QMUICommonListItemView {
    public CommonItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CommonItemView(Context context) {
        super(context);
    }

    public CommonItemView(Context context, int orientation, int accessoryType) {
        super(context);
        setOrientation(orientation);
        setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        int padding=QMUIDisplayHelper.dp2px(context,8);
        setPadding(padding,padding,padding,padding);
        setAccessoryType(accessoryType);
    }

    public void setImageDrawable(String imagePath, int width, int height) {
        GlideApp.with(this).load(imagePath).override(QMUIDisplayHelper.dpToPx(width),
                QMUIDisplayHelper.dpToPx(height)).circleCrop().into(mImageView);
        mImageView.setVisibility(VISIBLE);
    }

    public void setImageDrawable(int imageRes, int width, int height) {
        GlideApp.with(this).load(imageRes).override(QMUIDisplayHelper.dpToPx(width),
                QMUIDisplayHelper.dpToPx(height)).into(mImageView);
        mImageView.setVisibility(VISIBLE);
    }


}
