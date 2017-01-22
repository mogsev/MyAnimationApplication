package anim.android.mogsev.com.myanimationapplication;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mButton;
    private ImageView mImageView;
    private View someView;
    private Button btn2;
    private Button btnTimeDialog;
    private Button btnFragmentMenu;
    private Button btnRx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        mImageView = (ImageView) findViewById(R.id.ivMain);
        mButton = (Button) findViewById(R.id.btnMain);
        someView = (View) findViewById(R.id.llSomeView);
        btn2 = (Button) findViewById(R.id.btn2);

        final TextView tvCard = (TextView) findViewById(R.id.tvCard);
        Button btnCard = (Button) findViewById(R.id.btnCardOk);

        Button btnRx = (Button) findViewById(R.id.btnRx);
        btnRx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BasicActivity.class));
            }
        });

        btnCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvCard.setVisibility((tvCard.getVisibility() == View.VISIBLE) ? View.GONE : View.VISIBLE);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mImageView.setTranslationY(500);
                //mImageView.animate().translationY(700).setDuration(300).start();
                //mImageView.getViewTreeObserver().addOnPreDrawListener(new MainViewTreeObserver(mImageView));
                //someView.getViewTreeObserver().addOnPreDrawListener(new SomeViewTreeObserver(getBaseContext(), someView));
                someView.setVisibility(someView.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ObjectAnimator scaleDown = ObjectAnimator.ofPropertyValuesHolder(v,
                        PropertyValuesHolder.ofFloat("scaleX", 0.0f),
                        PropertyValuesHolder.ofFloat("scaleY", 0.0f));
                scaleDown.setDuration(600);
                scaleDown.start();
/**
                btn2.animate().scaleX(0.0f).scaleYBy(0.0f).setDuration(300).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        btn2.setVisibility(View.GONE);
                    }
                });
 */
            }

        });

        // create time dialog
        btnTimeDialog = (Button) findViewById(R.id.btnTimeDialog);
        btnTimeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "obClick btnTimeDialog");
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Time dialog");
                LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                ViewGroup viewGroup = (ViewGroup) findViewById(android.R.id.content);
                View viewDialog = layoutInflater.inflate(R.layout.item_full_time_dialog, viewGroup, false);
                NumberPicker npDay = (NumberPicker) viewDialog.findViewById(R.id.npDay);
                npDay.setMinValue(0);
                npDay.setMaxValue(31);
                NumberPicker npMonth = (NumberPicker) viewDialog.findViewById(R.id.npMonth);
                npMonth.setMinValue(0);
                npMonth.setMaxValue(12);
                NumberPicker npYear = (NumberPicker) viewDialog.findViewById(R.id.npYear);
                npYear.setMinValue(1901);
                npYear.setMaxValue(2017);
                builder.setView(viewDialog);
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog alert = builder.create();
                alert.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);
                        btn.setPadding(160, 0, 160, 0);
                    }
                });

                alert.show();
            }
        });

        btnFragmentMenu = (Button) findViewById(R.id.btnFragmentMenu);
        btnFragmentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(android.R.id.content, new MenuFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }




    private static class MainViewTreeObserver implements ViewTreeObserver.OnPreDrawListener {
        private static final String TAG = MainViewTreeObserver.class.getSimpleName();

        private ImageView imageView;

        public MainViewTreeObserver(@NonNull ImageView button) {
            this.imageView = button;

        }

        @Override
        public boolean onPreDraw() {
            Log.i(TAG, "onPreDraw");
            imageView.getViewTreeObserver().removeOnPreDrawListener(this);
            if (imageView.getVisibility() == View.GONE) {
                imageView.setVisibility(View.VISIBLE);
                ValueAnimator animator = ValueAnimator.ofInt(0, 128);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Log.i(TAG, "onAnimationUpdate");
                        int val = (Integer) animation.getAnimatedValue();
                        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
                        Log.i(TAG, "Val: " + val + "params: " + layoutParams.height);
                        layoutParams.height = val;
                        imageView.setLayoutParams(layoutParams);
                    }
                });
                animator.start();
            } else {
                imageView.setVisibility(View.GONE);
            }
            return false;
        }
    }

    private static class SomeViewTreeObserver implements ViewTreeObserver.OnPreDrawListener {
        private static final String TAG = SomeViewTreeObserver.class.getSimpleName();

        private Context context;
        private View view;

        public SomeViewTreeObserver(@NonNull Context context, @NonNull View button) {
            this.context = context;
            this.view = button;
        }

        @Override
        public boolean onPreDraw() {
            Log.i(TAG, "onPreDraw");
            view.getViewTreeObserver().removeOnPreDrawListener(this);
            ValueAnimator animator = null;

            if (view.getVisibility() == View.GONE) {
                view.setVisibility(View.VISIBLE);
                view.invalidate();
                Log.i(TAG, "Params: " + view.getMeasuredHeight() + " / " + view.getHeight() + " / " + view.getTranslationY());
                animator = ValueAnimator.ofInt(0, 128);

            } else {
                animator = ValueAnimator.ofInt((int) view.getMeasuredHeight(), 0);
                animator.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });

            }

            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.i(TAG, "onAnimationUpdate");
                    int val = (Integer) animation.getAnimatedValue();
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    Log.i(TAG, "Val: " + val + "params: " + layoutParams.height);
                    layoutParams.height = val;
                    view.setLayoutParams(layoutParams);
                }
            });
            animator.start();

            return false;
        }
    }
}
