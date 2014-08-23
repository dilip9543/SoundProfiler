package com.example.soundprofiler;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		try
		{
		URL obj = new URL(url);
		conn = (HttpsURLConnection) obj.openConnection();

		
		// default is GET
		conn.setRequestMethod("GET");
	 
		conn.setUseCaches(false);
	 
		// act like a browser
		conn.setRequestProperty("User-Agent", USER_AGENT);
		conn.setRequestProperty("Accept",
			"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		conn.setRequestProperty("Accept-Language", "en-US,en;q=0.8");
		if (cookies != null) 
		{
			for (String cookie : this.cookies) {
				conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
			}
		}
		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		System.out.println("Fetching page");
		//getting the input stream and reading frm it
		BufferedReader in = 
							new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		//storing the content received from input stream into a string
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		System.out.println("page fetched");
		in.close();
		setCookies(conn.getHeaderFields().get("Set-Cookie"));
		//return the string of contents 
		return response.toString();
		}
		catch(MalformedURLException e)
		{
			System.out.println("Please Enter the Write Url");
			e.printStackTrace();
			return "-1";
		}
		catch(IOException e)
		{
			System.out.println("Couldnt Open the Stream");
			e.printStackTrace();
			return "-1";
		}
	}
	public String asString(String url)
	{
		return makeConnection(url);
		
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
	public void sendPost(String url, String postParams) throws Exception
	{
		  
			URL obj = new URL(url);
			conn = (HttpsURLConnection) obj.openConnection();
		 
			// Acts like a browser
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Host","academics.vit.ac.in");
			conn.setRequestProperty("User-Agent", USER_AGENT);
			conn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			conn.setRequestProperty("Connection", "keep-alive");
			conn.setRequestProperty("Referer", "https://academics.vit.ac.in/parent/parent_login.asp");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			//conn.setRequestProperty("Content-Length", Integer.toString(postParams.length()));
		 
		
			conn.setDoOutput(true);
			conn.setDoInput(true);
		 
			// Send post request
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
		 
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + postParams);
			System.out.println("Response Code : " + responseCode);
		 
			BufferedReader in = 
		             new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
		 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			Log.i(TAG,response.toString());
}
	
	
	
	
	
	public String getFormParams(String html, String username, String password,String captcha)
			throws UnsupportedEncodingException
	{
	 
			System.out.println("Extracting form's data...");
			Document doc = Jsoup.parse(html);
			//getting the element with tag form ..this site has only one form element
			Elements loginformList = doc.getElementsByTag("form");
			
			Element loginform=null;
			//looping over the forms obtained to get the form with form name attribute as parent_login
			for(Element a:loginformList)
			{
				String key=a.attr("name");
				if(key.equals("parent_login"))
				{
					//if form name is equal to parent login we store that form element in the login form 
					loginform=a;
				
				}
			}
			//now extracting individual contents of the form i.e tags of type input
			Elements inputElements = loginform.getElementsByTag("input");
			List<String> paramList = new ArrayList<String>();
			for (Element inputElement : inputElements)
			{
				String key = inputElement.attr("name");
				String value = inputElement.attr("value");
				//wdregno is the name of the input element
				if (key.equals("wdregno"))
					value = username;
				//wdpswd if the name of password field in the form
				else if (key.equals("wdpswd"))
					value = password;
				//vrfcd is the name of captcha field in the form
				else if (key.equals("vrfcd"))
					value=captcha;
				//on observing the post url these two were not used so skipping
				else if(value.equals("Login")||value.equals("Reset"))
					continue;
				//creating the post url
				paramList.add(key + "=" + URLEncoder.encode(value, "UTF-8"));
			}

			// build parameters list
			StringBuilder result = new StringBuilder();
			for (String param : paramList)
			{
				if (result.length() == 0)
				{
					result.append(param);
				}
				else
				{
					//appending an & as it was seen the url uses this separator as terminating character
					result.append("&" + param);
				}
			}
			System.out.println("Actual param list "+result);
			//System.out.println(result.substring(0, result.lastIndexOf("&")));
			//String s=result.substring(0, result.lastIndexOf("&"));
			//return the parameters list
			return result.toString();
	}
	 
	



}
