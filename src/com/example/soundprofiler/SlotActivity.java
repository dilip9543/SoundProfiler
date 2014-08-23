package com.example.soundprofiler;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class SlotActivity extends Activity
{
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
	}





}
