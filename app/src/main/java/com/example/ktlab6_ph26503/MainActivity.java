package com.example.ktlab6_ph26503;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {
    ServiceFragment serviceFragment= new ServiceFragment();
    InfoFragment infoFragment=new InfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1,btn2;
        FragmentManager fragmentManager=getSupportFragmentManager();
        BottomNavigationView bottomNavigationView =findViewById(R.id.bottomnav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container1,serviceFragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_service:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container1,serviceFragment).commit();
                    case R.id.nav_info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container1,infoFragment).commit();
                }
                return false;
            }
        });

    }

}