package com.vk.libs.adapter;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;

import java.util.List;

public abstract class CustomViewListAdapter<T, V extends View> extends GenericListAdapter<T, V, V> {

    public CustomViewListAdapter(Context context) {
        super(context);
    }

    public CustomViewListAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @NonNull
    @Override
    protected V createHolder(V view) {
        return view;
    }
}