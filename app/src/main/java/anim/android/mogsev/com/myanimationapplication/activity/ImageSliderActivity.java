package anim.android.mogsev.com.myanimationapplication.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import java.util.List;

import anim.android.mogsev.com.myanimationapplication.R;
import anim.android.mogsev.com.myanimationapplication.adapter.ImageSliderAdapter;

public class ImageSliderActivity extends AppCompatActivity {
    private static final String TAG = ImageSliderActivity.class.getSimpleName();

    public static String DATA_LIST_STRING = "data_list_string";
    public static String TRANSITION_IMAGE_NAME = "image_slider";

    private ViewPager mViewPager;
    private ImageSliderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_image_slider);

        // set toolbar home button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // initialize view elements
        initView();

        mAdapter = new ImageSliderAdapter(getApplicationContext());
        mViewPager.setAdapter(mAdapter);
        Intent intent = getIntent();
        if (intent != null) {
            List<String> list = intent.getStringArrayListExtra(DATA_LIST_STRING);
            if (list != null && !list.isEmpty()) mAdapter.setList(list);
        }
    }

    private void initView() {
        Log.i(TAG, "initView");
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    /**
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //onBackPressed();
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
     */

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
