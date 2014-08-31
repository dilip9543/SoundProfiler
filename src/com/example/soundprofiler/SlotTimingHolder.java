package com.example.soundprofiler;


import java.sql.Time;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



public class SlotTimingHolder
{
		final String dayMonday="Monday";
		final String dayTuesday="Tuesday";
		final String dayWednesday="Wednesday";
		final String dayThursday="Thursday";
		final String dayFriday="Friday";
		final String daySaturday="Saturday";
		final String daySunday="Sunday";
		
		Map<String,List<Integer>> slotTiming=new HashMap<String,List<Integer>>();
		Map<String,String> AllAvailableSlots=new HashMap<String,String>();
		Map<String,List<String>> WeekWiseSlots=new HashMap<String,List<String>>();
		public SlotTimingHolder()
		{
			
			initializeSlots();
			weekWiseSlots();
			initializeTime();
			
		}
		public void initializeTime()
		{
			List<Integer> slotA1=new LinkedList<Integer>();
			slotA1.add(0,8);
			slotA1.add(1,-1);
			slotA1.add(2,-1);
			slotA1.add(3,9);
			slotA1.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("A1"),slotA1);
			List<Integer> slotTA1=new LinkedList<Integer>();
			slotTA1.add(0,8);
			slotTA1.add(1,11);
			slotTA1.add(2,-1);
			slotTA1.add(3,9);
			slotTA1.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("A1+TA1"),slotTA1);
			List<Integer> slotA2=new LinkedList<Integer>();
			slotA2.add(0,14);
			slotA2.add(1,-1);
			slotA2.add(2,-1);
			slotA2.add(3,15);
			slotA2.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("A2"),slotA2);
			List<Integer> slotTA2=new LinkedList<Integer>();
			slotTA2.add(0,14);
			slotTA2.add(1,17);
			slotTA2.add(2,-1);
			slotTA2.add(3,15);
			slotTA2.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("A2+TA2"),slotTA2);
			List<Integer> slotB1=new LinkedList<Integer>();
			slotB1.add(0,-1);
			slotB1.add(1,8);
			slotB1.add(2,-1);
			slotB1.add(3,-1);
			slotB1.add(4,9);
			slotTiming.put(AllAvailableSlots.get("B1"),slotB1);
			List<Integer> slotTB1=new LinkedList<Integer>();
			slotTB1.add(0,-1);
			slotTB1.add(1,8);
			slotTB1.add(2,11);
			slotTB1.add(3,-1);
			slotTB1.add(4,9);
			slotTiming.put(AllAvailableSlots.get("B1+TB1"),slotTB1);
			List<Integer> slotB2=new LinkedList<Integer>();
			slotB2.add(0,-1);
			slotB2.add(1,14);
			slotB2.add(2,-1);
			slotB2.add(3,-1);
			slotB2.add(4,15);
			slotTiming.put(AllAvailableSlots.get("B2"),slotB2);
			List<Integer> slotTB2=new LinkedList<Integer>();
			slotTB2.add(0,-1);
			slotTB2.add(1,14);
			slotTB2.add(2,17);
			slotTB2.add(3,-1);
			slotTB2.add(4,15);
			slotTiming.put(AllAvailableSlots.get("B2+TB2"),slotTB2);
			List<Integer> slotC1=new LinkedList<Integer>();
			slotC1.add(0,10);
			slotC1.add(1,-1);
			slotC1.add(2,8);
			slotC1.add(3,11);
			slotC1.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("C1"),slotC1);
			List<Integer> slotTC1=new LinkedList<Integer>();
			slotTC1.add(0,10);
			slotTC1.add(1,-1);
			slotTC1.add(2,8);
			slotTC1.add(3,11);
			slotTC1.add(4,12);
			slotTiming.put(AllAvailableSlots.get("C1+TC1"),slotTC1);
			List<Integer> slotC2=new LinkedList<Integer>();
			slotC2.add(0,16);
			slotC2.add(1,-1);
			slotC2.add(2,14);
			slotC2.add(3,17);
			slotC2.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("C2"),slotC2);
			List<Integer> slotTC2=new LinkedList<Integer>();
			slotTC2.add(0,16);
			slotTC2.add(1,-1);
			slotTC2.add(2,14);
			slotTC2.add(3,17);
			slotTC2.add(4,18);
			slotTiming.put(AllAvailableSlots.get("C2+TC2"),slotTC2);
			List<Integer> slotD1=new LinkedList<Integer>();
			slotD1.add(0,-1);
			slotD1.add(1,10);
			slotD1.add(2,-1);
			slotD1.add(3,8);
			slotD1.add(4,11);
			slotTiming.put(AllAvailableSlots.get("D1"),slotD1);
			List<Integer> slotTD1=new LinkedList<Integer>();
			slotTD1.add(0,12);
			slotTD1.add(1,10);
			slotTD1.add(2,-1);
			slotTD1.add(3,8);
			slotTD1.add(4,11);
			slotTiming.put(AllAvailableSlots.get("D1+TD1"),slotTD1);
			List<Integer> slotD2=new LinkedList<Integer>();
			slotD2.add(0,-1);
			slotD2.add(1,16);
			slotD2.add(2,-1);
			slotD2.add(3,14);
			slotD2.add(4,17);
			slotTiming.put(AllAvailableSlots.get("D2"),slotD2);
			List<Integer> slotTD2=new LinkedList<Integer>();
			slotTD2.add(0,18);
			slotTD2.add(1,16);
			slotTD2.add(2,-1);
			slotTD2.add(3,14);
			slotTD2.add(4,17);
			slotTiming.put(AllAvailableSlots.get("D2+TD2"),slotTD2);
			List<Integer> slotE1=new LinkedList<Integer>();
			slotE1.add(0,11);
			slotE1.add(1,-1);
			slotE1.add(2,10);
			slotE1.add(3,-1);
			slotE1.add(4,8);
			slotTiming.put(AllAvailableSlots.get("E1"),slotE1);
			List<Integer> slotTE1=new LinkedList<Integer>();
			slotTE1.add(0,11);
			slotTE1.add(1,-1);
			slotTE1.add(2,10);
			slotTE1.add(3,12);
			slotTE1.add(4,8);
			slotTiming.put(AllAvailableSlots.get("E1+TE1"),slotTE1);
			List<Integer> slotE2=new LinkedList<Integer>();
			slotE2.add(0,17);
			slotE2.add(1,-1);
			slotE2.add(2,16);
			slotE2.add(3,-1);
			slotE2.add(4,14);
			slotTiming.put(AllAvailableSlots.get("E2"),slotE2);
			List<Integer> slotTE2=new LinkedList<Integer>();
			slotTE2.add(0,17);
			slotTE2.add(1,-1);
			slotTE2.add(2,16);
			slotTE2.add(3,18);
			slotTE2.add(4,14);
			slotTiming.put(AllAvailableSlots.get("E2+TE2"),slotTE2);
			List<Integer> slotF1=new LinkedList<Integer>();
			slotF1.add(0,9);
			slotF1.add(1,-1);
			slotF1.add(2,9);
			slotF1.add(3,10);
			slotF1.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("F1"),slotF1);
			List<Integer> slotTF1=new LinkedList<Integer>();
			slotTF1.add(0,9);
			slotTF1.add(1,12);
			slotTF1.add(2,9);
			slotTF1.add(3,10);
			slotTF1.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("F1+TF1"),slotTF1);
			List<Integer> slotF2=new LinkedList<Integer>();
			slotF2.add(0,15);
			slotF2.add(1,-1);
			slotF2.add(2,15);
			slotF2.add(3,16);
			slotF2.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("F2"),slotF2);
			List<Integer> slotTF2=new LinkedList<Integer>();
			slotTF2.add(0,15);
			slotTF2.add(1,18);
			slotTF2.add(2,15);
			slotTF2.add(3,16);
			slotTF2.add(4,-1);
			slotTiming.put(AllAvailableSlots.get("F2+TF2"),slotTF2);
			List<Integer> slotG1=new LinkedList<Integer>();
			slotE1.add(0,-1);
			slotE1.add(1,9);
			slotE1.add(2,-1);
			slotE1.add(3,-1);
			slotE1.add(4,10);
			slotTiming.put(AllAvailableSlots.get("E1"),slotE1);
			List<Integer> slotTG1=new LinkedList<Integer>();
			slotTG1.add(0,-1);
			slotTG1.add(1,9);
			slotTG1.add(2,12);
			slotTG1.add(3,-1);
			slotTG1.add(4,10);
			slotTiming.put(AllAvailableSlots.get("G1+TG1"),slotTG1);
			List<Integer> slotG2=new LinkedList<Integer>();
			slotG2.add(0,-1);
			slotG2.add(1,15);
			slotG2.add(2,-1);
			slotG2.add(3,-1);
			slotG2.add(4,16);
			slotTiming.put(AllAvailableSlots.get("G2"),slotG2);
			List<Integer> slotTG2=new LinkedList<Integer>();
			slotTG2.add(0,-1);
			slotTG2.add(1,15);
			slotTG2.add(2,18);
			slotTG2.add(3,-1);
			slotTG2.add(4,16);
			slotTiming.put(AllAvailableSlots.get("G2+TG2"),slotTG2);

		
		
		}
		public List<Integer> fetchTime(String slot)
		{
			return slotTiming.get(slot);
			
			
			
		}
		public List<Boolean> getWeekList(String slot)
		{
			List<Boolean> weekList=new LinkedList<Boolean>();
			weekList.add(0,AllAvailableSlots.get(dayMonday).contains(slot));
			weekList.add(1,AllAvailableSlots.get(dayTuesday).contains(slot));
			weekList.add(2,AllAvailableSlots.get(dayWednesday).contains(slot));
			weekList.add(3,AllAvailableSlots.get(dayThursday).contains(slot));
			weekList.add(3,AllAvailableSlots.get(dayFriday).contains(slot));
			return weekList;
		}
		
		
		public void weekWiseSlots()
		{
			List<String> monday=new LinkedList<String>();
			monday.add(AllAvailableSlots.get("A1"));
			monday.add(AllAvailableSlots.get("F1"));
			monday.add(AllAvailableSlots.get("C1"));
			monday.add(AllAvailableSlots.get("E1"));
			monday.add(AllAvailableSlots.get("D1+TD1"));
			monday.add(AllAvailableSlots.get("A2"));
			monday.add(AllAvailableSlots.get("F2"));
			monday.add(AllAvailableSlots.get("C2"));
			monday.add(AllAvailableSlots.get("E2"));
			monday.add(AllAvailableSlots.get("D2+TD2"));
			WeekWiseSlots.put(dayMonday, monday);
			List<String> tuesday=new LinkedList<String>();
			tuesday.add(AllAvailableSlots.get("B1"));
			tuesday.add(AllAvailableSlots.get("G1"));
			tuesday.add(AllAvailableSlots.get("D1"));
			tuesday.add(AllAvailableSlots.get("A1+TA1"));
			tuesday.add(AllAvailableSlots.get("F1+TF1"));
			tuesday.add(AllAvailableSlots.get("B2"));
			tuesday.add(AllAvailableSlots.get("G2"));
			tuesday.add(AllAvailableSlots.get("D2"));
			tuesday.add(AllAvailableSlots.get("A2+TA2"));
			tuesday.add(AllAvailableSlots.get("F2+TF2"));
			WeekWiseSlots.put(dayTuesday, tuesday);
			List<String> wednesday=new LinkedList<String>();
			wednesday.add(AllAvailableSlots.get("C1"));
			wednesday.add(AllAvailableSlots.get("F1"));
			wednesday.add(AllAvailableSlots.get("E1"));
			wednesday.add(AllAvailableSlots.get("B1+TB1"));
			wednesday.add(AllAvailableSlots.get("G1+TG1"));
			wednesday.add(AllAvailableSlots.get("C2"));
			wednesday.add(AllAvailableSlots.get("F2"));
			wednesday.add(AllAvailableSlots.get("E2"));
			wednesday.add(AllAvailableSlots.get("B2+TB2"));
			wednesday.add(AllAvailableSlots.get("G2+TG2"));
			WeekWiseSlots.put(dayWednesday, wednesday);
			List<String> thursday=new LinkedList<String>();
			thursday.add(AllAvailableSlots.get("D1"));
			thursday.add(AllAvailableSlots.get("A1"));
			thursday.add(AllAvailableSlots.get("F1"));
			thursday.add(AllAvailableSlots.get("C1"));
			thursday.add(AllAvailableSlots.get("E1+TE1"));
			thursday.add(AllAvailableSlots.get("D2"));
			thursday.add(AllAvailableSlots.get("A2"));
			thursday.add(AllAvailableSlots.get("F2"));
			thursday.add(AllAvailableSlots.get("C2"));
			thursday.add(AllAvailableSlots.get("E2+TE2"));
			WeekWiseSlots.put(dayThursday, thursday);
			List<String> friday=new LinkedList<String>();
			friday.add(AllAvailableSlots.get("E1"));
			friday.add(AllAvailableSlots.get("B1"));
			friday.add(AllAvailableSlots.get("G1"));
			friday.add(AllAvailableSlots.get("D1"));
			friday.add(AllAvailableSlots.get("C1+TC1"));
			friday.add(AllAvailableSlots.get("E2"));
			friday.add(AllAvailableSlots.get("B2"));
			friday.add(AllAvailableSlots.get("G2"));
			friday.add(AllAvailableSlots.get("D2"));
			friday.add(AllAvailableSlots.get("C2+TC2"));
			WeekWiseSlots.put(dayFriday, friday);
		}
		public void initializeSlots()
		{
			AllAvailableSlots.put("A1","A1");
			AllAvailableSlots.put("A1+TA1","A1+TA1");
			AllAvailableSlots.put("A2","A2");
			AllAvailableSlots.put("A2+TA2","A2+TA2");
			AllAvailableSlots.put("B1","B1");
			AllAvailableSlots.put("B1+TB1","B1+TB1");
			AllAvailableSlots.put("B2","B2");
			AllAvailableSlots.put("B2+TB2","B2+TB2");
			AllAvailableSlots.put("C1","C1");
			AllAvailableSlots.put("C1+TC1","C1+TC1");
			AllAvailableSlots.put("C2","C2");
			AllAvailableSlots.put("C2+TC2","C2+TC2");
			AllAvailableSlots.put("D1","D1");
			AllAvailableSlots.put("D1+TD1","D1+TD1");
			AllAvailableSlots.put("D2","D2");
			AllAvailableSlots.put("D2+TD2","D2+TD2");
			AllAvailableSlots.put("E1","E1");
			AllAvailableSlots.put("E1+TE1","E1+TE1");
			AllAvailableSlots.put("E2","E2");
			AllAvailableSlots.put("E2+TE2","E2+TE2");
			AllAvailableSlots.put("F1","F1");
			AllAvailableSlots.put("F1+TF1","F1+TF1");
			AllAvailableSlots.put("F2","F2");
			AllAvailableSlots.put("F2+TF2","F2+TF2");
			AllAvailableSlots.put("G1","G1");
			AllAvailableSlots.put("G1+TG1","G1+TG1");
			AllAvailableSlots.put("G2","G2");
			AllAvailableSlots.put("G2+TG2","G2+TG2");
		}





}
