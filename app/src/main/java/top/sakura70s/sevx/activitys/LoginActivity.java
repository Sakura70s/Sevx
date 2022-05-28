package top.sakura70s.sevx.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.beans.LoginBean;
import top.sakura70s.sevx.helpers.StringHash;
import top.sakura70s.sevx.helpers.RequestHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // 硬编码测试用户
    final String account = SevxConsts.USER;
    final String url = SevxConsts.LOGIN_URL;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 实现点击方法（需要 implements View.OnClickListener）
        login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }


    public void onClick(View view) {
        if (view.getId() == R.id.login_button) {
            login_button.setEnabled(false);
            // 获得输入的密码字符串
            EditText passwordEdit = findViewById(R.id.login_editText);
            String passwordNumber = passwordEdit.getText().toString();

            // 转 SHA256 编码
            StringHash stringHash = new StringHash();
            String password = stringHash.SHA256(passwordNumber);

            // 构造 JSON
            // 实例化
            LoginBean loginBean = new LoginBean();
            loginBean.setUname(account);
            loginBean.setUpassword(password);
            String json = new Gson().toJson(loginBean);

            // 在这被坑了很久，发送网络请求需要开启新线程池。
            // 开启新线程池
            // start不加不会运行。
            new Thread(() -> {
                // 获取 request
                RequestHelper requestHelper = new RequestHelper();
                Request request = requestHelper.loginRequest(url, json);
                // OkHttp
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    // 同步执行，获取返回值
                    Response response = okHttpClient.newCall(request).execute();
                    // 通过后方API返回的状态码 决定使登录成功还是登录失败
                    switch (response.code()) {
                        // 200 Ok
                        case 200:{
                            Intent intent = new Intent(LoginActivity.this, SevxActivity.class);
                            intent.putExtra(SevxConsts.UNAME, account);
                            intent.putExtra(SevxConsts.UPASSWORD, password);
                            startActivity(intent);
                            // 进入主页面以后关闭登录页面
                            LoginActivity.this.finish();
                        } break;

                        // 401 Unauthorized
                        case 401:{
                            // 显示消息，更新控件等需要返回主线程。
                            runOnUiThread(() -> {
                                login_button.setEnabled(true);
                                Toast.makeText(LoginActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                            });
                        } break;

                        // 502 Bad Gateway
                        case 502:{
                            runOnUiThread(() -> {
                                login_button.setEnabled(true);
                                AlertDialog dialog = new AlertDialog.Builder(this)
                                        .setTitle("服务器君开小差啦~~~")
                                        .setMessage("服务端处于未启动的状态，请检查")
                                        .setPositiveButton("确定", (dialogInterface, i) -> dialogInterface.dismiss())
                                        .create();
                                // 显示模态对话框
                                dialog.show();
                            });
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    runOnUiThread(() -> {
                        login_button.setEnabled(true);
                        Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_SHORT).show();
                    });
                }
            }).start();

        }
    }
}