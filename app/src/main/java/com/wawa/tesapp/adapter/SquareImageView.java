/*
 * Created By Hendri Saputra on 6/2/17 5:51 PM
 * Copyright (c) 2017. All right reserved.
 *
 * Last modified 6/2/17 5:51 PM
 */

package com.wawa.tesapp.adapter;

/**
 * Created by pisua on 6/2/2017.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends ImageView {
    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}