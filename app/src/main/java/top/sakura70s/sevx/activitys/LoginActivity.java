package top.sakura70s.sevx.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.sakura70s.sevx.R;
import top.sakura70s.sevx.SevxConsts;
import top.sakura70s.sevx.helpers.JsonFrom;
import top.sakura70s.sevx.helpers.StringHash;
import top.sakura70s.sevx.helpers.HttpHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    // 硬编码测试用户
    final String account = SevxConsts.USER;
    final String url = SevxConsts.LOGIN_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 实现点击方法（需要 implements View.OnClickListener）
        Button login_button = findViewById(R.id.login_button);
        login_button.setOnClickListener(this);
    }


    public void onClick(View view) {
        if (view.getId() == R.id.login_button) {
            // 获得输入的密码字符串
            EditText passwordEdit = findViewById(R.id.login_editText);
            String passwordNumber = passwordEdit.getText().toString();

            // 转 SHA256 编码
            StringHash stringHash = new StringHash();
            String password = stringHash.SHA256(passwordNumber);

            // 构造 JSON
            JsonFrom jsonFrom = new JsonFrom();
            JSONObject json = jsonFrom.loginJson(account, password);

            // 在这被坑了很久，发送网络请求需要开启新线程池。
            // 开启新线程池
            // start不加不会运行。
            new Thread(() -> {
                // 获取 request
                HttpHelper httpHelper = new HttpHelper();
                Request request = httpHelper.loginRequest(url, json);

                // OkHttp
                OkHttpClient okHttpClient = new OkHttpClient();
                try {
                    // 同步执行，获取返回值
                    Response response = okHttpClient.newCall(request).execute();
                    // 通过后方API返回的值 决定使登录成功还是登录失败
                    if (response.code() == 200) {
                        Intent intent = new Intent(LoginActivity.this, SevxActivity.class);
                        startActivity(intent);
                        // 进入主页面以后关闭登录页面
                        LoginActivity.this.finish();
                    } else {
                        // 显示消息，更新控件等需要返回主线程。
                        runOnUiThread(() -> Toast.makeText(LoginActivity.this, "验证失败", Toast.LENGTH_SHORT).show());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // 显示消息，更新控件等需要返回主线程。
                    runOnUiThread(() -> Toast.makeText(LoginActivity.this, "连接服务器失败", Toast.LENGTH_SHORT).show());
                }
            }).start();

        }
    }
}