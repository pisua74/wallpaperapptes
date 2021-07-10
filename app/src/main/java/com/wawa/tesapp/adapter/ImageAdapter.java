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
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wawa.tesapp.R;

public class ImageAdapter extends BaseAdapter {

    private Context mContext;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return mThumbIds[position];
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        SquareImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new SquareImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imageView = (SquareImageView) convertView;
        }


        Glide.with(mContext)
                .load(mThumbIds[position])
//                    .asGif()
                .diskCacheStrategy( DiskCacheStrategy.NONE)
//                    .crossFade()
                .skipMemoryCache(true)
                .int‌​o(imageView);
        return imageView;
    }

    // references to our images
    public Integer[] mThumbIds = {

//            gambar raw atau drawable
//            R.raw.bjada,
            R.raw.a1,
            R.raw.a2,
            R.raw.a3,
            R.raw.a4,
            R.raw.a5,
            R.raw.a6,
            R.raw.a7,
            R.raw.a8,
            R.raw.a9,
            R.raw.a10,
            R.raw.a11,
            R.raw.a12,
            R.raw.a13,
            R.raw.a14,
            R.raw.a15,
            R.raw.a18,
            R.raw.a19,
            R.raw.a20,

    };

}