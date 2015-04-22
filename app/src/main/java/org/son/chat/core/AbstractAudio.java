package org.son.chat.core;

import android.media.AudioRecord;
import android.media.AudioTrack;


/**
 * 语音处理模板
 * Created by luojie on 15/4/22.
 */
public abstract class AbstractAudio implements IAudio {
    protected AudioRecord audioRecord;
    protected AudioTrack audioTrack;
    protected AudioConfig audioConfig = AudioConfig.valueOf();

    protected volatile boolean recordRun = false;


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
}
