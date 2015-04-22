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
import org.son.chat.core.FileAudio;
import org.son.chat.core.IAudio;

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
public class MainActivity extends ActionBarActivity {


    private FileAudio fileAudio;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //bnt bind click event
        fileAudio= new FileAudio();
        Button but_play = (Button) findViewById(R.id.play);
        Button but_record = (Button) findViewById(R.id.record);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.record:
                        fileAudio.record();
                        System.out.println("record");
                        break;
                    case R.id.play:
                        fileAudio.play();
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
        fileAudio.closeAll();
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
