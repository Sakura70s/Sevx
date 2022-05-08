package top.sakura70s.sevx.activitys;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import top.sakura70s.sevx.R;
import top.sakura70s.sevx.databinding.ActivitySevxBinding;

public class SevxActivity extends AppCompatActivity {

    private ActivitySevxBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 绑定布局文件
        binding = ActivitySevxBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 隐藏 Title
//        this.getSupportActionBar().hide();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_video, R.id.navigation_music, R.id.navigation_novel, R.id.navigation_comic)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_sevx);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}