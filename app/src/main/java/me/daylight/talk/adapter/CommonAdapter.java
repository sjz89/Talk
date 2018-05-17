package me.daylight.talk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import java.util.List;

import me.daylight.talk.R;
import me.daylight.talk.bean.CommonData;
import me.daylight.talk.utils.DateUtil;
import me.daylight.talk.customview.CommonItemView;

public class CommonAdapter extends RecyclerView.Adapter<CommonAdapter.ViewHolder> {
    private Context context;
    private List<CommonData> data;
    private int orientation;
    private int ACCESSORY_TYPE;
    private OnCommonItemClickListener onCommonItemClickListener;
    public CommonAdapter(Context context, int orientation){
        this.context=context;
        this.orientation=orientation;
        this.ACCESSORY_TYPE = QMUICommonListItemView.ACCESSORY_TYPE_NONE;
        setHasStableIds(true);
    }
    public CommonAdapter(Context context,int orientation,int ACCESSORY_TYPE){
        this.context=context;
        this.orientation=orientation;
        this.ACCESSORY_TYPE =ACCESSORY_TYPE;
        setHasStableIds(true);
    }
    public void setData(List<CommonData> data){
        this.data=data;
        notifyDataSetChanged();
    }

    public List<CommonData> getData() {
        return data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_common,parent,false);
        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (data!=null){
            CommonData commonData=data.get(position);
            holder.item.setOrientation(orientation);
            holder.item.setText(commonData.getText());
            holder.item.setDetailText(commonData.getSubText());
            if (commonData.getImage()!=null)
                holder.item.setImageDrawable(commonData.getImage(),42,42);
            else if (commonData.getIcon()!=0)
                holder.item.setImageDrawable(commonData.getIcon(),36,36);
            holder.item.setAccessoryType(ACCESSORY_TYPE);
            if (ACCESSORY_TYPE==QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM) {
                holder.initTextView();
                holder.item.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CUSTOM);
                holder.item.addAccessoryCustomView(holder.getTextView());
                holder.getTextView().setText(DateUtil.autoTransFormat(commonData.getTime()));
            }
            holder.item.setRedDotPosition(QMUICommonListItemView.REDDOT_POSITION_RIGHT);
            holder.item.showRedDot(commonData.isNew());
            if (commonData.getNumber()!=0) {
                holder.msgNumber.setVisibility(View.VISIBLE);
                holder.msgNumber.setText(String.valueOf(commonData.getNumber()));
            }else
                holder.msgNumber.setVisibility(View.GONE);
        }
        if (onCommonItemClickListener!=null){
            holder.item.setOnClickListener(v -> onCommonItemClickListener.onItemClick(holder.item,position));
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).getId();
    }

    public void setOnCommonItemClickListener(OnCommonItemClickListener listener){
        this.onCommonItemClickListener=listener;
    }

    public interface OnCommonItemClickListener {
        void onItemClick(View view,int position);
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private CommonItemView item;
        private Context context;
        private TextView textView;
        private TextView msgNumber;
        ViewHolder(View itemView,Context context) {
            super(itemView);
            this.context=context;
            item=itemView.findViewById(R.id.common_item);
            msgNumber=itemView.findViewById(R.id.common_msg_number);
        }
        void initTextView(){
            textView=new TextView(context);
            textView.setTextSize(13);
            textView.setLineSpacing(0,1.2f);
            textView.setGravity(Gravity.END);
            RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0,0, 0,QMUIDisplayHelper.dp2px(context,20));
            textView.setLayoutParams(layoutParams);
            textView.setTextColor(context.getResources().getColor(R.color.text_time));
        }
        TextView getTextView(){
            return textView;
        }
    }
}