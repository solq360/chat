package org.son.chat.ioc.exception;

/**
 * Created by luojie on 15/4/22.
 */
public class IocException extends RuntimeException {
    public IocException(String msg){
        super(msg);
    }
    public IocException(String msg,Throwable tr){
        super(msg,tr);
    }
}
