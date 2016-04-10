package net.betterbing.androidframworkstudy;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by aibb on 16/4/10.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * Stetho是Facebook出品的一个强大的Android调试工具，
         * 使用该工具你可以在Chrome Developer Tools查看App的布局，
         * 网络请求，sqlite，preference，一切都是可视化的操作，
         * 无须自己在去使用adb，也不需要root你的设备。
         * 使用的方式很简单，配置好之后，
         * 在Chrome地址栏输入chrome://inspect
         */
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
