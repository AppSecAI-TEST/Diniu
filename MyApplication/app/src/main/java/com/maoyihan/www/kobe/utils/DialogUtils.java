package com.maoyihan.www.kobe.utils;


import android.app.ActionBar;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.maoyihan.www.kobe.R;
import com.maoyihan.www.kobe.base.MyApplication;

/**
 * 对话框工具类
 * Created by Administrator on 2016/9/15.
 */
public class DialogUtils {
    /**
     * 对话框样式的重新设计
     */
    public static void showDialog(Activity activity, String title, String text, String btnTextOk, String btnTextCal, final OnResultListener onResultListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.popupw_dialog, null);
        final PopupWindow mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //设置
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialgo_title);//标题
        tvTitle.setText(title);
        TextView tvContent = (TextView) view.findViewById(R.id.tv_dialog_content);//内容
        tvContent.setText(text);

        Button btnOk = (Button) view.findViewById(R.id.btn_ok);//确定
        btnOk.setText(btnTextOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener.onOK();
                mPopupWindow.dismiss();
            }
        });
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);//取消
        btnCancel.setText(btnTextCal);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResultListener.onCancel();
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 只有确认按钮
     *
     * @param activity
     * @param onResultListener 确定按钮的事件
     * @return
     */
    public static void showDialogSingle(Activity activity, String context, int titleId, int okButtomId, final OnResultListener onResultListener) {
        View view = LayoutInflater.from(activity).inflate(R.layout.popupw_dialog_single, null);
        final PopupWindow mPopupWindow = new PopupWindow(view, ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(activity.getResources().getDrawable(android.R.color.transparent));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        //设置的标题
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_dialgo_title);
        tvTitle.setText(getString(titleId));
        // 内容
        TextView tvContent = (TextView) view.findViewById(R.id.tv_dialog_content);
        tvContent.setText(context);
        // 确定按钮
        Button btnOk = (Button) view.findViewById(R.id.btn_ok);
        btnOk.setText(getString(okButtomId));

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onResultListener) {
                    onResultListener.onOK();
                }
                mPopupWindow.dismiss();
            }
        });
    }

    /**
     * 得到字符串
     *
     * @param id 输 入的Id
     * @return 返回字符串
     */
    public static String getString(int id) {
        return MyApplication.getInstance().getResources().getString(id);
    }

    /**
     * 回调方法的接口
     */
    public abstract class OnResultListener {
        public void onOK() {
        }

        ;

        public void onCancel() {
        }

        ;
    }

    /**
     * 添加
     */
    public abstract class OnResListener {
        public void onOk(String... params) {
        }

        ;

        public void onCancel() {
        }

        ;
    }
}
