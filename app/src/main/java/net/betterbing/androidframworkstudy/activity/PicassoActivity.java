package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;
import net.betterbing.androidframworkstudy.utils.DensityUtil;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class PicassoActivity extends BaseHeadActivity {
    @Bind(R.id.picasso_image1)
    ImageView mImageView1;
    @Bind(R.id.picasso_image2)
    ImageView mImageView2;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("Picasso加载图片");
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
        //加载然后resize
        Picasso.with(getApplicationContext())
                .load(url)
                .resize(DensityUtil.dip2px(getApplicationContext(), 250.0f), DensityUtil.dip2px(getApplicationContext(), 250.0f))
                .centerCrop()
                .into(mImageView1);
        //不适用缓存策略
        Picasso.with(getApplicationContext()).load(R.drawable.a6).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .into(mImageView2);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_picasso;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
