package com.example.dddkj.ywtx.ui;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.Entity.LogIn;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.common.RequesURL;
import com.example.dddkj.ywtx.utils.NetUtils;
import com.example.dddkj.ywtx.utils.SPUtils;
import com.example.dddkj.ywtx.utils.T;
import com.example.dddkj.ywtx.utils.TimerCount;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 项目名称：亿我同行
 * <登陆界面>
 * 创建时间：2017/2/7 14:49
 */
//^1+[34578]+\\d{9}
public class LoginAcivity extends BaseActivity {
    //    返回
    @BindView(R.id.title_back)
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
        Logger.i("登陆界面");
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
        switch (view.getId()) {
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(LoginAcivity.this);
                break;
            case R.id.logIn_btn:
                if (mPhoneNumber.length() == 0) {
                    T.showLong(LoginAcivity.this, "手机号不能为空！");
                    return;
                }
                if (!isMobileNO(mPhoneNumber.getText().toString())) {
                    T.showLong(LoginAcivity.this, "手机号不正确");
                    return;
                }
                if (mVerificationCode.length() == 0) {
                    T.showLong(LoginAcivity.this, "验证码不能位空！");
                    return;
                }
                if (!NetUtils.isConnected(LoginAcivity.this)) {
                    T.showToast(LoginAcivity.this, "无网络");
                    return;
                }

                final SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.setCancelable(false);
                OkGo.post(RequesURL.REGISTER)
                        .tag(this)
                        .params("phone", mPhoneNumber.getText().toString())
                        .params("code", mVerificationCode.getText().toString())
                        .cacheKey("cacheKey")
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new StringCallback() {
                            @Override
                            public void onAfter(String s, Exception e) {
                                super.onAfter(s, e);
                                Gson gson = new Gson();
                                LogIn logIn = gson.fromJson(s, LogIn.class);
                                if (logIn.getCode().equals("200")) {
                                    Logger.i(logIn.getCode());
                                    SPUtils.put(LoginAcivity.this, "userid", logIn.getUserid(), Constant.FILE_NAME);
                                    MyApplication.getInstance().setUserid(logIn.getUserid());
                                    MyApplication.getInstance().finishActivity(LoginAcivity.this);

                                    T.showShort(LoginAcivity.this, logIn.getMessage());
                                } else {
                                    T.showShort(LoginAcivity.this, logIn.getMessage());
                                }
                                Logger.json(s);

                            }

                            @Override
                            public void onBefore(BaseRequest request) {
                                super.onBefore(request);
                                pDialog.setTitleText("Loading");
                                pDialog.setCancelable(false);
                                pDialog.show();


                            }

                            @Override
                            public void onSuccess(String s, Call call, Response response) {

                            }
                        });
                break;
            case R.id.sendVerification_btn:
                if (mPhoneNumber.length() == 0) {
                    T.showLong(LoginAcivity.this, "手机号不能为空！");
                    return;
                }
                if (!isMobileNO(mPhoneNumber.getText().toString())) {
                    T.showLong(LoginAcivity.this, "手机号不正确");
                    return;
                }
                TimerCount timer = new TimerCount(60000, 1000, mSendVerification);
                timer.start();
                OkGo.post(RequesURL.CODEFIND)
                        .tag(this)
                        .params("phone", mPhoneNumber.getText().toString())
                        .cacheKey("cacheKey")
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new StringCallback() {
                            @Override
                            public void onAfter(String s, Exception e) {
                                super.onAfter(s, e);
                                Logger.json(s);
                                T.showShort(LoginAcivity.this, "验证码发送成功！");
                            }

                            @Override
                            public void onBefore(BaseRequest request) {
                                super.onBefore(request);
                            }

                            @Override
                            public void onSuccess(String s, Call call, Response response) {

                            }
                        });
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
        String telRegex = "[1][34578]\\d{9}";
        if (TextUtils.isEmpty(mobiles)) return false;
        else return mobiles.matches(telRegex);
    }

    public void Submit() {

    }
}
