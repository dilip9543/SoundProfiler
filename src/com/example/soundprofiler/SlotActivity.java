package com.example.soundprofiler;



import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SlotActivity extends Activity
{
	private AlarmManager mAlarmManager;
	private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
	private PendingIntent mNotificationReceiverPendingIntent,
			mLoggerReceiverPendingIntent;
	private static final long INITIAL_ALARM_DELAY =1000L;
	protected static final long JITTER = 5000L;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slotactivity);
		String[] timeTable=getIntent().getStringArrayExtra("slots");
		TextView slotText=(TextView)findViewById(R.id.slot);
		String slotString="";
		for(String slot : timeTable)
		{
					slotString+="\n "+slot;
			
		}
		slotText.setText(slotString);
		mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		mNotificationReceiverIntent = new Intent(SlotActivity.this,
				AlarmNotificationReceiver.class);
		mNotificationReceiverPendingIntent = PendingIntent.getBroadcast(
				SlotActivity.this, 0, mNotificationReceiverIntent, 0);
	
		// Repeating Alarm Button
				final Button repeatingAlarmButton = (Button) findViewById(R.id.triggerAlarmButton);
				repeatingAlarmButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						mAlarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
								SystemClock.elapsedRealtime() + INITIAL_ALARM_DELAY,
								1000*30L,
								mNotificationReceiverPendingIntent);

						Toast.makeText(getApplicationContext(), "Repeating Alarm Set",
								Toast.LENGTH_LONG).show();
					}
				});

	}





}
