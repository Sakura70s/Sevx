package top.sakura70s.sevx.adapters.video;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.beans.VideoSvBean;

public class VideoItemSvAdapter extends RecyclerView.Adapter<VideoItemSvAdapter.VideoItemSvHolder> {
    // 定义 传进来的 集合
    private List<VideoSvBean> list;

    // 约定俗成
    @NonNull
    @Override
    public VideoItemSvAdapter.VideoItemSvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemSvHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    // 绑定视图
    @Override
    public void onBindViewHolder(@NonNull VideoItemSvAdapter.VideoItemSvHolder holder, int position) {
        holder.bindData(list.get(position));
        // 获取当前条目对应的 ID 编号
        Integer id = list.get(position).getId();
        // 点击事件
        holder.itemView.setOnClickListener(view -> {
            // 获取上下文对象
            Context context = holder.itemView.getContext();
            // 跳转
            Intent intent = new Intent(context, MainActivity.class);
            // 这是由 Sv 发起的跳转
            intent.putExtra(SevxConsts.TYPE, SevxConsts.SV);
            intent.putExtra(SevxConsts.ID, id);
            context.startActivity(intent);

        });
    }

    // 获取数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //
    public void setData(List<VideoSvBean> list) {
        this.list = list;
        notifyItemChanged(list.size());
    }

    // 设置具体 Item 的内容
    public static class VideoItemSvHolder extends RecyclerView.ViewHolder {

        private final TextView sv_make;
        private final TextView sv_title;
        private final ImageView sv_img;


        public VideoItemSvHolder(@NonNull View itemView) {
            super(itemView);
            sv_title = itemView.findViewById(R.id.item_video_title);
            sv_make = itemView.findViewById(R.id.item_video_make);
            sv_img = itemView.findViewById(R.id.item_video_img);
        }

        public void bindData(VideoSvBean videoSvBean) {
            sv_title.setText(videoSvBean.getSv_name());
            sv_make.setText(videoSvBean.getAuthor());
            Glide.with(itemView).load(videoSvBean.getLogo()).into(sv_img);
        }
    }
}
