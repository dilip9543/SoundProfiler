package com.example.soundprofiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class GetPageContent
{
	final String TAG="DownloaderClass";
	private List<String> cookies;
	private HttpsURLConnection conn;
	private final String USER_AGENT = "Mozilla/5.0";
	Context context;
	GetPageContent(Context context)
	{
		this.context=context;
		// make sure cookies is turn on
		CookieHandler.setDefault(new CookieManager());
		
	}
	public String makeConnection(String url)
	{
		int responseCode;
		URL obj;
		BufferedReader in;
		String inputLine;
		StringBuffer response;
		try
		{
			obj = new URL(url);
			conn = (HttpsURLConnection) obj.openConnection();
			// default is GET
			conn.setRequestMethod("GET");
			conn.setUseCaches(false);
			// act like a browser
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
			if (cookies != null) 
			{
				for (String cookie : this.cookies)
				{
					conn.addRequestProperty("Cookie",
											cookie.split(";", 1)[0]);
				}
			}
			responseCode = conn.getResponseCode();
			Log.i(TAG,"\nSending 'GET' request to URL : " + url);
			Log.i(TAG,"Response Code : " + responseCode);
			Log.i(TAG,"Fetching page");
			//getting the input stream and reading frm it
			in = new BufferedReader(new InputStreamReader
										(conn.getInputStream()));
			
			response = new StringBuffer();
			//storing the content received from input stream into a string
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			Log.i(TAG,"page fetched");
			in.close();
			setCookies(conn.getHeaderFields().get("Set-Cookie"));
			//return the string of contents 
			return response.toString();
		}
		catch(MalformedURLException e)
		{
			Log.e(TAG,"Please Enter the Write Url");
			return "-1";
		}
		catch(IOException e)
		{
			Log.e(TAG,"Couldnt Open the Stream");
			return "-1";
		}
	}
	public void setCookies(List<String> cookies)
	{
		this.cookies = cookies;
	}
	public int downloadCaptcha(String url,String filename)
	{
		try
		{
			Log.i(TAG, "Entered Downloading Method");
			URL url_obj=new URL(url);	
			InputStream io=url_obj.openStream();
			Log.i(TAG, "Obtained Input Stream");
			byte buf[]=new byte[5000];
			File f=new File(Environment.getExternalStorageDirectory(),filename);
			if(f != null) {
	            //file.mkdirs();
	            try {
	                f.createNewFile();
	            } catch (IOException e1) {
	                Log.e(TAG, "createNewFile() failed!", e1);
	                 
	            } 
	        }
			if(!f.exists()) {
	            Log.d(TAG, "file does not exist!");
	        }
	        if(!f.canWrite()) {
	            Log.d(TAG, "cannot write to file!");
	        }
	        Log.d(TAG, "full file-path is: " + f.toString());
	        FileOutputStream file=new FileOutputStream(f);
			//FileOutputStream file=context.openFileOutput(filename,context.MODE_PRIVATE);
			Log.i(TAG,"Obtained file outputStream");
			int pos = 0;
			while(true)
			{
			  pos=0;
			  int count = io.read(buf, pos, buf.length);
			  if (count <= 0) 
			  {
			    break;
			  }
			  Log.i(TAG,"Writing to captcha");
			  file.write(buf, pos, count);
			}
			Log.i(TAG,"Exiting");
			io.close();
			file.close();
			return 1;
		} 
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			Log.i(TAG,"File not found exception");
			return -1;
		}
		catch(IOException e)
		{
			Log.i(TAG,"I/O Exception"+e.getMessage());
			return -1;
		}
	}




}
