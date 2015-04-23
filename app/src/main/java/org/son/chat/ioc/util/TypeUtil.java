package org.son.chat.ioc.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author solq
 */
public abstract class TypeUtil {
    private static Map<Class<?>, Class<?>> pipeClass = new HashMap<>();

    static {
        putClass(Void.class,void.class);
        putClass(Double.class,double.class);
        putClass(Integer.class,int.class);
        putClass(Float.class,float.class);
        putClass(Boolean.class,boolean.class);
        putClass(Long.class,long.class);
    }

    private static void putClass(Class<?> from, Class<?> to){
        pipeClass.put(from,to);
        pipeClass.put(to,from);
    }
    public static boolean equalsPipeClass(Class<?> from, Class<?> to) {
        if (from.equals(to)) {
            return true;
        }
        Class<?> v = pipeClass.get(to);
        if (v == null) {
            return false;
        }
        return v.equals(from);
    }
}
