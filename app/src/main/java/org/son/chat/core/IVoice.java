package org.son.chat.core;

/**
 * Created by luojie on 15/4/18.
 */
public interface IVoice {
    public void createRecord();
    public void createPlay();


    public void record();
    public void play();

    public void closePlay();
    public void closeRecord();
    public void closeAll();
}
