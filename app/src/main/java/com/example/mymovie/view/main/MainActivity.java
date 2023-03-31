package com.example.mymovie.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.mymovie.R;
import com.example.mymovie.network.model.FriendItem;
import com.example.mymovie.view.FavoriteFragment;
import com.example.mymovie.view.HomeFragment;
import com.example.mymovie.view.SearchFragment;
import com.example.mymovie.view.SettingFragment;
import com.example.mymovie.view.VideoFragment;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ImageView menu;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);

        // UI를 표시하는 메소드
        setContentView(R.layout.activity_main);

        // 상단 헤더
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        // 기존 타이틀 제거
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        HomeFragment homeFragment = new HomeFragment();
        FavoriteFragment favoriteFragment = new FavoriteFragment();
        VideoFragment videoFragment = new VideoFragment();
        SearchFragment searchFragment = new SearchFragment();
        SettingFragment settingFragment = new SettingFragment();

        menu = findViewById(R.id.menu);
        menu.setOnClickListener(click);

//        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, navigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();

        NavigationBarView navigationBarView = findViewById(R.id.bottom_navigationview);
        navigationBarView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, homeFragment).commit();
                        return true;
                    case R.id.favorite:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, favoriteFragment).commit();
                        return true;
                    case R.id.video:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, videoFragment).commit();
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, searchFragment).commit();
                        return true;
                    case R.id.setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.containers, settingFragment).commit();
                        return true;
                }
                return false;
            }
        });

        // 메인 화면에서 메뉴 버튼을 클릭했을 때의 행동

//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//
//                switch (menuItem.getItemId()) {
//                    case R.id.account:
//                        menuItem.setChecked(true);
//                        displayMessage("camera selected");
//                        drawerLayout.closeDrawers();
//                        return true;
//
//                    case R.id.camera:
//                        menuItem.setChecked(true);
//                        displayMessage("photo selected");
//                        drawerLayout.closeDrawers();
//                        return true;
//
//                    case R.id.btn_logout:
//                        menuItem.setChecked(true);
//                        // 로그아웃 기능
//                        mFirebaseAuth = FirebaseAuth.getInstance();
//                        mFirebaseAuth.signOut();
//                        displayMessage("로그아웃 성공");
//                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();
//                }
//                return false;
//            }
//        });
    // 회원 탈퇴 기능
    //    mFirebaseAuth.getCurrentUser().delete();

    }
    // 토스트 메시지 메소드
    private void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    // 서랍을 버튼으로 제어하기 위한 메소드
    View.OnClickListener click = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.menu:
                    drawerLayout.openDrawer(navigationView);
                    break;
            }
        }
    };

    private long backpressedTime = 0;

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawers();
        }else{
            if (System.currentTimeMillis() > backpressedTime + 2000) {
                backpressedTime = System.currentTimeMillis();
                displayMessage("'뒤로' 버튼을 한 번 더 누르시면 종료됩니다.");

            } else if (System.currentTimeMillis() <= backpressedTime + 2000) {
                finish();
            }
        }


    }



}
