package top.sakura70s.sevx.adapters;

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
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.helpers.JsonFrom;
import top.sakura70s.sevx.helpers.RequestHelper;

public class NovelItemAdapter extends RecyclerView.Adapter<NovelItemAdapter.NovelItemHolder> {
    private List<NovelBean> list;
    private String uName;
    private String uPassword;

    @NonNull
    @Override
    public NovelItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NovelItemHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NovelItemHolder holder, int position) {
        holder.bindData(list.get(position));
        Integer id = list.get(position).getId();
        holder.itemView.setOnClickListener(view -> {
            Context context = holder.itemView.getContext();
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra(SevxConsts.TYPE, SevxConsts.NOVEL);
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
                        Request request = new RequestHelper().getDeleteRequest(SevxConsts.NOVEL_DELETE, json);
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

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<NovelBean> list, String uName, String uPassword) {
        this.list = list;
        this.uName = uName;
        this.uPassword = uPassword;
        notifyItemChanged(list == null ? 0 : list.size());
    }

    public static class NovelItemHolder extends RecyclerView.ViewHolder {

        private final TextView novel_title;
        private final TextView novel_info;
        private final ImageView novel_img;

        public NovelItemHolder(@NonNull View itemView) {
            super(itemView);
            novel_title = itemView.findViewById(R.id.item_book_title);
            novel_info = itemView.findViewById(R.id.item_book_info);
            novel_img = itemView.findViewById(R.id.item_book_img);
        }

        public void bindData(NovelBean novelBean) {
            novel_title.setText(novelBean.getNovel_name());
            novel_info.setText(String.format("%s - %s", novelBean.getAuthor(), novelBean.getNovel_status()));
            Glide.with(itemView).load(novelBean.getLogo()).into(novel_img);
        }
    }
}
