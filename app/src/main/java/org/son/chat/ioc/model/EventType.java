package org.son.chat.ioc.model;

import org.son.chat.ioc.exception.IocException;
import org.son.chat.ioc.util.TypeUtil;

import java.lang.reflect.Method;

/**
 * @author solq
 *         事件类型
 */
public enum EventType {
    Click(ClickEvent.class,Void.class),
    LongClick(LongClickEvent.class,Boolean.class),
    ItemClick(ItemClickEvent.class,Void.class),
    itemLongClick(ItemLongClickEvent.class,Boolean.class);

    /**
     * 关联上下文类型*
     */
    private Class<? extends EventCtx> ctxClass;
    private Class<?> retClass;

    private EventType(Class<? extends EventCtx> ctxClass,Class<?> retClass) {
        this.ctxClass = ctxClass;
        this.retClass = retClass;
    }

    public void verifyMethod(Method method) {
        Class<?>[] cls = method.getParameterTypes();
        if(cls==null || cls.length==0 || !cls[0].equals(this.ctxClass)){
            throw new IocException("注入事件上下文类型不同"+this.ctxClass);
        }
        Class<?> resultCls=method.getReturnType();
        if(!TypeUtil.equalsPipeClass(resultCls,retClass)){
            throw new IocException("注入事件方法返回类型不同"+this.retClass);
        }
    }

    //getter
    public Class<? extends EventCtx> getCtxClass() {
        return ctxClass;
    }

    public Class<?> getRetClass() {
        return retClass;
    }
}