package org.son.chat.ioc.model;

import android.view.View;

/**
 * @author solq
 *         点击事件
 */
public class LongClickEvent implements EventCtx {
    private View view;

    public static LongClickEvent valueOf(View view) {
        LongClickEvent result = new LongClickEvent();
        result.view = view;
        return result;
    }

    //getter
    public View getView() {
        return view;
    }
}
