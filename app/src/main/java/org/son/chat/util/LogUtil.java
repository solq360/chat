package org.son.chat.util;

/**
 * 日志工具
 *
 * @author solq
 */
public abstract class LogUtil {
    public static Logger getLogger(String tag) {
        return new Logger(tag);
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls.getName());
    }
}
