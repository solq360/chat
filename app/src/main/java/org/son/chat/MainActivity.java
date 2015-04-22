package org.son.chat;

import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.son.chat.core.AudioConfig;
import org.son.chat.core.IVoice;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;


/**
 * @author solq
 */
public class MainActivity extends ActionBarActivity implements IVoice {

    private AudioRecord audioRecord;
    private AudioTrack audioTrack;
    private AudioConfig audioConfig = AudioConfig.valueOf();

    private volatile boolean recordRun = false;

    private String fileName = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test.pcm";

    @Override
    public void createRecord() {

        audioConfig.buildRecordBuffer();
        audioRecord = new AudioRecord(audioConfig.getAudioSource(), audioConfig.getSampleRateInHz(),
                audioConfig.getChannelIn(), audioConfig.getAudioFormat(), audioConfig.getRecordBufferSize());
    }

    @Override
    public void createPlay() {
        audioConfig.buildTrackBuffer();
        audioTrack = new AudioTrack(audioConfig.getStreamType(), audioConfig.getSampleRateInHz(),
                audioConfig.getChannelOut(), audioConfig.getAudioFormat(), audioConfig.getTrackBufferSize(),
                audioConfig.getTrackMode());
        audioTrack.setVolume(audioConfig.getPlayVolume());
    }

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
                // InputStream is = new BufferedInputStream(new FileInputStream(file),1024*8);
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
                byte[] buf = new byte[1024 * 4];
                File file = new File(fileName);
                DataInputStream is = null;
                try {
                    is = new DataInputStream(new BufferedInputStream(new FileInputStream(file), 1024 * 8));
                    int i = 0;
                    final int len = 1024 * 1024;
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

    @Override
    public void closePlay() {
        if (audioTrack != null) {
            audioTrack.stop();
            audioTrack.release();
        }
    }

    @Override
    public void closeRecord() {
        if (audioRecord != null) {
            audioRecord.stop();
            audioRecord.release();
        }
    }

    @Override
    public void closeAll() {
        closePlay();
        closeRecord();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bnt bind click event

        Button but_play = (Button) findViewById(R.id.play);
        Button but_record = (Button) findViewById(R.id.record);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.record:
                        record();
                        System.out.println("record");
                        break;
                    case R.id.play:
                        play();
                        System.out.println("play");

                        break;
                }
            }
        };
        but_play.setOnClickListener(listener);
        but_record.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        this.closeAll();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
