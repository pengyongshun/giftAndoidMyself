package com.example.giftgenius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DrawerLoginActivity extends AppCompatActivity {

    private Button mBtnResirze;
    private Button mBtnBack;
    private EditText mEtUser;
    private EditText mEtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_login);
        iniToolBar();
        initView();
    }

    private void initView() {
        mEtUser = (EditText) findViewById(R.id.et_drawer_login_user);
        mEtPwd = (EditText) findViewById(R.id.et_drawer_login_pwd);
    }

    /***
     *
     * 初始化ToolBar
     * */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar)findViewById(R.id.toolbar_drawer_show);
        mBtnBack = (Button)findViewById(R.id.btn_drawer_actionbar_back);
        mBtnResirze = (Button) findViewById(R.id.btn_drawer_actionbar_register);
        setSupportActionBar(mToolBbar);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_drawer_actionbar_back:
                finish();
                break;
            case R.id.btn_drawer_actionbar_register:
                //转跳注册界面
                Intent intent=new Intent(DrawerLoginActivity.this,RegistervActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawer_login_button:
                String pwd = mEtPwd.getEditableText().toString();
                String user = mEtUser.getEditableText().toString();
                //点击登录
                if (user.isEmpty()){
                    mEtUser.requestFocus();
                    Toast.makeText(DrawerLoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.isEmpty()){
                    mEtUser.requestFocus();
                    Toast.makeText(DrawerLoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((!user.isEmpty())&&(!pwd.isEmpty())){
                    Toast.makeText(DrawerLoginActivity.this, "USER="+user+"   PWD="+pwd, Toast.LENGTH_SHORT).show();
                }



        }
    }
}
