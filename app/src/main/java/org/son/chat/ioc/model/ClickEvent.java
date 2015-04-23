package org.son.chat.ioc.model;

import android.view.View;

/**
 * @author solq
 * 点击事件
 */
public class ClickEvent implements EventCtx {
    private View view;

    public static ClickEvent valueOf(View view){
        ClickEvent result =new ClickEvent();
        result.view = view;
        return result;
    }
    //getter
    public View getView() {
        return view;
    }
}
