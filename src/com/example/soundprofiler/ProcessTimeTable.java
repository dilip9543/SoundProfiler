package com.example.soundprofiler;

public class ProcessTimeTable 
{
	String actualTimeTable[]=new String[15]; 
		

	public String[] generateData(String timetable)
	{
		int i=0;
		
		
		for(int j=0;j<3;j++)
		{
			i=timetable.indexOf("table", i)+1;
		//	System.out.println("Index is "+i);
		
		}
		timetable=timetable.substring(0,i+5);
		i=0;
		for(int k=0;k<3;k++)
		{
			i=timetable.indexOf("tr",i)+1;
			//System.out.println("Index is "+i);
			
			
		}
		timetable=timetable.substring(i+2, timetable.length());
		//System.out.println("Priting timetable /n"+timetable);
		boolean r=true;
		int trIndex=0;
		int trCloseIndex=0;
		int trFinalIndex=timetable.lastIndexOf("</tr>", timetable.length());
		//System.out.println("Final Index of </tr> is "+trFinalIndex);
		int count=0;
		while(r&& count<10)
		{
			trIndex=timetable.indexOf("<tr", trIndex)+2;
			trCloseIndex=timetable.indexOf("</tr>", trIndex);
			//System.out.println("Close Index of </tr> is "+trCloseIndex);
			if(trCloseIndex==trFinalIndex)
				break;
			int tdIndex=trIndex;
			for(int j=0;j<9;j++)
			{
				tdIndex=timetable.indexOf("<td", tdIndex)+3;
				
			}
			int fontIndex=timetable.indexOf("n'" , tdIndex)+3;
			int fontEndIndex=timetable.indexOf("<", fontIndex);
			actualTimeTable[count]=timetable.substring(fontIndex, fontEndIndex);
			System.out.println(actualTimeTable[count]);
			count++;
		}
		return actualTimeTable;
		//System.out.println("EM done");
	}












}
