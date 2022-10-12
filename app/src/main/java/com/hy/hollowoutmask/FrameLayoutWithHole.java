package com.hy.hollowoutmask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Author: hy
 * Date: 2022/9/17
 * Description:
 */
public class FrameLayoutWithHole extends FrameLayout {
    private Bitmap mEraserBitmap;
    private Canvas mEraserCanvas;
    private Paint mEraser;

    private int mBackgroundColor;
    private RectF eraserRect;


    public FrameLayoutWithHole(Context context, int backgroundColor, View eraserView){
        super(context);
        mBackgroundColor = backgroundColor;
        init(eraserView);
    }
    private void init(View eraserView) {
        //--------------------------------------------------
        setWillNotDraw(false);

        Point size = new Point();
        size.x = getContext().getResources().getDisplayMetrics().widthPixels;
        //此处得到的高度会差一些(推测是状态栏高度)，所以加上一个比较大的数，使能盖住整个屏幕
        size.y = getContext().getResources().getDisplayMetrics().heightPixels+100;
        mBackgroundColor  = mBackgroundColor !=-1?mBackgroundColor: Color.parseColor("#55000000");

        mEraserBitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888);

        mEraserCanvas = new Canvas(mEraserBitmap);


        mEraser = new Paint();
        mEraser.setColor(0xFFFFFFFF);
        mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mEraser.setFlags(Paint.ANTI_ALIAS_FLAG);

        int[] location = new int[2];
        eraserView.getLocationOnScreen(location);
        eraserRect = new RectF();
        eraserRect.left = location[0];
        eraserRect.top = location[1];
        eraserRect.right = location[0]+eraserView.getWidth();
        eraserRect.bottom = eraserRect.top+eraserView.getHeight();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mEraserBitmap.eraseColor(Color.TRANSPARENT);
        mEraserCanvas.drawColor(mBackgroundColor);
//        float radius = DensityUtil.dp2px(12f);
        float radius = 0;
        mEraserCanvas.drawRoundRect(eraserRect, radius,radius,mEraser);
        canvas.drawBitmap(mEraserBitmap, 0, 0, null);
    }
}
