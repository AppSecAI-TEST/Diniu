package com.workapp.auto.carterminal.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.workapp.auto.carterminal.R;


/**
 * Created by wangguibin on 2017/8/04.
 */

public class SelectDialog extends Dialog {
    public SelectDialog(Context context) {
        super(context);
    }

    public SelectDialog(Context context, int themeResId) {
        super(context, themeResId);
    }
    public static class Builder{
        private String positiveButtonText;
        private String negativeButtonText;
        private Context context;
        private OnClickListener positiveButtonClickListener;
        private OnClickListener negativeButtonClickListener;
        public Builder(Context context) {
            this.context=context;
        }
        public Builder setPositiveButton(OnClickListener listener) {
            this.positiveButtonClickListener = listener;
            return this;
        }
        public Builder setNegativeButton(OnClickListener listener) {
            this.negativeButtonClickListener = listener;
            return this;
        }
        public SelectDialog create(){
            Button bt_video;
            Button bt_photo;
            LinearLayout ll_cancel;
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SelectDialog dialog = new SelectDialog(context, R.style.selctVideoDialog);
            View layout = layoutInflater.inflate(R.layout.dialog_select, null);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            bt_photo= (Button) layout.findViewById(R.id.bt_photo);
            bt_video= (Button) layout.findViewById(R.id.bt_video);
            ll_cancel= (LinearLayout) layout.findViewById(R.id.ll_cancel);
            ll_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            if (positiveButtonClickListener != null) {
                bt_photo .setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                positiveButtonClickListener.onClick(dialog,
                                        DialogInterface.BUTTON_POSITIVE);
                            }
                        });
            }else {
                bt_photo.setVisibility(View.GONE);
            }
            if(negativeButtonClickListener!=null){
                bt_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negativeButtonClickListener.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
                    }
                });
            }else {
                bt_video.setVisibility(View.GONE);
            }
            dialog.setContentView(layout);
            return dialog;
        }
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height= ViewGroup.LayoutParams.MATCH_PARENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);

    }
}
