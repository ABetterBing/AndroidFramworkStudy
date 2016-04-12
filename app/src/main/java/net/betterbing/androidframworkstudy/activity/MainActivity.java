package net.betterbing.androidframworkstudy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import net.betterbing.androidframworkstudy.R;
import net.betterbing.androidframworkstudy.fragment.ViewPagerFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.betterbing.net/"));
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.button_recyclerview)
    void onRecyclerViewClick() {
        startIntent(RecyclerViewActivity.class);
    }

    @OnClick(R.id.button_bottomsheet)
    void onBottomSheetsClick() {
        startIntent(BottomSheetActivity.class);
    }

    @OnClick(R.id.button_drawlayout)
    void onDrawlayoutClick() {
        startIntent(DrawLayoutActivity.class);
    }

    @OnClick(R.id.button_viewpager)
    void onViewPagerClick() {
        startIntent(ViewPagerActivity.class);
    }

    @OnClick(R.id.button_PullToRefresh)
    void onPulltoRefreshClick() {
        startIntent(PulltorefreshActivity.class);
    }

    @OnClick(R.id.button_glide)
    void onGlideClick() {
        startIntent(GlideActivity.class);
    }

    @OnClick(R.id.button_picasso)
    void onPicassoClick() {
        startIntent(PicassoActivity.class);
    }

    @OnClick(R.id.button_universalimageloader)
    void onUILClick() {
        startIntent(UILActivity.class);
    }

    @OnClick(R.id.button_retrofit)
    void onRetrofitClick() {
        startIntent(RetorfitActivity.class);
    }

    @OnClick(R.id.button_okhttp)
    void onOKhttpClick() {
        startIntent(OKHttpActivity.class);
    }

    @OnClick(R.id.button_customview_ondraw)
    void onCustomViewOnDrawClick() {
        startIntent(CustomViewByOverrideOnDraw.class);
    }

    @OnClick(R.id.button_customview_extends_viewgroup)
    void onCustomViewExtendsViewGroupClick() {
        startIntent(CustomViewByExtendsViewGroup.class);
    }

    @OnClick(R.id.button_MVP)
    void onMVPClick() {
        startIntent(MVPActivity.class);
    }

    private void startIntent(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
