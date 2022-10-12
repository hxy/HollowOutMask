package com.hy.hollowoutmask;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Author: hy
 * Date: 2022/9/17
 * Description:
 */
public class FrameLayoutWithScreenshot extends FrameLayout {

    private int mBackgroundColor;

    public FrameLayoutWithScreenshot(Context context, int backgroundColor, View eraserView){
        super(context);
        mBackgroundColor = backgroundColor;
        init(eraserView);
    }
    private void init(View eraserView) {
        eraserView.destroyDrawingCache();
        eraserView.setDrawingCacheEnabled(true);
        eraserView.buildDrawingCache();

        ImageView imageView = new ImageView(getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(eraserView.getWidth(),eraserView.getHeight());
        imageView.setImageBitmap(eraserView.getDrawingCache());
        addView(imageView,layoutParams);
        int[] location = new int[2];
        eraserView.getLocationOnScreen(location);
        imageView.setX(location[0]);
        imageView.setY(location[1]);
        setBackgroundColor(mBackgroundColor);
    }

}
