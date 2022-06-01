package top.sakura70s.sevx.adapters.video;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.helpers.JsonFrom;
import top.sakura70s.sevx.helpers.RequestHelper;

public class VideoItemFilmAdapter extends RecyclerView.Adapter<VideoItemFilmAdapter.VideoItemFilmHolder> {
    // 定义 传进来的 集合
    private List<VideoFilmBean> list;
    private String uName;
    private String uPassword;


    // 约定俗成
    @NonNull
    @Override
    public VideoItemFilmAdapter.VideoItemFilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoItemFilmHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false));
    }

    // 绑定视图
    @Override
    public void onBindViewHolder(@NonNull VideoItemFilmAdapter.VideoItemFilmHolder holder, int position) {
        holder.bindData(list.get(position));
        // 获取当前条目对应的 ID 编号
        Integer id = list.get(position).getId();
        // 点击事件
        holder.itemView.setOnClickListener(view -> {
            // 获取上下文对象
            Context context = holder.itemView.getContext();
            // 跳转
            Intent intent = new Intent(context, MainActivity.class);
            // 这是由 Film 发起的跳转
            intent.putExtra(SevxConsts.TYPE, SevxConsts.FILM);
            intent.putExtra(SevxConsts.ID, id);
            intent.putExtra(SevxConsts.UNAME, uName);
            intent.putExtra(SevxConsts.UPASSWORD, uPassword);
            context.startActivity(intent);

        });

        holder.itemView.setOnLongClickListener(view -> {
            AlertDialog dialog = new AlertDialog.Builder(view.getContext())
                    .setIcon(R.drawable.ic_delete)
                    .setTitle("删除")
                    .setMessage("确定要删除吗？")
                    .setNegativeButton("取消", (dialogInterface, i) -> dialogInterface.dismiss())
                    .setPositiveButton("确定", (dialogInterface, i) -> {
                        dialogInterface.dismiss();
                        // 构建请求体
                        JSONObject json = new JsonFrom().deleteJson(id, uName, uPassword);
                        Request request = new RequestHelper().getDeleteRequest(SevxConsts.VIDEO_FILM_DELETE, json);
                        // 执行删除
                        new OkHttpClient().newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) {

                            }
                        });
                    }).create();
            // 显示模态对话框
            dialog.show();
            return true;
        });
    }

    // 获取数量
    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    //
    public void setData(List<VideoFilmBean> list, String uName, String uPassword) {
        this.list = list;
        this.uName = uName;
        this.uPassword = uPassword;
        notifyItemChanged(list == null ? 0 : list.size());
    }

    // 设置具体 Item 的内容
    public static class VideoItemFilmHolder extends RecyclerView.ViewHolder {

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
