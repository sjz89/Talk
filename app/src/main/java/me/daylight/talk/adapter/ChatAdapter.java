package me.daylight.talk.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import me.daylight.talk.R;
import me.daylight.talk.app.GlideApp;
import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.utils.DateUtil;
import me.daylight.talk.utils.GlobalField;
import me.daylight.talk.customview.QQFaceView;

public class ChatAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<ChatMessage> data;
    private String left,right;
    private OnFriendAvatarClickListener mListener;

    public ChatAdapter(Context context, String right,String left){
        this.context=context;
        this.right=right;
        this.left=left;
        setHasStableIds(true);
    }

    public void setData(List<ChatMessage> data){
        this.data=data;
        notifyDataSetChanged();
    }

    public void setOnFriendAvatarClickListener(OnFriendAvatarClickListener listener){
        mListener=listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType== GlobalField.MessageType_Send) {
            view= LayoutInflater.from(context).inflate(R.layout.item_msg_send, parent, false);
            return new SendViewHolder(view,context,right);
        }else{
            view = LayoutInflater.from(context).inflate(R.layout.item_msg_receive, parent, false);
            return new ReceiveViewHolder(view,context,left);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).getMsgType();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (data!=null){
            if (holder instanceof SendViewHolder){
                ((SendViewHolder)holder).content.setText(data.get(position).getContext());
                ((SendViewHolder)holder).time.setText(DateUtil.autoTransFormat(data.get(position).getTime()));
            } else if (holder instanceof ReceiveViewHolder){
                ((ReceiveViewHolder)holder).content.setText(data.get(position).getContext());
                ((ReceiveViewHolder)holder).time.setText(DateUtil.autoTransFormat(data.get(position).getTime()));
            }
        }
        if (mListener!=null){
            if (holder instanceof ReceiveViewHolder)
                ((ReceiveViewHolder) holder).headImage.setOnClickListener(v -> mListener.onClick(v,position));
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

    public interface OnFriendAvatarClickListener{
        void onClick(View view,int position);
    }

    private static class ReceiveViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private ImageView headImage;
        private QQFaceView content;
        ReceiveViewHolder(View itemView,Context context,String left) {
            super(itemView);
            time=itemView.findViewById(R.id.receive_time);
            headImage=itemView.findViewById(R.id.receive_image);
            GlideApp.with(context).load(left).circleCrop().into(headImage);
            content =itemView.findViewById(R.id.receive_content);
        }
    }

    private static class SendViewHolder extends RecyclerView.ViewHolder{
        private TextView time;
        private ImageView headImage;
        private QQFaceView content;
        SendViewHolder(View itemView,Context context,String right) {
            super(itemView);
            time=itemView.findViewById(R.id.send_time);
            headImage=itemView.findViewById(R.id.send_image);
            GlideApp.with(context).load(right).circleCrop().into(headImage);
            content =itemView.findViewById(R.id.send_content);
        }
    }
}
