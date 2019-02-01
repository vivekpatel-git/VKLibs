package com.vk.libs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import androidx.annotation.NonNull;

import java.util.Collections;
import java.util.List;

public abstract class GenericListAdapter<T, H, V extends View> extends BaseAdapter {

    @NonNull
    protected abstract V createView(Context context, ViewGroup parent);
    @NonNull
    protected abstract H createHolder(V view);
    protected abstract void setModel(H row, T data);

    private List<T> data;
    private Context context;

    public GenericListAdapter(Context context) {
        this(context, Collections.<T>emptyList());
    }

    public GenericListAdapter(Context context, List<T> list) {
        data = list;
        this.context = context;
    }

    public void setData(@NonNull List<T> list) {
        data = list;
        notifyDataSetChanged();
    }

    private int getDataCount() {
        return data == null ? 0 : data.size();
    }

    private boolean isEmptyData() {
        return getDataCount() == 0;
    }

    @Override
    public int getCount() {
        return isEmptyData() ? (hasEmpty() ? 1 : 0) : getDataCount();
    }

    @Override
    public Object getItem(int position) {
        return getCastItem(position);
    }

    public T getCastItem(int position) {
        return (position < 0 || isEmptyData() || position >= data.size()) ? null : data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public int getViewTypeCount() {
        return hasEmpty() ? 2 : 1;
    }

    @Override
    public int getItemViewType(int position) {
        return isEmptyData() ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (isEmptyData()) {
            if (convertView == null) {
                convertView = createEmptyView(context, parent);
                if (convertView != null) {
                    convertView.setClickable(true);
                    convertView.setFocusable(false);
                }
            }
            return convertView;
        }

        V view;
        if (convertView == null) {
            view = createView(context, parent);
            view.setTag(createHolder(view));
        } else {
            //noinspection unchecked
            view = (V) convertView;
        }
        //noinspection unchecked
        setModel((H) view.getTag(), data.get(position));

        return view;
    }

    protected boolean hasEmpty() {
        return false;
    }

    protected View createEmptyView(Context context, ViewGroup parent) {
        return null;
    }
}