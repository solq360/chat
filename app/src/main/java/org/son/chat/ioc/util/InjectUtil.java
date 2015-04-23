package org.son.chat.ioc.util;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;

import org.son.chat.ioc.anno.EventInject;
import org.son.chat.ioc.anno.ViewInject;
import org.son.chat.ioc.exception.IocException;
import org.son.chat.ioc.model.EventListener;
import org.son.chat.ioc.model.EventType;
import org.son.chat.util.LogUtil;
import org.son.chat.util.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author solq
 *         注入工具类
 */
public abstract class InjectUtil {
    private final static Logger LOGGER = LogUtil.getLogger(InjectUtil.class);

    public static void initInjected(Activity activity) {
        initInjectedView(activity);
        initInjectedEvents(activity);
    }

    /**
     * 注入事件
     */
    public static void initInjectedEvents(Activity activity) {
        Method[] methods = activity.getClass().getDeclaredMethods();
        for (Method method : methods) {
            EventInject anno = method.getAnnotation(EventInject.class);
            if (anno == null) {
                continue;
            }
            method.setAccessible(true);
            final int[] ids = anno.value();
            final EventType eventType = anno.eventType();
            for (int id : ids) {
                View target = activity.findViewById(id);
                if (target == null) {
                    final String msg = "注入event失败 ：" + activity.getClass() + " id :" + id;
                    LOGGER.e(msg);
                    throw new IocException(msg);
                }
                setListener(activity, target, method, eventType);
            }
        }
    }

    private static void setListener(Object target, View obj, Method method, EventType eventType) {

        EventListener eventListener = EventListener.valueOf(target, method, eventType);
        switch (eventType) {
            case Click:
                if (obj instanceof View) {
                    ((View) obj).setOnClickListener(eventListener);
                }
                break;
            case ItemClick:
                if (obj instanceof AbsListView) {
                    ((AbsListView) obj).setOnItemClickListener(eventListener);
                }
                break;
            case LongClick:
                if (obj instanceof View) {
                    ((View) obj).setOnLongClickListener(eventListener);
                }
                break;
            case itemLongClick:
                if (obj instanceof AbsListView) {
                    ((AbsListView) obj).setOnItemLongClickListener(eventListener);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 注入ui
     */
    public static void initInjectedView(Object injectedSource, View sourceView) {
        Field[] fields = injectedSource.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            for (Field field : fields) {
                try {
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);
                    if (viewInject == null) {
                        continue;
                    }
                    field.setAccessible(true);
                    int viewId = viewInject.value();
                    field.set(injectedSource, sourceView.findViewById(viewId));
                } catch (Exception e) {
                    final String msg = "注入view失败 ：" + injectedSource.getClass() + " field :" + field.getName();
                    LOGGER.e(msg, e);
                    throw new IocException(msg, e);
                }
            }
        }
    }


    public static void initInjectedView(Activity activity) {
        initInjectedView(activity, activity.getWindow().getDecorView());
    }

}
