package com.vk.libs.dialog;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.vk.libs.R;

public class BaseBottomDialog {

    private BottomSheetDialog dialog;
    private TextView cancelView;
    private TextView titleView;
    private TextView doneView;
    private LinearLayout bodyView;

    public BaseBottomDialog(@NonNull Activity activity) {
        View root = LayoutInflater.from(activity).inflate(R.layout.base_bottom_dialog, null);
        cancelView = (TextView) root.findViewById(R.id.btnCancel);
        titleView = (TextView) root.findViewById(R.id.txtTitle);
        doneView = (TextView) root.findViewById(R.id.btnDone);
        bodyView = (LinearLayout) root.findViewById(R.id.layout_body);
        dialog = new BottomSheetDialog(activity);
        dialog.setContentView(root);

        doneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onDoneClicked()) {
                    dialog.dismiss();
                }
            }
        });

        root.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    protected boolean onDoneClicked() {
        return true;
    }

    public void setTitle(CharSequence title) {
        titleView.setText(title);
    }

    public void setTitle(int id) {
        titleView.setText(id);
    }

    public void setDoneButtonText(CharSequence buttonText) {
        doneView.setText(buttonText);
    }

    public void setCancelButtonText(CharSequence buttonText) {
        cancelView.setText(buttonText);
    }

    public void setDoneButtonBackground(int color) {
        doneView.setBackgroundColor(color);
    }

    public void setCancelButtonBackground(int color) {
        cancelView.setBackgroundColor(color);
    }

    public void setDoneButtonTextColor(int color) {
        doneView.setTextColor(color);
    }

    public void setCancelButtonTextColor(int color) {
        cancelView.setTextColor(color);
    }

    public void setBody(View... columns) {
        bodyView.removeAllViews();
        if (columns != null) {
            for (View view : columns) {
                bodyView.addView(view, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f));
            }
        }
    }

    public void setCancelable(boolean cancelable){
        dialog.setCancelable(cancelable);
    }

    public void setDoneEnabled(boolean enabled) {
        doneView.setEnabled(enabled);
    }

    public void show() {
        dialog.show();
    }
}
