package com.example.soundprofiler;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends Activity {
	final String TAG="Main-Activity";
	EditText registration=null;
	EditText dob=null;
	Button submit=null;
	ImageView captcha=null;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registration=(EditText)findViewById(R.id.registrationNumber);
		dob=(EditText)findViewById(R.id.dateOfBirth);
		submit=(Button)findViewById(R.id.button1);
		captcha=(ImageView)findViewById(R.id.captcha);
		submit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.i(TAG,"I'll Start Downloading Captcha");
				new DownloadCaptchaASync().execute();
			}
		
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
class SubmitRequestASync extends AsyncTask<Void,Void,Integer>
{
	@Override
	protected void onPreExecute()
	{
		//get registration number and date of birth
	
	
	}


	@Override
	protected Integer doInBackground(Void... arg0)
	{
		// TODO Auto-generated method stub
		//submitrequest and return the status that whether timetable was fetched or not
		
		
		return null;
	
	
	
	}
	
	
	
	
	
	
}

class DownloadCaptchaASync extends AsyncTask<Void,Void,Integer> 
{
	String baseUrlCaptcha="https://academics.vit.ac.in/student/captcha.asp";
	final String TAG="Captcha-Async";
	@Override
	protected void onPreExecute()
	{
		
	
	
	}
	private String constructCaptchaUrl()
	{
		String url=baseUrlCaptcha;
		String displayDate;
		DateFormat df;
		Date date;
		df = new SimpleDateFormat("E,ddMMMyyyyHH:mm:ss");  
		df.setTimeZone(TimeZone.getTimeZone("UTC"));  
		date = new Date();
		displayDate=df.format(date);
		displayDate+="UTC";
		displayDate.replace(" ","%20");
		Log.i(TAG,"displayDate "+displayDate);
		url=url+"?x="+displayDate;
		Log.i(TAG,"url for captcha"+url);
		return url;
	}
	
	
	
	@Override
	protected Integer doInBackground(Void... params)
	{
		
		GetPageContent downloaderClass=new GetPageContent(getApplicationContext());
		downloaderClass.downloadCaptcha(constructCaptchaUrl(),"captcha.bmp");
		return 1;
			
		// TODO Auto-generated method stub
		
	}
	

	@Override
	protected void onPostExecute(Integer result) {
		//mProgressBar.setVisibility(ProgressBar.INVISIBLE);
		//mImageView.setImageBitmap(result);
		//File imgFile = new  File(Environment.getExternalStorageDirectory()+"captcha.bmp");
		//if(imgFile.exists())
	//	{
		//	Log.i(TAG, "Yes the file exists");
		    Bitmap myBitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/captcha.bmp");
		    //Drawable d = new BitmapDrawable(getResources(), myBitmap);
		    captcha.setImageBitmap(myBitmap);

	//	}
		//else
			//Log.i(TAG,"No the file doesnot exist");
	
	}
}
}