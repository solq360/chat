package org.son.chat.ioc.model;

import android.view.View;
import android.widget.AdapterView;

/**
 * @author solq
 * 点击事件
 */
public class ItemClickEvent implements EventCtx {
    private AdapterView<?> parent;
    private View view;
    private int position;private long id;
    public static ItemClickEvent valueOf(AdapterView<?> parent, View view, int position, long id){
        ItemClickEvent result =new ItemClickEvent();
        result.parent = parent;
        result.view = view;
        result.position = position;
        result.id = id;
         return result;
    }
    //getter
    public View getView() {
        return view;
    }

    public AdapterView<?> getParent() {
        return parent;
    }

    public int getPosition() {
        return position;
    }

    public long getId() {
        return id;
    }
}
