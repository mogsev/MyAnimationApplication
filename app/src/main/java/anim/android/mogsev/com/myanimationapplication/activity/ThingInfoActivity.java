package anim.android.mogsev.com.myanimationapplication.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.viewpagerindicator.UnderlinePageIndicator;

import anim.android.mogsev.com.myanimationapplication.R;
import anim.android.mogsev.com.myanimationapplication.adapter.ImageSliderAdapter;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;

public class ThingInfoActivity extends AppCompatActivity {
    private static final String TAG = ThingInfoActivity.class.getSimpleName();

    private ImageSliderAdapter mAdapter;
    private ViewPager mViewPager;

    private Toolbar toolbar;
    private CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_thing_info);

        initView();

        initToolbar();

        mAdapter = new ImageSliderAdapter(getApplicationContext());
        mViewPager.setAdapter(mAdapter);

        initMoveView();

        initDrawIndicator();

        UnderlinePageIndicator indicator = (UnderlinePageIndicator) findViewById(R.id.indicator);
        indicator.setViewPager(mViewPager);
        indicator.setFades(false);
        indicator.setSelectedColor(Color.GRAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    private void initView() {
        Log.i(TAG, "initView");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        cardView = (CardView) findViewById(R.id.cardView);
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initMoveView() {
        cardView.setOnTouchListener(new View.OnTouchListener() {

            float dX = 0;
            float dY = 0;

            @Override
            public boolean onTouch(View view, MotionEvent event) {
                Log.i(TAG, "view: " + view.getX() + " / " + view.getY());
                Log.i(TAG, "event: " + event.getRawX() + " / " + view.getY());

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        view.animate()
                                .x(event.getRawX() + dX)
                                .y(event.getRawY() + dY)
                                .setDuration(0)
                                .start();
                        break;
                    case MotionEvent.ACTION_UP:
                        dX = view.getX() - event.getRawX();
                        dY = view.getY() + event.getRawY();
                        break;
                    case MotionEvent.ACTION_SCROLL:
                        Log.i(TAG, "scroll");
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });
    }

    private void initDrawIndicator() {
        Indicator indicator = new Indicator(getApplicationContext(), cardView);

        cardView.addView(indicator);
    }

    private static class Indicator extends View {
        private static final String TAG = Indicator.class.getSimpleName();
        private final Paint paint = new Paint(ANTI_ALIAS_FLAG);
        private final Rect rect = new Rect();
        private View view = null;

        public Indicator(final Context context, final View view) {
            super(context);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            this.setLayoutParams(layoutParams);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.YELLOW);
            this.view = view;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            view.getGlobalVisibleRect(rect);
            Log.i(TAG, "rect: " + rect.toString());
            Resources res = getContext().getResources();
            float h = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, res.getDisplayMetrics());
            float y = rect.bottom - h;
            float x = (rect.right - rect.left) / 2;
            canvas.drawCircle(x, y, 30, paint);
        }
    }
}
