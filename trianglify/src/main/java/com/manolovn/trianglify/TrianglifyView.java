package com.manolovn.trianglify;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

/**
 * Trianglify view
 *
 * @author manolovn
 */
public class TrianglifyView extends View {

    private TrianglifyDrawable drawable;

    public TrianglifyView(Context context) {
        super(context);
        init(null);
    }

    public TrianglifyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TrianglifyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        int cellSize = Default.cellSize;
        int variance = Default.variance;
        int bleedX = Default.bleedX;
        int bleedY = Default.bleedY;

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.TrianglifyView, 0, 0);

            try {
                cellSize = a.getInteger(R.styleable.TrianglifyView_cellSize, cellSize);
                variance = a.getInteger(R.styleable.TrianglifyView_variance, variance);
                bleedX = a.getInteger(R.styleable.TrianglifyView_bleedX, bleedX);
                bleedY = a.getInteger(R.styleable.TrianglifyView_bleedY, bleedY);
            } finally {
                a.recycle();
            }
        }

        drawable = new TrianglifyDrawable(cellSize, variance, bleedX, bleedY);
        setBackgroundDrawable(drawable);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        drawable.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    public TrianglifyDrawable getDrawable() {
        return drawable;
    }
}
