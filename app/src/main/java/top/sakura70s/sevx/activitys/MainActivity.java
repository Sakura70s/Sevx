package top.sakura70s.sevx.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import top.sakura70s.sevx.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        TextView idTextView= findViewById(R.id.main_id);
        TextView typeTextView = findViewById(R.id.main_type);
        idTextView.setText("ID: " + intent.getStringExtra("ID"));
        typeTextView.setText("Type: " + intent.getStringExtra("Type"));
    }

    @Override
    protected void onStart() {
        super.onStart();


    }
}