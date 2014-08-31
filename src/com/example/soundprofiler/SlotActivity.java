package com.example.soundprofiler;



import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SlotActivity extends Activity
{
	SlotTimingHolder slotTiming;
	private AlarmCalculation calculator;
	private AlarmManager mAlarmManager;
	private Intent mNotificationReceiverIntent, mLoggerReceiverIntent;
	private PendingIntent mNotificationReceiverPendingIntent,
			mLoggerReceiverPendingIntent;
	
	
	String[] timeTable;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slotactivity);
		timeTable=getIntent().getStringArrayExtra("slots");
		TextView slotText=(TextView)findViewById(R.id.slot);
		String slotString="";
		SlotTimingHolder s=new SlotTimingHolder();
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
					public void onClick(View v) 
					{
						createAlarm();
						Toast.makeText(getApplicationContext(), "Repeating Alarm Set",
								Toast.LENGTH_LONG).show();
					}
				});

	}
	 public void createAlarm()
	 {
		    calculator=new AlarmCalculation();
	    	slotTiming=new SlotTimingHolder();
	    	for(String slot:timeTable)
	    	{
	    		if(slot!=null&&!slot.contentEquals("NIL"))
	    		{
	    			List<Integer> slotWeekList= slotTiming.fetchTime(slot);
	    			int i=0;
	    			for(int hourOfTheDay: slotWeekList)
	    			{
	    				//>0 if that slot exists on that day
	    				if(hourOfTheDay>0)
	    				{
	    					Log.i("Testing SlotTiming","Alarm will go on on dayIndex "+i+1+" time is "+hourOfTheDay);
	    				
	    					mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
	    						calculator.getTimeInMilliSec(hourOfTheDay,0),
	    						calculator.weekInterval(),
								mNotificationReceiverPendingIntent);
	    					mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
	    						calculator.getTimeInMilliSec(hourOfTheDay,0),
	    						calculator.weekInterval()+50*60L*1000L,
								mNotificationReceiverPendingIntent);
	    			
	    				}
	    				i++;
	    			}
	    		}
	    	}
	    	
	    	
	    }



}
