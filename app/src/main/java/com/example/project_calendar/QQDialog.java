package com.example.project_calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.project_calendar.base.BaseDialog;
import com.example.project_calendar.base.EventBus_Tag;
import com.example.project_calendar.base.QQBean;
import com.example.project_calendar.util.DateUtil;
import com.example.project_calendar.util.ScreenUtil;
import com.example.project_calendar.util.StrUtil;
import com.example.project_calendar.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;



public class QQDialog extends BaseDialog {

    TextView tv_commit, tv_cannal, tv_title;
    EditText et_1, et_2;
    int myTag;//tag
    String content;
    int stype = 0;//0 add，1 edit
    QQBean bean;

    public QQDialog(Activity activity, int stype) {
        super(activity);
        this.stype = stype;
    }

    public QQDialog(Activity activity, int stype, QQBean bean) {
        super(activity);
        this.stype = stype;
        this.bean = bean;
    }

    @Override
    public void initDialogEvent(Window window) {
        window.setContentView(R.layout.dialog_qq);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        //init view
        et_1 = window.findViewById(R.id.et_1);
        et_2 = window.findViewById(R.id.et_2);


        tv_commit = window.findViewById(R.id.tv_commit);
        tv_cannal = window.findViewById(R.id.tv_cannal);
        tv_title = window.findViewById(R.id.tv_title);
        //set view
        if (stype == 0) {
            tv_title.setText("add");
        } else {
            tv_title.setText("edit");
        }
        //cancel
        tv_cannal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        //confirm
        tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp1 = et_1.getText().toString().trim();
                String temp2 = et_2.getText().toString().trim();


                if (
                        StrUtil.isEmpty(temp1) ||
                                StrUtil.isEmpty(temp2)   ) {
                    ToastUtil.showToast(activity, "Incomplete content");
                    return;
                }


                if (stype == 0) {//add
                    QQBean tempBean = new QQBean();
                    tempBean.setName1(temp1);
                    tempBean.setName2(temp2);

                    tempBean.setTimes(DateUtil.getTodayData_3());
                    tempBean.save();
                    if (tempBean.isSaved()) {
                        ToastUtil.showToast(activity, "Successfully Added");
                    }
                } else {//updata
                    ContentValues values = new ContentValues();
                    values.put("name1", temp1);
                    values.put("name2", temp2);


                    DataSupport.updateAll(QQBean.class, values, "times = ?", bean.getTimes());
                    ToastUtil.showToast(activity, "Successfully edited");
                }
                dialog.dismiss();
                EventBus.getDefault().post(new EventBus_Tag(1));

            }
        });

    }


    @Override
    public void showDialog() {
        dialog = new AlertDialog.Builder(activity).create();
        //click outside area to cancel dialog
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(null);
        dialog.show();

        Window window = dialog.getWindow();
        window.setLayout((int) (ScreenUtil.getScreenWidth(activity) * 0.8), WindowManager.LayoutParams.WRAP_CONTENT );
        window.setGravity(Gravity.CENTER);
        //resolve edge prob
        window.setBackgroundDrawable(new BitmapDrawable());
        initDialogEvent(window);
    }
}

