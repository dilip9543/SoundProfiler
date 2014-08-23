package com.example.soundprofiler;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
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
	GetPageContent http;
	EditText captchaText=null;
	Button submit=null;
	ImageView captcha=null;
	String loginPage;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		registration=(EditText)findViewById(R.id.registrationNumber);
		dob=(EditText)findViewById(R.id.dateOfBirth);
		captchaText=(EditText)findViewById(R.id.captchaText);
		submit=(Button)findViewById(R.id.button1);
		captcha=(ImageView)findViewById(R.id.captcha);
		http=new GetPageContent(this);
		
		new DownloadCaptchaASync().execute();
		submit.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Log.i(TAG,"I'll Start Submitting Request");
				new SubmitRequestASync().execute("Login");
				new SubmitRequestASync().execute("TimeTable");
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
class SubmitRequestASync extends AsyncTask<String,Void,Integer>
{
	String registrationNumber;
	String dateOfBirth;
	String captchaText;
	String page1;
	String timeTable[]=new String[15];
	GetPageContent http1;
	int shouldILoadNewActivity=0;
	String urlForTimeTable="https://academics.vit.ac.in/parent/timetable.asp?sem=";
	String urlForPostSubmit="https://academics.vit.ac.in/parent/parent_login_submit.asp";
	String parentLoginUrl="https://academics.vit.ac.in/parent/parent_login.asp";
	
	public String constructTimeTableParameter()
	{
		
		
		return "FS";
		
	}
	
	
	@Override
	protected void onPreExecute()
	{
			
		
		//get registration number and date of birth
		registrationNumber=registration.getText().toString();
		dateOfBirth=dob.getText().toString();
		captchaText=MainActivity.this.captchaText.getText().toString();
		Log.i(TAG, "parameters are "+registrationNumber+" "+dateOfBirth+" "+captchaText);
		
	}

	@Override
	protected Integer doInBackground(String... whatToDownload)
	{
		try
		{
			//GetPageContent http = new GetPageContent(getApplicationContext());
			if(whatToDownload[0].contentEquals("Login"))
			{
				String postParams = http.getFormParams(loginPage, registrationNumber, dateOfBirth,captchaText);
				http.sendPost(urlForPostSubmit, postParams);
			}
			if(whatToDownload[0].contentEquals("TimeTable"))
			{
				
				String timetable=http.asString(urlForTimeTable+constructTimeTableParameter());
				ProcessTimeTable timeTableProcessor=new ProcessTimeTable();
				timeTable=timeTableProcessor.generateData(timetable);
				shouldILoadNewActivity=1;
			}
			return 1;
		
		
		} 
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			Log.i(TAG,"Sorry man wrong encoding");
			return -1;
		}
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			Log.i(TAG,"Sorry man ran into trouble"+e.getMessage());
			return -1;
		}
		
		// TODO Auto-generated method stub
		//submitrequest and return the status that whether timetable was fetched or not
		
		
		
	
	
	
	}
	@Override
	protected void onPostExecute(Integer result)
	{
		Log.i(TAG,"Successfully submitted request");
		if(shouldILoadNewActivity==1)
		{
			Intent newActivity=new Intent(getApplicationContext(),SlotActivity.class);
			newActivity.putExtra("slots", timeTable);
			startActivity(newActivity);
			Log.i(TAG,"on Post Execute of Timetable");
		}
		
	}


	
	
}

class DownloadCaptchaASync extends AsyncTask<Void,Void,Integer> 
{
	String baseUrlCaptcha="https://academics.vit.ac.in/parent/captcha.asp";
	final String TAG="Captcha-Async";
	
	
	@Override
	protected void onPreExecute()
	{
		
	
	
	}
	private String constructCaptchaUrl()
	{
		loginPage=http.asString("https://academics.vit.ac.in/parent/parent_login.asp");
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
		
		//GetPageContent downloaderClass=new GetPageContent(getApplicationContext());
		http.downloadCaptcha(constructCaptchaUrl(),"captcha.bmp");
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