package com.example.experiment_day4;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Toolbar tb;
    private ViewPager vp;
    private TabLayout tab;
    private DrawerLayout dl;
    private NavigationView nv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initList();
    }

    private void initList() {
        View headerView = nv.getHeaderView(0);
        ImageView img = headerView.findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "我想开学", Toast.LENGTH_SHORT).show();
            }
        });
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
              switch (menuItem.getItemId()){
                  case R.id.item:
                      phone();
                  break;
              }

                return false;
            }
        });
    }
    private void phone() {
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {
                callphone();

            }
            public void permissionDenied(@NonNull String[] permission) {
                Toast.makeText(MainActivity.this, "获取权限失败", Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.CALL_PHONE);
    }

    private void callphone() {
         Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
    }

    private void initView() {
        tb = (Toolbar) findViewById(R.id.tb);
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
        dl = (DrawerLayout) findViewById(R.id.dl);
        tb.setTitle("标题");
        setSupportActionBar(tb);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, dl, tb, R.string.app_name, R.string.app_name);
        toggle.syncState();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());
        fragments.add(new FFragment());
        ArrayList<String> title = new ArrayList<>();
        title.add("首页");
        title.add("知识体");
        title.add("公众号");
        title.add("导航");
        title.add("项目");
        MainAdapter mainAdapter = new MainAdapter(getSupportFragmentManager(), fragments, title);
        vp.setAdapter(mainAdapter);
        tab.setupWithViewPager(vp);
        tab.getTabAt(0).setIcon(R.drawable.xuan);
        tab.getTabAt(1).setIcon(R.drawable.xuan_a);
        tab.getTabAt(2).setIcon(R.drawable.xuan_b);
        tab.getTabAt(3).setIcon(R.drawable.xuan_c);
        tab.getTabAt(4).setIcon(R.drawable.xuan);
        nv = (NavigationView) findViewById(R.id.nv);

    }
}
