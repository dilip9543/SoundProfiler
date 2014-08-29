package com.example.soundprofiler;

import java.text.DateFormat;
import java.util.Date;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;

public class AlarmNotificationReceiver extends BroadcastReceiver 
{
	// Notification ID to allow for future updates
		private static final int MY_NOTIFICATION_ID = 1;
		private static final String TAG = "AlarmNotificationReceiver";

		// Notification Text Elements
		private final CharSequence tickerText = "Putting Your Phone in Silent";
		private final CharSequence contentTitle = "You have a class";
		private final CharSequence contentText = "Good Luck";
		private long[] mVibratePattern = { 0, 200, 200, 300 };
		// Notification Action Elements
		private Intent mNotificationIntent;
		private PendingIntent mContentIntent;

	@Override
	public void onReceive(Context context, Intent intent)
	{
		// TODO Auto-generated method stub
		mNotificationIntent = new Intent(context, SlotActivity.class);
		mContentIntent = PendingIntent.getActivity(context, 0,
				mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
		AudioManager audiomanage = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		if(audiomanage.getRingerMode()==AudioManager.RINGER_MODE_NORMAL)
		{
			Log.i(TAG,"putting your phone on silent");
			audiomanage.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			Log.i(TAG," "+audiomanage.getRingerMode());
		}	
		else if(audiomanage.getRingerMode()==AudioManager.RINGER_MODE_SILENT)
		{
			Log.i(TAG,"putting your phone on general");
			audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
			Log.i(TAG," "+audiomanage.getRingerMode());
			
		}
		Notification.Builder notificationBuilder = new Notification.Builder(context)
				.setTicker(tickerText)
				.setSmallIcon(android.R.drawable.stat_sys_warning)
				.setAutoCancel(true).setContentTitle(contentTitle)
				.setContentText(contentText).setContentIntent(mContentIntent)
				.setVibrate(mVibratePattern);

		// Pass the Notification to the NotificationManager:
		NotificationManager mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(MY_NOTIFICATION_ID,
				notificationBuilder.build());
		
		Log.i(TAG,"Sending notification at:" + DateFormat.getDateTimeInstance().format(new Date()));

	
	
	
	
	}










}
