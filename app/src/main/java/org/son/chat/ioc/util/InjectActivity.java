package org.son.chat.ioc.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
/***
 * @author solq
 * 自动注入 Activity
 * */
public abstract class InjectActivity extends Activity {

    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        InjectUtil.initInjected(this);
    }


    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        InjectUtil.initInjected(this);
    }


    public void setContentView(View view) {
        super.setContentView(view);
        InjectUtil.initInjected(this);
    }


}