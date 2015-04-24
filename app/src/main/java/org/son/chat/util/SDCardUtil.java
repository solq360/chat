package org.son.chat.util;

import android.os.Environment;

/**
 * @author solq
 * sd卡工具类
 */
public abstract  class SDCardUtil {

    /**sd卡主目录*/
    public final static String ROOT_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    public static String getPath(String file){
        if(isAssembleSDCard()){
            return ROOT_PATH+file;
        }

        return file;
    }
    public static boolean isAssembleSDCard(){
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
