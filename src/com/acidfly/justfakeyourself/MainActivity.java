package com.acidfly.justfakeyourself;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {
	Intent phoneIntent;
	AudioManager m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                                        WindowManager.LayoutParams.FLAG_FULLSCREEN);  
		setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
         m = (AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	 switch(keyCode){
	   case KeyEvent.KEYCODE_VOLUME_UP:
		   m.adjustStreamVolume(AudioManager.STREAM_MUSIC,AudioManager.ADJUST_RAISE,AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
	     event.startTracking();
	     return true;
	 }
	 return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        setUpAlarm(20, "Aravind", "8129342130");
	 return true;
	}
	
	public void setUpAlarm(long selectedTimeInMilliseconds, String fakeName, String fakeNumber){

		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(this, FakeCallReceiver.class);

		intent.putExtra("FAKENAME", fakeName);
		intent.putExtra("FAKENUMBER", fakeNumber);

		PendingIntent fakePendingIntent = PendingIntent.getBroadcast(this, 0,  intent, PendingIntent.FLAG_CANCEL_CURRENT);
		alarmManager.set(AlarmManager.RTC_WAKEUP, selectedTimeInMilliseconds, fakePendingIntent);

		Intent intents = new Intent(this, MainActivity.class);
		startActivity(intents);
		finish();

	}
	
}
