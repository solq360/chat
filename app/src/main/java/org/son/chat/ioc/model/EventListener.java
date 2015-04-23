package org.son.chat.ioc.model;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import org.son.chat.ioc.exception.IocException;
import org.son.chat.util.LogUtil;
import org.son.chat.util.Logger;

import java.lang.reflect.Method;

/**
 * @author solq
 */
public class EventListener implements OnClickListener, OnLongClickListener, OnItemClickListener, OnItemLongClickListener {
    private final static Logger LOGGER = LogUtil.getLogger(EventListener.class);

    private Object target;
    private EventType eventType;
    private Method method;


    public static EventListener valueOf(Object target, Method method, EventType eventType) {
        eventType.verifyMethod(method);
        EventListener result = new EventListener();
        result.target = target;
        result.eventType = eventType;
        result.method = method;
        return result;
    }

    @Override
    public void onClick(View v) {
        try {
            method.invoke(target, ClickEvent.valueOf(v));
        } catch (Exception e) {
            final String msg = "执行事件错误 ：" + target.getClass() + " method :" + this.method.getName();
            LOGGER.e(msg, e);
            throw new IocException(msg, e);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            method.invoke(target, ItemClickEvent.valueOf(parent, view, position, id));
        } catch (Exception e) {

            final String msg = "执行事件错误 ：" + target.getClass() + " method :" + this.method.getName();
            LOGGER.e(msg, e);
            throw new IocException(msg, e);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            return (boolean) method.invoke(target, ItemLongClickEvent.valueOf(parent, view, position, id));
        } catch (Exception e) {

            final String msg = "执行事件错误 ：" + target.getClass() + " method :" + this.method.getName();
            LOGGER.e(msg, e);
            throw new IocException(msg, e);
        }
    }


    @Override
    public boolean onLongClick(View v) {
        try {
            return (boolean) method.invoke(target, LongClickEvent.valueOf(v));
        } catch (Exception e) {

            final String msg = "执行事件错误 ：" + target.getClass() + " method :" + this.method.getName();
            LOGGER.e(msg, e);
            throw new IocException(msg, e);
        }
    }
}