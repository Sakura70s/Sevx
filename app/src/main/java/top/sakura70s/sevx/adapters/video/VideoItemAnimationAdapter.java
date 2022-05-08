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

import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.beans.VideoAnimationBean;

public class VideoItemAnimationAdapter extends RecyclerView.Adapter<VideoItemAnimationAdapter.VideoItemAnimationHolder> {
    // 定义 传进来的 集合
    private List<VideoAnimationBean> list;

    // 约定俗成
    @NonNull
    @Override
    public VideoItemAnimationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemAnimationHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false)
        );
    }

    // 绑定视图
    @Override
    public void onBindViewHolder(@NonNull VideoItemAnimationHolder holder, int position) {
        holder.bindData(list.get(position));
        // 获取当前条目对应的 ID 编号
        String id = String.valueOf(list.get(position).getId());
        // 点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取上下文对象
                Context context = holder.itemView.getContext();
                // 跳转
                Intent intent = new Intent(context, MainActivity.class);
                // 这是由 Animation 发起的跳转
                intent.putExtra("Type", "Animation");
                intent.putExtra("ID", id);
                context.startActivity(intent);

            }
        });
    }

    // 获取数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    // 将外部 JSON 数据传入 Adapter
    public void setData(List<VideoAnimationBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    // 设置具体 Item 的内容
    public class VideoItemAnimationHolder extends RecyclerView.ViewHolder {

        private final TextView animation_make;
        private final TextView animation_title;
        private final ImageView animation_img;


        public VideoItemAnimationHolder(@NonNull View itemView) {
            super(itemView);
            animation_title = itemView.findViewById(R.id.item_video_title);
            animation_make = itemView.findViewById(R.id.item_video_make);
            animation_img = itemView.findViewById(R.id.item_video_img);
        }

        public void bindData(VideoAnimationBean videoAnimationBean) {
            animation_title.setText(videoAnimationBean.getAnimation_name());
            animation_make.setText(videoAnimationBean.getMake());
            Glide.with(itemView).load(videoAnimationBean.getLogo()).into(animation_img);
        }
    }

}