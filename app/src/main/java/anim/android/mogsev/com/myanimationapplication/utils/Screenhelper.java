package anim.android.mogsev.com.myanimationapplication.utils;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;

/**
 * @author Eugene Sikaylo (mogsev@gmail.com)
 */

public final class ScreenHelper {
    private static final String TAG = ScreenHelper.class.getSimpleName();

    /*
     * Getting screen width and height
     */
    public static Point getScreenSize(@NonNull final Context context) {
        if (context == null) {
            throw new NullPointerException("Context cannot be null");
        }
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        final Point point = new Point();
        try {
            display.getSize(point);
        } catch (java.lang.NoSuchMethodError ignore) { // Older device
            point.x = display.getWidth();
            point.y = display.getHeight();
        }

        return point;
    }
}
