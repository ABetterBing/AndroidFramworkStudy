package net.betterbing.androidframworkstudy.activity;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.utils.DensityUtil;
import net.betterbing.androidframworkstudy.utils.ImageLoaderUtils;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class UILActivity extends BaseHeadActivity {
    @Bind(R.id.uil_image1)
    ImageView mImageView1;
    @Bind(R.id.uil_image2)
    ImageView mImageView2;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("UniversialImageLoader加载图片");
        /**
         *
         * 默认情况下，Android使用ARGB_8888
         Android中有四种，分别是：
         ALPHA_8：每个像素占用1byte内存
         ARGB_4444:每个像素占用2byte内存
         ARGB_8888:每个像素占用4byte内存
         RGB_565:每个像素占用2byte内存
         */
        String url = "http://img18.house365.com/newcms/2015/01/29/142252362154c9fce551db6.jpg";
        ImageLoaderUtils.getImageLoaderInstance(getApplicationContext()).displayImage(url, mImageView1);
        //加载本地drawable:  drawable://drawable ID
        ImageLoaderUtils.getImageLoaderInstance(getApplicationContext()).displayImage("drawable://"+R.drawable.a6,mImageView2);

    }

    @Override
    public int getLayout() {
        return R.layout.activity_universialimageloader;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
