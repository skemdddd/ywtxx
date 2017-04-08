package com.example.dddkj.ywtx.ui;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dddkj.ywtx.Base.BaseActivity;
import com.example.dddkj.ywtx.MyApplication.MyApplication;
import com.example.dddkj.ywtx.R;
import com.example.dddkj.ywtx.Widget.DialogChooseImage;
import com.example.dddkj.ywtx.common.Constant;
import com.example.dddkj.ywtx.utils.RxPhotoUtils;
import com.example.dddkj.ywtx.utils.RxSPUtils;
import com.example.dddkj.ywtx.utils.SPUtils;
import com.orhanobut.logger.Logger;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import cn.pedant.SweetAlert.SweetAlertDialog;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import permissions.dispatcher.NeedsPermission;

import static com.example.dddkj.ywtx.Widget.DialogChooseImage.LayoutType.NO_TITLE;

/**
 * 项目名称：亿我同行
 * <p>
 * 创建时间：2017/3/27 9:18
 */

public class PersonalSettingActivity extends BaseActivity {
    @BindView(R.id.title_back)
    ImageView title_back;
    @BindView(R.id.shipping_address_rlyt)
    RelativeLayout shipping_address_rlyt;
    @BindView(R.id.collect_rlyt)
    RelativeLayout collect_rlyt;
    @BindView(R.id.sex_rl)
    RelativeLayout sex_rl;
    @BindView(R.id.sex_tv)
    TextView sex_tv;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.back_rl)
    RelativeLayout back_rl;
    private Uri resultUri;
    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_personal_setting);
    }

    @Override
    protected void setListener() {
        initView();
        title_back.setOnClickListener(this);
        shipping_address_rlyt.setOnClickListener(this);
        collect_rlyt.setOnClickListener(this);
        sex_rl.setOnClickListener(this);
        back_rl.setOnClickListener(this);

    }

    protected void initView() {

        Resources r = this.getResources();
        resultUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://"
                + r.getResourcePackageName(R.mipmap.personal_head_portrait) + "/"
                + r.getResourceTypeName(R.mipmap.personal_head_portrait) + "/"
                + r.getResourceEntryName(R.mipmap.personal_head_portrait));

        Glide.with(PersonalSettingActivity.this).
                load(RxSPUtils.getString(this,"AVATAR")).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                bitmapTransform(new CropCircleTransformation(mContext)).
                thumbnail(0.5f).
                placeholder(R.mipmap.personal_head_portrait).
                priority(Priority.LOW).
                error(R.mipmap.personal_head_portrait).
                fallback(R.mipmap.personal_head_portrait).
                dontAnimate().
                into(ivAvatar);
    }

    @Override
    protected void Request() {

    }

    @Override
    protected Context getActivityContext() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                MyApplication.getInstance().finishActivity(this);
                break;
            case R.id.shipping_address_rlyt:
                Intent intent = new Intent(this, DeliveryAddrssActivity.class);
                startActivity(intent);
                break;
            case R.id.sex_rl:
                if (sex_tv.getText().toString().equals("男")) {
                    sex_tv.setText("女");
                } else {
                    sex_tv.setText("男");
                }
                break;
            case R.id.collect_rlyt:
                initDialogChooseImage();
                break;
            case R.id.back_rl:
                new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
                        .setContentText("你确定退出登陆？")
                        .setCancelText("取消")
                        .setConfirmText("确定")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Logger.i("123走了");
                                SPUtils.put(PersonalSettingActivity.this, "userid", "", Constant.FILE_NAME);
                                MyApplication.getInstance().setUserid("");
                                sDialog.cancel();
                                finish();
                            }
                        })
                        .show();
                break;


        }
    }
    @NeedsPermission(Manifest.permission.CAMERA)
    public void initDialogChooseImage() {
        DialogChooseImage dialogChooseImage = new DialogChooseImage(this, NO_TITLE);
        dialogChooseImage.getLayoutParams().gravity = Gravity.BOTTOM;
        dialogChooseImage.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case RxPhotoUtils.GET_IMAGE_FROM_PHONE://选择相册之后的处理
                if (resultCode == RESULT_OK) {
//                    RxPhotoUtils.cropImage(ActivityUser.this, );// 裁剪图片
                    initUCrop(data.getData());
                }

                break;
            case RxPhotoUtils.GET_IMAGE_BY_CAMERA://选择照相机之后的处理
                if (resultCode == RESULT_OK) {
                   /* data.getExtras().get("data");*/
//                    RxPhotoUtils.cropImage(ActivityUser.this, RxPhotoUtils.imageUriFromCamera);// 裁剪图片
                    initUCrop(RxPhotoUtils.imageUriFromCamera);
                }

                break;
            case RxPhotoUtils.CROP_IMAGE://普通裁剪后的处理
                Glide.with(PersonalSettingActivity.this).
                        load(RxPhotoUtils.cropImageUri).
                        diskCacheStrategy(DiskCacheStrategy.RESULT).
                        bitmapTransform(new CropCircleTransformation(mContext)).
                        thumbnail(0.5f).
                        placeholder(R.mipmap.personal_head_portrait).
                        priority(Priority.LOW).
                        error(R.mipmap.personal_head_portrait).
                        fallback(R.mipmap.personal_head_portrait).
                        dontAnimate().
                        into(ivAvatar);
//                RequestUpdateAvatar(new File(RxPhotoUtils.getRealFilePath(mContext, RxPhotoUtils.cropImageUri)));
                break;

            case UCrop.REQUEST_CROP://UCrop裁剪之后的处理
                if (resultCode == RESULT_OK) {
                    resultUri = UCrop.getOutput(data);
                    roadImageView(resultUri, ivAvatar);
                    RxSPUtils.putContent(PersonalSettingActivity.this, "AVATAR", resultUri.toString());
                } else if (resultCode == UCrop.RESULT_ERROR) {
                    final Throwable cropError = UCrop.getError(data);
                }
                break;
            case UCrop.RESULT_ERROR://UCrop裁剪错误之后的处理
                final Throwable cropError = UCrop.getError(data);
                break;
            default:
                break;
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
    //从Uri中加载图片 并将其转化成File文件返回
    private File roadImageView(Uri uri, ImageView imageView) {
        Glide.with(PersonalSettingActivity.this).
                load(uri).
                diskCacheStrategy(DiskCacheStrategy.RESULT).
                bitmapTransform(new CropCircleTransformation(mContext)).
                thumbnail(0.5f).
                placeholder(R.mipmap.personal_head_portrait).
                priority(Priority.LOW).
                error(R.mipmap.personal_head_portrait).
                fallback(R.mipmap.personal_head_portrait).
                dontAnimate().
                into(imageView);

        return (new File(RxPhotoUtils.getImageAbsolutePath(this, uri)));
    }

    private void initUCrop(Uri uri) {
        //Uri destinationUri = RxPhotoUtils.createImagePathUri(this);

        SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));

        Uri destinationUri = Uri.fromFile(new File(getCacheDir(), imageName + ".jpeg"));
        Logger.i("123"+destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE, UCropActivity.ROTATE, UCropActivity.ALL);
        //设置隐藏底部容器，默认显示
        //options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryDark));

        //开始设置
        //设置最大缩放比例
        options.setMaxScaleMultiplier(5);
        //设置图片在切换比例时的动画
        options.setImageToCropBoundsAnimDuration(666);
        //设置裁剪窗口是否为椭圆
//        options.setOvalDimmedLayer(true);
        //设置是否展示矩形裁剪框
//        options.setShowCropFrame(false);
        //设置裁剪框横竖线的宽度
//        options.setCropGridStrokeWidth(20);
        //设置裁剪框横竖线的颜色
//        options.setCropGridColor(Color.GREEN);
        //设置竖线的数量
//        options.setCropGridColumnCount(2);
        //设置横线的数量
//        options.setCropGridRowCount(1);

        UCrop.of(uri, destinationUri)
                .withAspectRatio(1, 1)
                .withMaxResultSize(1000, 1000)
                .withOptions(options)
                .start(PersonalSettingActivity.this);
    }

}
