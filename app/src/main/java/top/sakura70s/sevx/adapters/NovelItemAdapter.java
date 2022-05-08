package top.sakura70s.sevx.adapters;

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

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.activitys.MainActivity;
import top.sakura70s.sevx.beans.NovelBean;

public class NovelItemAdapter extends RecyclerView.Adapter<NovelItemAdapter.NovelItemHolder> {
    private List<NovelBean> list;

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
        String id = String.valueOf(list.get(position).getId());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.itemView.getContext();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Type", "Novel");
                intent.putExtra("ID", id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public void setData(List<NovelBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public class NovelItemHolder extends RecyclerView.ViewHolder {

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
            novel_info.setText(novelBean.getAuthor() + " - " + novelBean.getNovel_status());
            Glide.with(itemView).load(novelBean.getLogo()).into(novel_img);
        }
    }
}