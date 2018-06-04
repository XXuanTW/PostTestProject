package com.example.xuan.postproject;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText QRcodeEdit;
    EditText TitleEdit;
    Toolbar toolbar;
    NavigationView liftmenu;
    DrawerLayout mainlayout;
    Button PostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        liftmenu = (NavigationView)findViewById(R.id.liftmenu);
        mainlayout = (DrawerLayout)findViewById(R.id.main_layout);
        PostButton = (Button)findViewById(R.id.PostButton);
        QRcodeEdit = (EditText)findViewById(R.id.QREditTest);
        TitleEdit = (EditText)findViewById(R.id.titleEditTest);

        SetToolBar();
        SetLifeMenu();

        PostButton.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                // http://www.itioi.com/API_PHP_DEMO.php
                new Thread()
                {
                    public void run()
                    {
                        try{

                            HttpClient httpClient = new DefaultHttpClient();
                            HttpPost httpPost = new HttpPost("http://www.itioi.com/API_PHP_DEMO.php");
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("textName", TitleEdit.getText().toString()));
                            params.add(new BasicNameValuePair("textQRcode", QRcodeEdit.getText().toString()));
                            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"UTF-8");
                            httpPost.setEntity(entity);
                            HttpResponse httpResponse =  httpClient.execute(httpPost);
                            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                                HttpEntity entity2 = httpResponse.getEntity();

                            }
                        }catch(Exception e){e.printStackTrace();}
                    };
                }.start();
            }
        });

    }


    //ToolBar
    private void SetToolBar() {

        toolbar.setTitle("POSThttp");

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);//toolbar 上面


                ActionBarDrawerToggle mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mainlayout, toolbar,R.string.drawer_open,R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mActionBarDrawerToggle.syncState();
    }

    //左側表單
    private void SetLifeMenu() {
        liftmenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // 點選時收起選單
                mainlayout.closeDrawer(GravityCompat.START);
                // 取得選項id
                int id = item.getItemId();
                // 依照id判斷點了哪個項目並做相應事件
                if (id == R.id.action_Home) {
                    Toast.makeText(MainActivity.this, "目前顯示頁面：盤點系統", Toast.LENGTH_SHORT).show();
                }else if(id == R.id.action_setting){
//                    Intent intent = new Intent();
//                    intent.setClass(MainActivity.this, MainSetting.class);
//                    MainActivity.this.finish();
//                    startActivity(intent);
//                    return true;
                }
                return false;
            }
        });
    }
}
