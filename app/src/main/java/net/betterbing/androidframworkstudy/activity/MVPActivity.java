package net.betterbing.androidframworkstudy.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.base.BaseHeadActivity;

import butterknife.Bind;

/**
 * Created by aibb on 16/4/12.
 */
public class MVPActivity extends BaseHeadActivity {
    @Bind(R.id.mvp)
    WebView mWebView;
    @Bind(R.id.title)
    TextView mTitle;

    @Override
    public int getHeadTitle() {
        return 0;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState, Toolbar toolBar) {
        setTitle("MVC,MVP,MVVM");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //此方法可以处理webview 在加载时和加载完成时一些操作
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    // 这里是设置activity的标题， 也可以根据自己的需求做一些其他的操作
                    mTitle.setText("加载完成");
                } else {
                    mTitle.setText("加载中......." + newProgress + "%");
                }
            }
        });
        mWebView.loadUrl("http://www.jianshu.com/p/140e3081253c");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //是back键相应webview后退而不是finish activity
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_mvp;
    }

    @Override
    public String getTag() {
        return this.getClass().getSimpleName().toString();
    }
}
