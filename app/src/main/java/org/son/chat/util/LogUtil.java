package org.son.chat.util;

import android.util.Log;

/**
 * 日志工具
 * @author solq
 */
public abstract class LogUtil {
    public static Logger getLogger(String tag) {
        return new Logger(tag);
    }

    public static Logger getLogger(Class<?> cls) {
        return new Logger(cls.getName());
    }

    final static class Logger {
        private String tag;

        public Logger(String tag) {
            this.tag = tag;
        }

        public void d(String msg) {
            Log.d(tag, msg);
        }

        public void d(String msg, Throwable tr) {
            Log.d(tag, msg, tr);
        }


        public void e(String msg) {
            Log.e(tag, msg);
        }

        public void e(String msg, Throwable tr) {
            Log.e(tag, msg, tr);
        }


        public void i(String msg) {
            Log.i(tag, msg);
        }

        public void i(String msg, Throwable tr) {
            Log.i(tag, msg, tr);
        }


        public void v(String msg) {
            Log.v(tag, msg);
        }

        public void v(String msg, Throwable tr) {
            Log.v(tag, msg, tr);
        }


        public void w(String msg) {
            Log.w(tag, msg);
        }

        public void w(String msg, Throwable tr) {
            Log.w(tag, msg, tr);
        }

    }

}
