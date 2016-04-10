package net.betterbing.androidframworkstudy.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ImageLoaderUtils {


    public static ImageLoader getImageLoaderInstance(Context context) {
        DisplayImageOptions.Builder options = new DisplayImageOptions.Builder();
        options.bitmapConfig(Bitmap.Config.RGB_565);
        options.cacheInMemory(true);
        options.cacheOnDisk(true);
//        options.showImageOnFail(R.mipmap.image_loadfail);
//        options.showImageOnLoading(R.mipmap.image_loading);
        ImageLoader loader = ImageLoader.getInstance();
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                context)
                .defaultDisplayImageOptions(options.build())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).build();
        loader.init(configuration);
        return loader;
    }
}
