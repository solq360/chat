package org.son.chat.core;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaRecorder;

/**
 * Created by luojie on 15/4/18.
 */
public class AudioConfig {
    public static final int HZ44 = 44100;
    public static final int HZ22 = 22050;
    public static final int HZ11 = 11025;
    /////////////基本配置///////////////
    private int sampleRateInHz = HZ44;
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;

    //////////record 配置////////
    private int recordBufferSize;
    private int channelIn = AudioFormat.CHANNEL_IN_MONO;
    private int audioSource = MediaRecorder.AudioSource.DEFAULT;

    ///////track 配置//////
    private int trackBufferSize;
    private int streamType = AudioManager.STREAM_MUSIC;
    private int channelOut = AudioFormat.CHANNEL_OUT_MONO;
    private int trackMode = AudioTrack.MODE_STREAM;
    private float playVolume = AudioTrack.getMaxVolume();
    public static AudioConfig valueOf() {
        AudioConfig result = new AudioConfig();
         return result;
    }

    /***每次创建AudioRecord 都要重新buffer*/
    public void buildRecordBuffer(){
        this.recordBufferSize = AudioRecord.getMinBufferSize(this.sampleRateInHz, this.channelIn, this.audioFormat);

    }
    public void buildTrackBuffer() {
        this.trackBufferSize = AudioTrack.getMinBufferSize(this.sampleRateInHz, this.channelOut, this.audioFormat );
    }

    //getter


    public int getSampleRateInHz() {
        return sampleRateInHz;
    }

    public int getChannelIn() {
        return channelIn;
    }

    public int getStreamType() {
        return streamType;
    }

    public int getChannelOut() {
        return channelOut;
    }

    public int getAudioFormat() {
        return audioFormat;
    }

    public int getAudioSource() {
        return audioSource;
    }

    public int getRecordBufferSize() {
        return recordBufferSize;
    }

    public int getTrackBufferSize() {
        return trackBufferSize;
    }

    public float getPlayVolume() {
        return playVolume;
    }

    public int getTrackMode() {
        return trackMode;
    }
}
