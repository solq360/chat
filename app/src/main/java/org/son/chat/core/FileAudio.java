package org.son.chat.core;

import org.son.chat.util.SDCardUtil;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * 文件处理语音
 * Created by luojie on 15/4/22.
 */
public class FileAudio extends AbstractAudio {
    private String fileName = SDCardUtil.getPath("/a.pcm");

    @Override
    public void record() {
        if (this.recordRun) {
            return;
        }
        createRecord();
        audioRecord.startRecording();
        this.recordRun = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] buf = new byte[1024 * 4];
                File file = new File(fileName);
                 DataOutputStream ds = null;
                try {
                    ds = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
                    while (recordRun) {
                        int len = audioRecord.read(buf, 0, buf.length);
                        // write
                        ds.write(buf, 0, len);
                    }
                    ds.flush();
                    ds.close();
                    closeRecord();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void play() {
        if (!this.recordRun) {
            return;
        }
        createPlay();
        audioTrack.play();
        this.recordRun = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                File file = new File(fileName);
                DataInputStream is = null;
                try {
                    is = new DataInputStream(new BufferedInputStream(new FileInputStream(file), 1024 * 8));
                    int i = 0;
                    final int len = 1024 * 512;
                    byte[] pBuf = new byte[len];
                    System.out.println("play len :" + pBuf.length);
                    System.out.println("file len :" + file.length());
                    while (is.available() > 0) {
                        pBuf[i] = (byte) is.readByte();
                        i++;
                        if (i >= len) {
                            audioTrack.write(pBuf, 0, i - 1);
                            i = 0;
                        }
                    }
                    is.close();

                    if (i > 0) {
                        audioTrack.write(pBuf, 0, i - 1);
                    }
                    closePlay();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
