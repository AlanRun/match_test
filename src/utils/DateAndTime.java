package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTime {
	
	/**
	 * 
	 * @param s1 比赛时间
	 * @param t1 提前时间
	 * @return 截止时间
	 * @throws ParseException
	 */
	public static String getEndTime (String s1, int t1) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		long t2 = sdf.parse(s1).getTime();
		t2 = t2 - (45 * 60000);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(t2);
		String s2 = sdf.format(calendar.getTime());
		return s2;
	}
	
	public static void main(String[] args) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy_MM_dd_HH" );
		Date d = new Date();
		String s = sdf.format(d);
		System.out.println(s);
		
//		String s1 = "2017-09-26 02:10:00";
//		String s2 = "2017-09-26 02:55:00";
//		String s3 = getEndTime(s2, 45);
//		System.out.println(s3);
	}
}
