package org.son.chat;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import org.son.chat.core.FileAudio;
import org.son.chat.ioc.anno.EventInject;
import org.son.chat.ioc.model.ClickEvent;
import org.son.chat.ioc.model.EventType;
import org.son.chat.ioc.util.InjectUtil;


/**
 * @author solq
 */
public class MainActivity extends ActionBarActivity {


    private FileAudio fileAudio;

//    @ViewInject(R.id.play)
//    private Button but_play;
//    @ViewInject(R.id.record)
//    private Button but_record;


    @EventInject(value = {R.id.play}, eventType = EventType.Click)
    private void testBntPlay(ClickEvent event) {
        fileAudio.play();
        System.out.println("play");
    }


    @EventInject(value = {R.id.record}, eventType = EventType.Click)
    private void testBntRecord(ClickEvent event) {
        fileAudio.record();
        System.out.println("record");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtil.initInjected(this);
        //bnt bind click event
        fileAudio = new FileAudio();

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
