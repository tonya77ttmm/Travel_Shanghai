package com.chen.fy.wisdomscenicspot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.chen.fy.wisdomscenicspot.R;
import com.chen.fy.wisdomscenicspot.model.Visitor;
import com.chen.fy.wisdomscenicspot.utils.LoginRegisterUtils;
import com.chen.fy.wisdomscenicspot.utils.UiUtils;

import org.litepal.LitePal;

import java.util.List;

public class ModifyAgeActivity extends AppCompatActivity {

    private EditText modify_age_et;
    /**
     * 当前用户登入的账号
     */
    private String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_age_layout);

        //初始化View
        initView();
        //获取当前用户登入账号
        getUserId();
    }

    /**
     * 初始化View
     */
    private void initView() {
        //设置toolbar
        Toolbar toolbar = findViewById(R.id.toolbar_modify_age);
        setSupportActionBar(toolbar);

        modify_age_et = findViewById(R.id.modify_age_et);
        LoginRegisterUtils.setEditTextInhibitInputSpace(modify_age_et);

        //自动弹出输入法
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        //标题栏返回键
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //将状态栏字体变为黑色
        UiUtils.changeStatusBarTextImgColor(this,true);
    }

    /**
     * 获取当前用户登入账号
     */
    private void getUserId() {
        Intent intent = getIntent();
        if (intent != null) {
            userId = intent.getStringExtra("userId");
        }
    }

    //反射右上角菜单项
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.modify_info, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //点击菜单项
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.modify_save:
                String age = modify_age_et.getText().toString();
                //Toast.makeText(this,age,Toast.LENGTH_LONG).show();
                if (!age.isEmpty()) {
                    List<Visitor> visitors = LitePal.where("userId = ?", userId).find(Visitor.class);
                    for (Visitor visitor : visitors) {
                        visitor.setAge(age);
                        boolean flag=visitor.save();
                        if (flag)
                        {Toast.makeText(this,"成功",Toast.LENGTH_LONG).show();}
                        else
                        {
                            Toast.makeText(this,"失败",Toast.LENGTH_LONG).show();
                        }
                    }
                    finish();
                } else {
                    Toast.makeText(this, "年龄不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}