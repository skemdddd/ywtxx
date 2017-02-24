package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.utils.NetUtils;
import com.example.dddkj.ywtx.utils.T;
import com.example.dddkj.ywtx.utils.TimerCount;

import butterknife.BindView;

/**
 * 项目名称：亿我同行
 * <登陆界面>
 * 创建时间：2017/2/7 14:49
 */
//^1+[34578]+\\d{9}
public class LoginAcivity extends BaseActivity  {
    @BindView(R.id.title_back)
//    返回
    ImageView mBack;
//    手机号
    @BindView(R.id.phoneNumber_edtText)
    EditText mPhoneNumber;
//    验证码
    @BindView(R.id.verificationCode_edtText)
    EditText mVerificationCode;
//    登陆
    @BindView(R.id.logIn_btn)
    Button mLogIn;
//    验证码
    @BindView(R.id.sendVerification_btn)
    Button mSendVerification;


    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_login);
    }



    @Override
    protected void setListener() {
        mBack.setOnClickListener(this);
        mLogIn.setOnClickListener(this);
        mSendVerification.setOnClickListener(this);
    }

    @Override
    protected void Request() {

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.title_back:
            finish();
             break;
         case R.id.logIn_btn:
             if(mPhoneNumber.length()== 0){
                 T.showLong(LoginAcivity.this,"手机号不能为空！");
                 return;
             }
             if (!isMobileNO(mPhoneNumber.getText().toString())){
                 T.showLong(LoginAcivity.this,"手机号不正确");
                 return;
             }
             if(mVerificationCode.length() == 0){
                 T.showLong(LoginAcivity.this,"验证码不能位空！");
                 return;
             }
             if (!NetUtils.isConnected(LoginAcivity.this))
             {
                 T.showToast(LoginAcivity.this,"无网络");
                 return;
             }
             break;
         case R.id.sendVerification_btn:
             TimerCount timer  = new TimerCount(60000,1000,mSendVerification);
             timer.start();
             break;


     }

    }
    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3458]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }
    public void Submit(){

    }
}
