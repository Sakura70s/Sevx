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
import top.sakura70s.sevx.beans.music.MusicBean;
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

    private void setBundleData(Bundle bundle) {
        bundle.putString(SevxConsts.FROM, from);
        bundle.putString(SevxConsts.TYPE, type);
        bundle.putString(SevxConsts.UNAME, uName);
        bundle.putString(SevxConsts.UPASSWORD, uPassword);
    }

    private void openFragment(){
        // 从列表页来的，判断为添加
        if (from.equals(SevxConsts.LIST)){
            // 影视类型
            if (type.equals(SevxConsts.ANIMATION) || type.equals(SevxConsts.FILM) || type.equals(SevxConsts.TV) || type.equals(SevxConsts.SV)){
                Bundle bundle = new Bundle();
                this.setBundleData(bundle);

                EditVideoFragment editVideoFragment = new EditVideoFragment();
                editVideoFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.edit_view, editVideoFragment);
                fragmentTransaction.commit();
            }

            // 音乐类型
            if (type.equals(SevxConsts.MUSIC)) {
                Bundle bundle = new Bundle();
                this.setBundleData(bundle);

                MusicEditFragment musicEditFragment = new MusicEditFragment();
                musicEditFragment.setArguments(bundle);

                fragmentTransaction.add(R.id.edit_view, musicEditFragment);
                fragmentTransaction.commit();
            }

            // 书籍类型
            if (type.equals(SevxConsts.NOVEL) || type.equals(SevxConsts.COMIC)) {

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
                        
                        Bundle bundle = new Bundle();
                        // 讲传进来的数据传递到 Fragment 里
                        bundle.putSerializable(SevxConsts.ANIMATION_BEAN, animationBean);
                        this.setBundleData(bundle);

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

                        Bundle bundle = new Bundle();
                        this.setBundleData(bundle);
                        bundle.putSerializable(SevxConsts.MUSIC_BEAN, musicBean);

                        MusicEditFragment musicEditFragment = new MusicEditFragment();
                        musicEditFragment.setArguments(bundle);

                        fragmentTransaction.add(R.id.edit_view, musicEditFragment);
                        fragmentTransaction.commit();
                    }
                } break;
            }
        }
        // 有问题
        else Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
    }
}