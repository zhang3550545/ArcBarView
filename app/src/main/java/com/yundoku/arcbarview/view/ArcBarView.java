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


    private int startColor = 0xff082255;
    private int centerColor = 0xff0c5bb9;
    private int endColor = 0xff298DF5;
    private List<Integer> list = new ArrayList<>();
    private int[] defaultColors = new int[]{startColor, centerColor, endColor};
    private Paint paint;
    private Path path;

    public ArcBarView(Context context) {
        super(context);
    }

    public ArcBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = null;
        float arcBarHightToScreenPercentage = 0.0f;
        float arcBarHightToArcPercentage = 0.0f;
        float arcBarWidthToArcPercentage = 0.0f;
        try {
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.ArcBarView);
            arcBarHightToScreenPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarHightToScreenPercentage, 0.0f);
            arcBarHightToArcPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarHightToArcPercentage, 0.0f);
            arcBarWidthToArcPercentage = typedArray.getFloat(R.styleable.ArcBarView_arcBarWidthToArcPercentage, 0.0f);
            startColor = typedArray.getColor(R.styleable.ArcBarView_startColor, 0);
            centerColor = typedArray.getColor(R.styleable.ArcBarView_centerColor, 0);
            endColor = typedArray.getColor(R.styleable.ArcBarView_endColor, 0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }


        int heightPixels = getContext().getResources().getDisplayMetrics().heightPixels;
        float arcBarHight = heightPixels * arcBarHightToScreenPercentage;
        float value = arcBarHight * arcBarHightToArcPercentage;

        initPaint(arcBarHight);
        initPath(arcBarHight, value, arcBarWidthToArcPercentage);

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
        Shader mShader = new LinearGradient(0, 0, 0, arcBarHight, getColors(), null, Shader.TileMode.MIRROR);
        paint.setShader(mShader);
        paint.setStrokeWidth((float) 1.0);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    }

    private int[] getColors() {
        if (startColor != 0) {
            list.add(startColor);
        }
        if (centerColor != 0) {
            list.add(centerColor);
        }
        if (endColor != 0) {
            list.add(endColor);
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

    public ArcBarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.drawPath(path, paint);
    }
}
