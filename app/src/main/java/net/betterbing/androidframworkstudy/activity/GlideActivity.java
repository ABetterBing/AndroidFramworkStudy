package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/8.
 */
public class GlideActivity extends BaseHeadActivity {
    @Bind(R.id.glide_image1)
    ImageView mImageView1;
    @Bind(R.id.glide_image2)
    ImageView mImageView2;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        /**
         *
         * 1.Glide.with(context).resumeRequests()和 Glide.with(context).pauseRequests()

         当列表在滑动的时候，调用pauseRequests()取消请求，滑动停止时，调用resumeRequests()恢复请求。这样是不是会好些呢？

         2.Glide.clear()    当你想清除掉所有的图片加载请求时，这个方法可以帮助到你。

         3.ListPreloader    如果你想让列表预加载的话，不妨试一下ListPreloader这个类。
         */
        setTitle("Glide加载图片");
        String url = "http://img18.house365.com/newcms/2015/01/29/142252362154c9fce551db6.jpg";
        Glide.with(this).load(url).placeholder(R.mipmap.ic_launcher).crossFade().into(mImageView1);
        Glide.with(this).load(R.drawable.a5).into(mImageView2);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_glide;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
