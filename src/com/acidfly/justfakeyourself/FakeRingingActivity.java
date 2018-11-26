package com.acidfly.justfakeyourself;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class FakeRingingActivity extends Activity {
	//private String networkCarrier;
    private MediaPlayer mp;
	AudioManager m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        setContentView(R.layout.activity_fake_ringing);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
 
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        mp = MediaPlayer.create(getApplicationContext(), notification);
        mp.start();
        m = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        Button rejectCall = (Button)findViewById(R.id.rejectcall);
 
        rejectCall.setOnClickListener(new View.OnClickListener(){
 
            @Override
            public void onClick(View v) {
                mp.stop();
                setContentView(R.layout.black);
            }
        });
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	 switch(keyCode){
	   case KeyEvent.KEYCODE_VOLUME_UP:
		   m.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
	     return true;
	 }
	 return super.onKeyDown(keyCode, event);
	}
	
   @Override
   public void onBackPressed()
   {
	   System.exit(0);
   }
 
}

