package top.sakura70s.sevx.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.beans.comic.ComicBean;
import top.sakura70s.sevx.beans.film.VideoFilmBean;
import top.sakura70s.sevx.beans.music.MusicBean;
import top.sakura70s.sevx.beans.novel.NovelBean;
import top.sakura70s.sevx.beans.sv.VideoSvBean;
import top.sakura70s.sevx.beans.tv.VideoTvBean;
import top.sakura70s.sevx.fragments.BookEditFragment;
import top.sakura70s.sevx.fragments.MusicEditFragment;
import top.sakura70s.sevx.fragments.video.EditVideoFragment;

public class EditActivity extends AppCompatActivity {
    private String from;
    private String type;
    private String uName;
    private String uPassword;
    private Intent intent;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.initData();
        this.initView();
        this.openFragment();
    }

    private void initData() {
        intent = getIntent();
        if (intent != null){
            from = intent.getStringExtra(SevxConsts.FROM);
            type = intent.getStringExtra(SevxConsts.TYPE);
            uName = intent.getStringExtra(SevxConsts.UNAME);
            uPassword = intent.getStringExtra(SevxConsts.UPASSWORD);
        }

    }

    private void initView() {
        TextView textView = this.findViewById(R.id.edit_tv);
        textView.setText(String.format("Now you are from :%s", from));
        fragmentTransaction = getSupportFragmentManager().beginTransaction();

    }

    private Bundle getBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(SevxConsts.FROM, from);
        bundle.putString(SevxConsts.TYPE, type);
        bundle.putString(SevxConsts.UNAME, uName);
        bundle.putString(SevxConsts.UPASSWORD, uPassword);
        return bundle;
    }

    private void openFragment(){
        // 从列表页来的，判断为添加
        if (from.equals(SevxConsts.LIST)){
            // 影视类型
            if (type.equals(SevxConsts.ANIMATION) || type.equals(SevxConsts.FILM) || type.equals(SevxConsts.TV) || type.equals(SevxConsts.SV)){
                EditVideoFragment editVideoFragment = new EditVideoFragment();
                editVideoFragment.setArguments(this.getBundle());

                fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                fragmentTransaction.commit();
            }

            // 音乐类型
            if (type.equals(SevxConsts.MUSIC)) {
                MusicEditFragment musicEditFragment = new MusicEditFragment();
                musicEditFragment.setArguments(this.getBundle());

                fragmentTransaction.add(R.id.edit_view, musicEditFragment);
                fragmentTransaction.commit();
            }

            // 书籍类型
            if (type.equals(SevxConsts.NOVEL) || type.equals(SevxConsts.COMIC)) {
                BookEditFragment bookEditFragment = new BookEditFragment();
                bookEditFragment.setArguments(this.getBundle());

                fragmentTransaction.add(R.id.edit_view, bookEditFragment);
                fragmentTransaction.commit();
            }
        }



        // 从详情页来的，判断为修改，需要预先填写数据
        else if (from.equals(SevxConsts.DETAILS)){
            switch (type){
                case SevxConsts.ANIMATION: {
                    // 当 intent不为空的时候才提取数据
                    if (intent != null) {
                        // 获得传进来的数据
                        VideoAnimationBean animationBean = (VideoAnimationBean) intent.getSerializableExtra(SevxConsts.ANIMATION_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.ANIMATION_BEAN, animationBean);

                        // 实例化 Fragment
                        EditVideoFragment editVideoFragment = new EditVideoFragment();
                        editVideoFragment.setArguments(bundle);

                        // 启动 Fragment
                        fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                        fragmentTransaction.commit();
                    }
                } break;

                case SevxConsts.MUSIC: {
                    if (intent != null){
                        MusicBean musicBean = (MusicBean) intent.getSerializableExtra(SevxConsts.MUSIC_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.MUSIC_BEAN, musicBean);

                        MusicEditFragment musicEditFragment = new MusicEditFragment();
                        musicEditFragment.setArguments(bundle);

                        fragmentTransaction.add(R.id.edit_view, musicEditFragment);
                        fragmentTransaction.commit();
                    }
                } break;

                case SevxConsts.NOVEL:{
                    if (intent != null){
                        NovelBean novelBean = (NovelBean) intent.getSerializableExtra(SevxConsts.NOVEL_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.NOVEL_BEAN, novelBean);

                        BookEditFragment bookEditFragment = new BookEditFragment();
                        bookEditFragment.setArguments(bundle);

                        fragmentTransaction.add(R.id.edit_view, bookEditFragment);
                        fragmentTransaction.commit();
                    }
                } break;

                case SevxConsts.COMIC:{
                    if (intent != null){
                        ComicBean comicBean = (ComicBean) intent.getSerializableExtra(SevxConsts.COMIC_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.COMIC_BEAN, comicBean);

                        BookEditFragment bookEditFragment = new BookEditFragment();
                        bookEditFragment.setArguments(bundle);

                        fragmentTransaction.add(R.id.edit_view, bookEditFragment);
                        fragmentTransaction.commit();
                    }
                }

                case SevxConsts.FILM:{
                    if (intent != null) {
                        // 获得传进来的数据
                        VideoFilmBean filmBean = (VideoFilmBean) intent.getSerializableExtra(SevxConsts.FILM_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.FILM_BEAN, filmBean);

                        // 实例化 Fragment
                        EditVideoFragment editVideoFragment = new EditVideoFragment();
                        editVideoFragment.setArguments(bundle);

                        // 启动 Fragment
                        fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                        fragmentTransaction.commit();
                    }
                } break;

                case SevxConsts.TV:{
                    if (intent != null) {
                        // 获得传进来的数据
                        VideoTvBean tvBean = (VideoTvBean) intent.getSerializableExtra(SevxConsts.TV_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.TV_BEAN, tvBean);

                        // 实例化 Fragment
                        EditVideoFragment editVideoFragment = new EditVideoFragment();
                        editVideoFragment.setArguments(bundle);

                        // 启动 Fragment
                        fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                        fragmentTransaction.commit();
                    }
                } break;

                case SevxConsts.SV:{
                    if (intent != null) {
                        // 获得传进来的数据
                        VideoSvBean svBean = (VideoSvBean) intent.getSerializableExtra(SevxConsts.SV_BEAN);

                        Bundle bundle = this.getBundle();
                        bundle.putSerializable(SevxConsts.SV_BEAN, svBean);

                        // 实例化 Fragment
                        EditVideoFragment editVideoFragment = new EditVideoFragment();
                        editVideoFragment.setArguments(bundle);

                        // 启动 Fragment
                        fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                        fragmentTransaction.commit();
                    }
                }
            }
        }
        // 有问题
        else Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
    }
}