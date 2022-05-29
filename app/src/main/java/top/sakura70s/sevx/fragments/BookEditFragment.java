package top.sakura70s.sevx.fragments;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.helpers.HttpHelper;


public class BookEditFragment extends Fragment implements View.OnClickListener {
    private Activity editActivity;
    private HttpHelper httpHelper;

    private Integer id;
    private String type;
    private String from;
    private String uName;
    private String uPassword;
    private Bundle bundle;

    private EditText seriesEdit;
    private EditText nameEdit;
    private EditText yearEdit;
    private EditText statusEdit;
    private EditText logoEdit;
    private EditText authorEdit;
    private EditText localFlagEdit;
    private EditText localUrlEdit;
    private EditText remoteFlagEdit;
    private EditText remoteUrlEdit;
    private EditText containerEdit;
    private EditText remarkEdit;


    public BookEditFragment() {
        // Required empty public constructor
        httpHelper = new HttpHelper();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        editActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initView();
    }

    private void initView() {
        seriesEdit = editActivity.findViewById(R.id.editPage_book_series_edit);
        nameEdit = editActivity.findViewById(R.id.editPage_book_name_edit);
        yearEdit = editActivity.findViewById(R.id.editPage_book_year_edit);
        statusEdit = editActivity.findViewById(R.id.editPage_book_status_edit);
        logoEdit = editActivity.findViewById(R.id.editPage_book_logo_edit);
        authorEdit = editActivity.findViewById(R.id.editPage_book_author_edit);
        localFlagEdit = editActivity.findViewById(R.id.editPage_book_localFlag_edit);
        localUrlEdit = editActivity.findViewById(R.id.editPage_book_localUrl_edit);
        remoteFlagEdit = editActivity.findViewById(R.id.editPage_book_remoteFlag);
        remoteUrlEdit = editActivity.findViewById(R.id.editPage_book_remoteUrl_edit);
        containerEdit = editActivity.findViewById(R.id.editPage_book_container_edit);
        remarkEdit = editActivity.findViewById(R.id.editPage_book_remark_edit);
        FloatingActionButton floatingActionButton = editActivity.findViewById(R.id.float_button_editPage_book_complete);
        floatingActionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}