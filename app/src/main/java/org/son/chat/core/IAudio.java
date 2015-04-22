package org.son.chat.core;

/**
 * 语音行为接口
 * Created by luojie on 15/4/18.
 */
public interface IAudio {
    public void createRecord();
    public void createPlay();


    public void record();
    public void play();

    public void closePlay();
    public void closeRecord();
    public void closeAll();
}
