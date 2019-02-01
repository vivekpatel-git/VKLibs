package com.vk.libs.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.vk.libs.R;
import com.vk.libs.adapter.CustomViewListAdapter;

import java.util.List;

public abstract class BottomListDialog<T> extends BaseBottomDialog {

    public abstract void setItem(TextView textView, T t, boolean isSelected);

    private ListView listView;
    private CustomViewListAdapter<T, TextView> adapter;
    private Listener<T> listener;
    private T selected;

    public interface Listener<T> {
        void onItemSelected(T item);
    }

    public BottomListDialog(@NonNull Activity activity) {
        super(activity);

        listView = new ListView(activity);
        listView.setDivider(null);
        setBody(listView);

        adapter = new CustomViewListAdapter<T, TextView>(activity) {

            @NonNull
            @Override
            protected TextView createView(Context context, ViewGroup viewGroup) {
                return (TextView) LayoutInflater.from(context).inflate(R.layout.bottom_list_dialog_item, viewGroup, false);
            }

            @Override
            protected void setModel(TextView textView, T t) {
                setItem(textView, t, t.equals(selected));
            }
        };

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setSelectedItem(adapter.getCastItem(position));
            }
        });
    }

    public void setListener(Listener<T> listener) {
        this.listener = listener;
    }

    public void setList(List<T> items) {
        adapter.setData(items);
    }

    public void setDevider(ColorDrawable devider, int height) {
        listView.setDivider(devider);
        listView.setDividerHeight(height);
    }

    public void setSelectedItem(T item) {
        selected = item;
        adapter.notifyDataSetInvalidated();
    }

    @Override
    protected boolean onDoneClicked() {
        if (listener != null) {
            listener.onItemSelected(selected);
        }
        return true;
    }
}
