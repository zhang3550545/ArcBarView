package com.yundoku.arcbarview.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.yundoku.arcbarview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Widsom Zhang on 2017/4/13.
 */

public class ArcBarView extends View {


    private int startGradientColor = 0xff082255;
    private int centerGradientColor = 0xff0c5bb9;
    private int endGradientColor = 0xff298DF5;
    private int arcBarColor = 0xffffffff;
    private List<Integer> list = new ArrayList<>();
    private int[] defaultColors = new int[]{startGradientColor, centerGradientColor, endGradientColor};
    private Paint paint;
    private Path path;
    private boolean isShowGradientColor;

    private float arcBarHightToScreenPercentage = 0.0f;
    private float arcBarHightToArcPercentage = 0.0f;
    private float arcBarWidthToArcPercentage = 0.0f;

    public ArcBarView(Context context) {
        super(context);
    }

    public ArcBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = null;

        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcBarView);
            arcBarHightToScreenPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarHightToScreenPercentage, 0.0f);
            arcBarHightToArcPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarHightToArcPercentage, 0.0f);
            arcBarWidthToArcPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarWidthToArcPercentage, 0.0f);
            arcBarColor = typedArray.getColor(R.styleable.ArcBarView_arcBarColor, arcBarColor);
            isShowGradientColor = typedArray.getBoolean(R.styleable.ArcBarView_arcBarShowGradientColor, false);
            startGradientColor = typedArray.getColor(R.styleable.ArcBarView_arcBarStartGradientColor, startGradientColor);
            centerGradientColor = typedArray.getColor(R.styleable.ArcBarView_arcBarCenterGradientColor, centerGradientColor);
            endGradientColor = typedArray.getColor(R.styleable.ArcBarView_arcBarEndGradientColor, endGradientColor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }

        init();
    }

    private void init() {
        int heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
        float arcBarHight = heightPixels * arcBarHightToScreenPercentage;
        float value = arcBarHight * arcBarHightToArcPercentage;
        initPaint(arcBarHight);
        initPath(arcBarHight, value, arcBarWidthToArcPercentage);
    }

    public ArcBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
    }

    private void initPath(float arcBarHight, float value, float widthPercentage) {

        int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;

        path = new Path();
        path.addRect(0, 0, widthPixels, arcBarHight - value, Path.Direction.CW);

        RectF oval = new RectF();
        oval.left = -widthPixels * widthPercentage;
        oval.top = arcBarHight - value * 2;
        oval.right = widthPixels + widthPixels * widthPercentage;
        oval.bottom = arcBarHight;
        path.addArc(oval, 0, 180);
        path.setFillType(Path.FillType.WINDING);
    }

    private void initPaint(float arcBarHight) {
        paint = new Paint();
        paint.setAntiAlias(true);
        if (isShowGradientColor) {
            Shader mShader = new LinearGradient(0, 0, 0, arcBarHight, getColors(), null, Shader.TileMode.MIRROR);
            paint.setShader(mShader);
        } else {
            paint.setColor(arcBarColor);
        }
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private int[] getColors() {
        if (startGradientColor != 0) {
            list.add(startGradientColor);
        }
        if (centerGradientColor != 0) {
            list.add(centerGradientColor);
        }
        if (endGradientColor != 0) {
            list.add(endGradientColor);
        }
        int size = list.size();
        int[] colors = new int[size];

        for (int i = 0; i < size; i++) {
            colors[i] = list.get(i);
        }
        //渐变色颜色值必须大于2
        if (colors.length < 2) {
            colors = defaultColors;
        }
        return colors;
    }
}
