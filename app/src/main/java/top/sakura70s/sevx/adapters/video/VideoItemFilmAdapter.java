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
import top.sakura70s.sevx.beans.VideoFilmBean;

public class VideoItemFilmAdapter extends RecyclerView.Adapter<VideoItemFilmAdapter.VideoItemFilmHolder> {
    // 定义 传进来的 集合
    private List<VideoFilmBean> list;

    // 约定俗成
    @NonNull
    @Override
    public VideoItemFilmAdapter.VideoItemFilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemFilmAdapter.VideoItemFilmHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    // 绑定视图
    @Override
    public void onBindViewHolder(@NonNull VideoItemFilmAdapter.VideoItemFilmHolder holder, int position) {
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
                // 这是由 Film 发起的跳转
                intent.putExtra("Type", "Film");
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

    //
    public void setData(List<VideoFilmBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    // 设置具体 Item 的内容
    public class VideoItemFilmHolder extends RecyclerView.ViewHolder {

        private final TextView film_make;
        private final TextView film_title;
        private final ImageView film_img;


        public VideoItemFilmHolder(@NonNull View itemView) {
            super(itemView);
            film_title = itemView.findViewById(R.id.item_video_title);
            film_make = itemView.findViewById(R.id.item_video_make);
            film_img = itemView.findViewById(R.id.item_video_img);
        }

        public void bindData(VideoFilmBean videoFilmBean) {
            film_title.setText(videoFilmBean.getFilm_name());
            film_make.setText(videoFilmBean.getMake());
            Glide.with(itemView).load(videoFilmBean.getLogo()).into(film_img);
        }
    }
}