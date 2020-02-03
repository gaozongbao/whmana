package com.cmdi.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DateUtil {
	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * String 转date
	 * @param dateString
	 * @return
	 * @throws java.text.ParseException 
	 */
	public static Date stringToDate(String dateString) throws java.text.ParseException{ 
		String datestr = dateString.substring(0, 4)+dateString.substring(5, 7)+dateString.substring(8, 10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
	    Date date = sdf.parse(datestr); 
		return date;
	}
	/**
	 * wxs  转换日期字符串的格式yyyyMMdd转成yyyy-MM-dd
	 * @param date
	 * @return
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	 public static String strToDateFormat(String date) throws ParseException, java.text.ParseException {
	       SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	       Date newDate= formatter.parse(date);
	       formatter = new SimpleDateFormat("yyyy-MM-dd");
	       return formatter.format(newDate);
	   }
	public static final String[] hours ={
		"00","01","02","03","04","05","06","07","08","09","10","11",
		"12","13","14","15","16","17","18","19","20","21","22","23"
	};
	
	public static final String[] days ={
		"24","25","26","27"
	};
	
	private static final List<String> checkMonth= new ArrayList<String>(){{
		add("05");
		add("07");
		add("10");
		add("12");
	}};
	
	public static final String getDate(String dateFormat){
		Date now = new Date();
		SimpleDateFormat date = new SimpleDateFormat(dateFormat);
		return date.format(now);
	}
	
	public static final String getHour(){
		return getDate("hh");
	}
	
	public static final String getToday(){
		return getDate("dd");
	}
	
	public static final String getMonth(){
		return getDate("MM");
	}
	
	public static final String getYear(){
		return getDate("yyyy");
	}

	public static String getTempHour() {
		return getToday()+getHour();
	}

	public static final String getYestoday(){
		if(1 == Integer.parseInt(getToday())){
			if(checkMonth.contains(getDate("MM"))){
				return "30";
			} else if ("03" == getMonth()){
				int year = Integer.parseInt(getYear());
		        if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
		        	return "29";
		        }
		        else {
		        	return "28";
		        }
		        	
			} else {
				return "31";
			}
		} else {
			int yes = Integer.parseInt(getToday()) - 1;
			if(yes < 10) {
				return "0" + yes;
			}
			return String.valueOf(yes);
		}
	}
	
	public static String getLastHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY,
				calendar.get(Calendar.HOUR_OF_DAY) -1);
		
		String day = getToday(calendar.getTime());
		String hour = getHour(calendar.getTime());
		
		System.out.println("一小时前的时间：" + day+hour);
		return day+hour;
	}
	
	public static String getHourForNow() {
		Calendar calendar = Calendar.getInstance();
		
		String hour = getHour(calendar.getTime());
		
		return hour;
	}
	
	public static String getLastDay() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        String year = getYear(calendar.getTime());
        String month = getMonth(calendar.getTime());
        String day = getToday(calendar.getTime());
        
        System.out.println("一天前的时间：" + year+month+day);
        return day;  
    }  
	
	public static String getLastDayTime() {  
        Calendar calendar = Calendar.getInstance();  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        String year = getYear(calendar.getTime());
        String month = getMonth(calendar.getTime());
        String day = getToday(calendar.getTime());
        
        System.out.println("一天前的时间：" + year+month+day);
        return year+month+day;  
    }  
	
	public static String getTodayDay() {  
        Calendar calendar = Calendar.getInstance();  
//        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        String year = getYear(calendar.getTime());
        String month = getMonth(calendar.getTime());
        String day = getToday(calendar.getTime());
        
        return year+month+day;  
    }  
	
	public static final String getDate(Date time, String dateFormat){
		SimpleDateFormat date = new SimpleDateFormat(dateFormat);
		return date.format(time);
	}
	
	public static final String getYear(Date time){
		return getDate(time, "yyyy");
	}
	
	public static final String getMonth(Date time){
		return getDate(time, "MM");
	}
	
	public static final String getToday(Date time){
		return getDate(time, "dd");
	}
	
	public static final String getHour(Date time){
		return getDate(time, "HH");
	}
	
	public static void main(String args[]){
		System.out.println(DateUtil.getDate("yyyyMMddHHmmss"));
	}
	public static List getMaxAndMinDate(List<String> list){
		String temp="";
	    for(int i=0;i<list.size()-1;i++){  
	        for(int j=0;j<list.size()-1-i;j++){  
		        if(Integer.parseInt(list.get(j))>Integer.parseInt(list.get(j+1))){  
		            temp=list.get(j);  
		            list.add(j, list.get(j+1));
		            list.add(j+1, temp);
		        }  
	        }  
	    }  
		return list;
		
	}
	public static final Date getDate(String date, String dateFormat) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		return format.parse(date);
	}
}
