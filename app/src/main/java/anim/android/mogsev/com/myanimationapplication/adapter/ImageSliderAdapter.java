package anim.android.mogsev.com.myanimationapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import anim.android.mogsev.com.myanimationapplication.utils.ScreenHelper;

/**
 * @author Eugene Sikaylo (mogsev@gmail.com)
 */
public class ImageSliderAdapter extends PagerAdapter {
    private static final String TAG = ImageSliderAdapter.class.getSimpleName();

    private Context context;
    private List<String> list = new ArrayList<>();
    private Point point;

    public ImageSliderAdapter(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException("Context can not be null");
        }
        this.context = context;
        this.point = ScreenHelper.getScreenSize(context);
        fillList();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i(TAG, "instantiateItem");
        final FrameLayout frameLayout = new FrameLayout(context);
        final FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);
        frameLayout.setBackgroundColor(Color.BLACK);

        final ImageView imageView = new ImageView(context);
        Log.i(TAG, "point: " + point.toString());
        Picasso.with(context)
                .load(list.get(position))
                .resize(300, 200)
                .centerInside()
                .into(imageView);
        frameLayout.addView(imageView);
        container.addView(frameLayout);

        return frameLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((FrameLayout) object);
    }

    public void setList(@NonNull List<String> list) {
        if (list == null) {
            throw new NullPointerException("List can not be null");
        }
        this.list = list;
        notifyDataSetChanged();
    }

    private void fillList() {
        list.add("http://www.tombcare.com:8000/media/cache/da/c0/dac0a2692ec51e822a2afc14bf39eec3.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/60/45/6045379bad44368fc40d0dbfa64669a1.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/a4/e6/a4e6f36f5411ab473926ec0f4047fb73.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/02/02/02023cd08b48a082d7532a62fa73dca0.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/a2/5d/a25d626bcdd9a1595f7458c91397d69e.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/fe/7a/fe7a3d63541833319056c9e55a022a91.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/b7/e5/b7e5a69743149c3ad57d2f1b7e8dc4ef.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/3e/05/3e05ca6b85779ebd9b46153253409e29.jpg");
        list.add("http://www.tombcare.com:8000/media/cache/63/d3/63d3f490ccba929d2961e54ffec0c078.jpg");
    }
}
