package top.sakura70s.sevx.fragments.video;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.animation.AnimationAddBean;
import top.sakura70s.sevx.beans.animation.AnimationUpdateBean;
import top.sakura70s.sevx.beans.animation.VideoAnimationBean;
import top.sakura70s.sevx.helpers.HttpHelper;

public class EditVideoFragment extends Fragment implements View.OnClickListener {
    private Activity editActivity;
    private final HttpHelper httpHelper;

    private Integer id;
    private String type;
    private String from;
    private String uName;
    private String uPassword;
    private Bundle bundle;

    private EditText seriesFlagEdit;
    private EditText seriesIdEdit;
    private EditText nameEdit;
    private EditText yearEdit;
    private EditText directorEdit;
    private EditText screenWriterEdit;
    private EditText makeEdit;
    private EditText logoEdit;
    private EditText amountEdit;
    private EditText localFlagEdit;
    private EditText localUrlEdit;
    private EditText remoteFlagEdit;
    private EditText remoteUrlEdit;
    private EditText containerEdit;
    private EditText codeVEdit;
    private EditText codeAEdit;
    private EditText subTypeEdit;
    private EditText subTeamEdit;
    private EditText lastWatchEdit;
    private EditText remarkEdit;


    public EditVideoFragment() {
        // Required empty public constructor
        httpHelper = new HttpHelper();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_video, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        completeButton.setOnClickListener(this);
        this.initView();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initData();

    }

    private void initData() {
        bundle = getArguments();
        if (bundle != null) {
            this.from = bundle.getString(SevxConsts.FROM);
            this.type = bundle.getString(SevxConsts.TYPE);
            this.uName = bundle.getString(SevxConsts.UNAME);
            this.uPassword = bundle.getString(SevxConsts.UPASSWORD);
        }
    }

    private void initView(){
        // 初始化控件
        seriesFlagEdit = editActivity.findViewById(R.id.editPage_seriesFlag_edit);
        seriesIdEdit = editActivity.findViewById(R.id.editPage_seriesId_edit);
        nameEdit = editActivity.findViewById(R.id.editPage_name_edit);
        yearEdit = editActivity.findViewById(R.id.editPage_year_edit);
        directorEdit = editActivity.findViewById(R.id.editPage_director_edit);
        screenWriterEdit = editActivity.findViewById(R.id.editPage_screenWriter_edit);
        makeEdit = editActivity.findViewById(R.id.editPage_make_edit);
        logoEdit = editActivity.findViewById(R.id.editPage_logo_edit);
        amountEdit = editActivity.findViewById(R.id.editPage_amount_edit);
        localFlagEdit = editActivity.findViewById(R.id.editPage_localFlag_edit);
        localUrlEdit = editActivity.findViewById(R.id.editPage_localUrl_edit);
        remoteFlagEdit = editActivity.findViewById(R.id.editPage_remoteFlag_edit);
        remoteUrlEdit = editActivity.findViewById(R.id.editPage_remoteUrl_edit);
        containerEdit = editActivity.findViewById(R.id.editPage_container_edit);
        codeVEdit = editActivity.findViewById(R.id.editPage_codeV_edit);
        codeAEdit = editActivity.findViewById(R.id.editPage_codeA_edit);
        subTypeEdit = editActivity.findViewById(R.id.editPage_subType_edit);
        subTeamEdit = editActivity.findViewById(R.id.editPage_subTeam_edit);
        lastWatchEdit = editActivity.findViewById(R.id.editPage_lastWatch_edit);
        remarkEdit = editActivity.findViewById(R.id.editPage_remark_edit);
        FloatingActionButton completeButton = editActivity.findViewById(R.id.float_button_editPage_complete);
        completeButton.setOnClickListener(this);

    }

    private void setData(){
        switch (type){
            case SevxConsts.ANIMATION: {
                VideoAnimationBean animationBean = (VideoAnimationBean) bundle.getSerializable(SevxConsts.ANIMATION_BEAN);
                this.id = animationBean.getId();
                this.seriesFlagEdit.setText(animationBean.getSeriesflag().toString());
                this.seriesIdEdit.setText(String.valueOf(animationBean.getSeriesid()));
                this.nameEdit.setText(String.valueOf(animationBean.getAnimation_name()));
                this.yearEdit.setText(String.valueOf(animationBean.getAnimation_year()));
                this.directorEdit.setText(String.valueOf(animationBean.getDirector()));
                this.screenWriterEdit.setText(String.valueOf(animationBean.getScreenwriter()));
                this.makeEdit.setText(String.valueOf(animationBean.getMake()));
                this.logoEdit.setText(String.valueOf(animationBean.getLogo()));
                this.amountEdit.setText(String.valueOf(animationBean.getAmount()));
                this.localFlagEdit.setText(String.valueOf(animationBean.getLocalflag()));
                this.localUrlEdit.setText(String.valueOf(animationBean.getLocalurl()));
                this.remoteFlagEdit.setText(String.valueOf(animationBean.getRemoteflag()));
                this.remoteUrlEdit.setText(String.valueOf(animationBean.getRemoteurl()));
                this.containerEdit.setText(String.valueOf(animationBean.getContainer()));
                this.codeVEdit.setText(String.valueOf(animationBean.getCodev()));
                this.codeAEdit.setText(String.valueOf(animationBean.getCodea()));
                this.subTypeEdit.setText(String.valueOf(animationBean.getSubtype()));
                this.subTeamEdit.setText(String.valueOf(animationBean.getSubteam()));
                this.lastWatchEdit.setText(String.valueOf(animationBean.getLastwatch()));
                this.remarkEdit.setText(String.valueOf(animationBean.getRemark()));
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (from.equals(SevxConsts.DETAILS)){
            this.setData();
        }
//        this.setData();
    }

    @Override
    public void onClick(View view) {
        // 新增
        if (from.equals(SevxConsts.LIST)) {
            switch (type) {
                // 新增动漫
                case SevxConsts.ANIMATION: {
                    String addAnimationJson = this.getAnimationAddJson();
                    if (addAnimationJson != null){
                        new Thread(() -> {
                            if (httpHelper.animationAdd(addAnimationJson)) {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加成功！", Toast.LENGTH_SHORT).show());
                            } else {
                                editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "添加失败！", Toast.LENGTH_SHORT).show());
                            }
                        }).start();
                    } else {
                        Toast.makeText(getContext(), "有东西没有填写哦~~~", Toast.LENGTH_SHORT).show();
                    }

                } break;
                // 新增电影
                case SevxConsts.FILM: {

                } break;
            }
        }
        // 更新
        if (from.equals(SevxConsts.DETAILS)) {
            switch (type) {
                // 修改动漫
                case SevxConsts.ANIMATION: {
                    String animationUpdateJson = this.getAnimationUpdateJson();
                    new Thread(() -> {
                        if (httpHelper.animationUpdate(animationUpdateJson)) {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show());
                        } else {
                            editActivity.runOnUiThread(() -> Toast.makeText(getContext(), "更新失败！", Toast.LENGTH_SHORT).show());
                        }
                    }).start();
                } break;
                case SevxConsts.FILM: {

                } break;
            }
        }
    }


    /**
     * 获取更新动漫实体 JSON
     * @return jsonString
     */
    private String getAnimationUpdateJson(){
        // 实例化一个修改动漫实体类
        AnimationUpdateBean animationUpdateBean = new AnimationUpdateBean();
        // 数据填充
        animationUpdateBean.setId(id);
        animationUpdateBean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
        animationUpdateBean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
        animationUpdateBean.setAnimation_name(nameEdit.getText().toString());
        animationUpdateBean.setAnimation_year(yearEdit.getText().toString());
        animationUpdateBean.setDirector(directorEdit.getText().toString());
        animationUpdateBean.setScreenwriter(screenWriterEdit.getText().toString());
        animationUpdateBean.setMake(makeEdit.getText().toString());
        animationUpdateBean.setLogo(logoEdit.getText().toString());
        animationUpdateBean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
        animationUpdateBean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
        animationUpdateBean.setLocalurl(localUrlEdit.getText().toString());
        animationUpdateBean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
        animationUpdateBean.setRemoteurl(remoteUrlEdit.getText().toString());
        animationUpdateBean.setContainer(containerEdit.getText().toString());
        animationUpdateBean.setCodev(codeVEdit.getText().toString());
        animationUpdateBean.setCodea(codeAEdit.getText().toString());
        animationUpdateBean.setSubtype(subTypeEdit.getText().toString());
        animationUpdateBean.setSubteam(subTeamEdit.getText().toString());
        animationUpdateBean.setLastwatch(lastWatchEdit.getText().toString());
        animationUpdateBean.setRemark(remarkEdit.getText().toString());
        animationUpdateBean.setUname(uName);
        animationUpdateBean.setUpassword(uPassword);
        return new Gson().toJson(animationUpdateBean);
    }

    /**
     * 获取添加动漫Json
     * @return jsonString
     */
    private String getAnimationAddJson(){
        // 实例化一个 添加动漫 实体类
        AnimationAddBean animationAddBean = new AnimationAddBean();
        try {
            animationAddBean.setSeriesflag(Boolean.valueOf(seriesFlagEdit.getText().toString()));
            animationAddBean.setSeriesid(Integer.valueOf(seriesIdEdit.getText().toString()));
            animationAddBean.setAnimation_name(nameEdit.getText().toString());
            animationAddBean.setAnimation_year(yearEdit.getText().toString());
            animationAddBean.setDirector(directorEdit.getText().toString());
            animationAddBean.setScreenwriter(screenWriterEdit.getText().toString());
            animationAddBean.setMake(makeEdit.getText().toString());
            animationAddBean.setLogo(logoEdit.getText().toString());
            animationAddBean.setAmount(Integer.valueOf(amountEdit.getText().toString()));
            animationAddBean.setLocalflag(Boolean.valueOf(localFlagEdit.getText().toString()));
            animationAddBean.setLocalurl(localUrlEdit.getText().toString());
            animationAddBean.setRemoteflag(Boolean.valueOf(remoteFlagEdit.getText().toString()));
            animationAddBean.setRemoteurl(remoteUrlEdit.getText().toString());
            animationAddBean.setContainer(containerEdit.getText().toString());
            animationAddBean.setCodev(codeVEdit.getText().toString());
            animationAddBean.setCodea(codeAEdit.getText().toString());
            animationAddBean.setSubtype(subTypeEdit.getText().toString());
            animationAddBean.setSubteam(subTeamEdit.getText().toString());
            animationAddBean.setLastwatch(lastWatchEdit.getText().toString());
            animationAddBean.setRemark(remarkEdit.getText().toString());
            animationAddBean.setUname(uName);
            animationAddBean.setUpassword(uPassword);
        } catch (Exception ignored) {
            return null;
        }
        // 填充数据

        return new Gson().toJson(animationAddBean);
    }

}