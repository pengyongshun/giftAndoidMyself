package com.example.giftgenius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistervActivity extends AppCompatActivity {

    private Button mBtnBack;
    private EditText mEtPhone;
    private EditText mEtNick;
    private EditText mEtPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerv);
        iniToolBar();
        initView();
    }

    private void initView() {
        mEtPhone = (EditText) findViewById(R.id.et_drawer_resrioptr_phone);
        mEtNick = (EditText) findViewById(R.id.et_drawer_resrioptr_nick);
        mEtPwd = (EditText) findViewById(R.id.et_drawer_resrioptr_pwd);
    }

    /***
     *
     * 初始化ToolBar
     * */
    private void iniToolBar() {
        Toolbar mToolBbar = (Toolbar)findViewById(R.id.toolbar_drawer_resrioptr_show);
        mBtnBack = (Button)findViewById(R.id.btn_drawer_resritor_actionbar_back);
        setSupportActionBar(mToolBbar);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_drawer_resritor_actionbar_back:
                finish();
                break;
            case R.id.btn_drawer_resrioptr_button:
                String nick = mEtNick.getEditableText().toString();
                String phone = mEtPhone.getEditableText().toString();
                String pwd = mEtPwd.getEditableText().toString();
                if (nick.isEmpty()){
                    mEtPhone.requestFocus();
                    Toast.makeText(RegistervActivity.this, "匿名不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (phone.isEmpty()){
                    mEtPhone.requestFocus();
                    Toast.makeText(RegistervActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.isEmpty()){
                    mEtPhone.requestFocus();
                    Toast.makeText(RegistervActivity.this, "密码不能 为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegistervActivity.this, "nick="+nick+"  phone="+phone+"   pwd="+pwd, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
