package com.funpay.usercenter.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间数据 Util
 * <p>
 * Created by dave on 2020/05/27.
 */
public class DateUtil {
	
	public static final String YYYYMMDD = "yyyyMMdd";

	public static final String YYYY_MM_DD = "yyyy-MM-dd";

	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";

	public static final String YY_MM_DD_HH_MM_SS = "yy-MM-dd HH:mm:ss";
	
	public static final String DD_MM_YY = "dd-MM-yy";
	
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	
	public static final String MM_SS_DD_MM_YY = "HH:mm dd-MM-yy";
	
	public static final String MM_SS_DD_MM_YYYY = "HH:mm dd/MM/yyyy";

	public static final String DD_MM_YYYY_HH_mm_ss = "dd/MM/yyyy HH:mm:ss";
	
	public static final String YYYYMM = "yyyyMM";

	public static final String D_DD_MM_YYYY_HH_mm_ss = "dd-MM-yyyy HH:mm:ss";
	
	/**
	 * 获取指定之后的时间
	 * @param time 时间 (秒)
	 * @return
	 */
	public static Timestamp getSpecifiedTime(Integer time) {
		return new Timestamp(System.currentTimeMillis() + (time * 1000));
	}
	
	/**
	 * 获取指定格式化时间
	 * 
	 * @param date
	 *            指定时间
	 * @param length
	 *            格式长度
	 * @return 格式化字符串 dave
	 */
	public static String getFormatTime(Date date, int length) {
		SimpleDateFormat formatter = null;
		if (length == 10)
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		else if (length == 6)
			formatter = new SimpleDateFormat(YYYYMM);
		else if (length == 8)
			formatter = new SimpleDateFormat("yyyyMMdd");
		else if (length == 14)
			formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
		else if (length == 15)
			formatter = new SimpleDateFormat(YYYYMMDDHHMMSSSSS);
		else if (length == 18)
			formatter = new SimpleDateFormat(DD_MM_YYYY_HH_mm_ss);
		else if (length == 19)
			formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		else if (length == 7)
			formatter = new SimpleDateFormat("yyyy-MM");
		else if (length == 21)
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		else
			return date.toString();
		return formatter.format(date);
	}

	/**
	 * 获取指定格式化date
	 * 
	 * @param date
	 *            指定时间
	 * @param length
	 *            格式长度
	 * @return 格式化字符串 dave
	 */
	public static Date getFormatTime(String date, int length) {
		SimpleDateFormat formatter = null;
		if (length == 10)
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		else if (length == 8)
			formatter = new SimpleDateFormat("yyyyMMdd");
		else if (length == 14)
			formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
		else if (length == 18)
			formatter = new SimpleDateFormat(DD_MM_YYYY_HH_mm_ss);
		else if (length == 19)
			formatter = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		else if (length == 7)
			formatter = new SimpleDateFormat("yyyy-MM");
		else if (length == 21)
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		else
			return new Date();
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
//			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取指定格式化date
	 * 
	 * @param date 指定时间
	 * @param format 格式
	 * @return 格式化字符串 dave
	 */
	public static Date getFormatString(String date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		try {
			return formatter.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/**
	 * 获取指定Timestamp时间
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException 
	 */
	public static Timestamp getTimestamp(String timestamp) throws ParseException {
		return (timestamp != null && timestamp.length() > 0 ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(timestamp).getTime()) : new Timestamp(System.currentTimeMillis()));
	}
	
	/**
	 * 获取当前Timestamp时间
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException 
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 获取当前转换的Timestamp时间
	 * 
	 * @param timestamp
	 * @return
	 * @throws ParseException 
	 */
	public static Timestamp getStrTimestamp(String time) {
		try {
			return time != null && !"".equals(time) ? new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time).getTime()) : new Timestamp(System.currentTimeMillis());
		} catch (ParseException e) {
			e.printStackTrace();
			return new Timestamp(System.currentTimeMillis());
		}
	}
	
	/**
	 * 判断时间是否是周末
	 * @param date 判断时间
	 * @return
	 * @throws ParseException
	 */
	public static boolean isWeekend(String date) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Date bdate = format1.parse(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(bdate);
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
				? true : false;
	}
	
	/**
	 * 获取当前时间字符串
	 * 
	 * @param 时间长度
	 * @return
	 * @throws ParseException
	 */
	public static String getStrTimestamp(Integer len) {
		String styleTime = len == 14 ? "yyyyMMddHHmmss" : "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(styleTime);
		return sdf.format(new Date());
	}
	
	/**
	 * 获取当前账单时间
	 * @param current 当前时间
	 * @return Timestamp 
	 */
	public static Timestamp getCurrentAccountDate(Date current) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String format = sdf.format(current);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 5);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 0);
		if (Integer.parseInt(format.substring(6)) > 25) {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		} else if (Integer.parseInt(format.substring(6)) < 6){
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
		} else {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		}
		Date date = calendar.getTime();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 获取上一个月当前时间
	 * @param current 当前时间
	 * @return Timestamp 
	 */
	public static Timestamp getLastMonth(Date current) {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//过去一月
		c.setTime(current);
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		return Timestamp.valueOf(mon);
	}
	
	/**
	 * 获取本月出账时间 最后还款日时间
	 * @param current 当前时间
	 * @return Timestamp 
	 */
	public static Timestamp getBillDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 5);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date date = calendar.getTime();
		return new Timestamp(date.getTime());
	}
	
	/**
	 * 根据最后还款日时间，获取出账时间 
	 */
	public static long getBillDataByExpireData(Date date) {
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 26);
		calendar.set(Calendar.HOUR_OF_DAY, 4);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}
	
	
	/**
	 * 获取当天 字符
	 */
	public static String getNewDay() {
		return new SimpleDateFormat("dd").format(new Date());
	}
	
	/**
	 * 获取当月 字符
	 */
	public static String getNewMonth() {
		return new SimpleDateFormat("MM").format(new Date());
	}
	
	/**
	 * 获取当年 字符
	 */
	public static String getNewYear() {
		return new SimpleDateFormat("yyyy").format(new Date());
	}
	
	/**
	 * 获取当前时间 date yyyy-MM-dd HH-mm-ss
	 */
	public static Date getCurrDate() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
			String time = sdf.format(new Date());
			return sdf.parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}
	
	/**
	 * 获取当前年加季度字符串
	 */
	public static String getTableNameByQuarter() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String yearMonth = simpleDateFormat.format(new Date());
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		int intMonth = Integer.parseInt(month);
		int quarter = intMonth % 3 == 0 ? intMonth / 3 : intMonth / 3 + 1;
		return year + quarter;
	}
	
	/**
	 * 获取当前年加季度字符串
	 * param 选择时间
	 */
	public static String getTableNameByQuarter(Date date) {
		if (null == date) {
			date = new Date();
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
		String yearMonth = simpleDateFormat.format(date);
		String year = yearMonth.substring(0, 4);
		String month = yearMonth.substring(5, 7);
		int intMonth = Integer.parseInt(month);
		int quarter = intMonth % 3 == 0 ? intMonth / 3 : intMonth / 3 + 1;
		return year + quarter;
	}
	
	/**
	 * 获取当前时间 date yyyyMMddHHmmss
	 */
	public static Date getInDate(String date) {
		try {
			if (null == date || "".equals(date)) {
				return null;
			}
			SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHMMSS);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 几天前后的时间
	 *
	 * @param d
	 * @param day
	 * @param oper 操作  1 加(后) 2 减(前)
	 * @return
	 */
	public static Date getDateAfter(Date d, int day, int oper) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, oper == 1 ? now.get(Calendar.DATE) + day : now.get(Calendar.DATE) - day);
		return now.getTime();
	}
	
	/**
	 * 获取当前时间 dd-mm-yyyy
	 */
	public static String getCurrentDDMMYYYY() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(new Date());
	}

	/**
	 * 获取前一天时间 dd-mm-yyyy
	 */
	public static String getBeforeDDMMYYYY() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(c.getTime());
	}
	
	/**
	 * 获取当前指定的时间字符串
	 * @date 2019-5-8
	 * @version 1.0
	 * @author dave
	 */
	public static String getSpecificCurrentTime(String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 获取指定天数的时间 数据
	 * @date 2019-7-22
	 * @version 1.0
	 * @author dave
	 * @param format 格式
	 * @param time 天数
	 */
	public static String getSpecificDay(String format, int time) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, time);
		date = calendar.getTime();
		return sdf.format(date);
	}
	
	/**
	 * 获取指定天数的时间 数据
	 * @date 2019-7-22
	 * @version 1.0
	 * @author dave
	 * @param format 格式
	 * @param time 天数
	 */
	public static Date getSpecificDayDate(int time) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, time);
		return calendar.getTime();
	}
	
	/**
	 * 获取当前时间 yyyy-mm-dd
	 */
	public static String getCurrentYYYYMMDD() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(new Date());
	}

	/**
	 * 获取前一天时间 yyyy-mm-dd
	 */
	public static String getBeforeYYYYMMDD() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(c.getTime());
	}
	
    public static String dealDate(String oldDateStr) {
		 try {
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		    Date date = df.parse(oldDateStr);
		    SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
		    Date date1 =  df1.parse(date.toString());
		    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    return df2.format(date1);
		 } catch (ParseException e) {
			e.printStackTrace();
		 }
		 return null;
	}
   
    /**
     * 判断是否超过天数(不可小于当前时间)
     * @param date 判断时间
     * @param day 超过天数
     * @return 超过 true 不超过false
     */
	public static boolean isOutDay(Date date, int day) {
		if (new Date().after(date)) {
			return true;
		}
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_MONTH, day);
		Date newTime = calc.getTime();
		return date.after(newTime) ? true : false;
	}
	
	/**
	 * 获取当前时间前面几个小时
	 * @param hour 当前时间的前几个小时
	 * @return
	 */
	public static String beforeOneHourToNowDate(int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - hour);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) - minute);
        SimpleDateFormat sdf = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
		return sdf.format(calendar.getTime());
	}
	
	/**
     * 判断时间是否在时间段内
     * @param nowTime
     * @param beginTime
     * @param endTime
     * @return
     */
    public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(beginTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }
    

	/**
	 * 获取当前时间之前的数据
	 * 
	 * @param todayStart
	 * @param hour 时
	 * @param second 秒
	 * @param minute 分
	 * @param millisecond 毫秒
	 * @return
	 */
	public static long getStartTime(Calendar todayStart, int hour, int second, int minute, int millisecond) {
		todayStart.set(Calendar.HOUR_OF_DAY, todayStart.get(Calendar.HOUR_OF_DAY) - hour);
		todayStart.set(Calendar.MINUTE, todayStart.get(Calendar.MINUTE) - minute);
		todayStart.set(Calendar.SECOND, todayStart.get(Calendar.SECOND) - second);
		todayStart.set(Calendar.MILLISECOND, todayStart.get(Calendar.MILLISECOND) - millisecond);
		return todayStart.getTime().getTime();
	}
	
	/**
     * 判断是否在hour小时之内
     * (注释: 起始时间小于当前时间返回true)
     * @param start 起始时间
     * @param end 结束时间
     * @param hour 时间(时)
     * @throws Exception
     */
    public static boolean judgmentDate(Date start, Date end, Integer hour) {
        long cha = end.getTime() - start.getTime();
        if (cha < 0) {
            return true;
        }
        long hourCurr = cha / 3600000;
        return hourCurr <= hour.longValue();
    }

    /**
     * 判断是否在前后分钟内
     * (注释: 起始时间小于当前时间返回true)
     * @param start 判断时间
     * @param minute 时间(分)
     * @return 在之内返回 false 反之 true
     * @throws 
     */
	public static boolean judgmentMinuteDate(long start, Integer minute) {
		Date date = new Date();
		Date judgmentDate = new Date(start);
		Calendar c1 = Calendar.getInstance();
		Calendar begin = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		c1.setTime(judgmentDate);
		begin.setTime(date);
		end.setTime(date);
		begin.add(Calendar.MINUTE, -minute);
		end.add(Calendar.MINUTE, minute);
		return !(c1.after(begin) && c1.before(end));
	}
	
	/**
	 * 日期格式化为字符串
	 *
	 * @param source source
	 */
	public static Date string2date(String source) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(source);
		
	}
	
	/**
	 * 字符串解析为日期
	 *
	 * @param source source
	 */
	public static String date2String(Date source) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(source);
	}
	
	/**
	 * @param calendar 当前的calendar
	 * @param day      往前或者往后推几天
	 * @return 目标日期的0点时间.
	 */
	public static String getZeroTime(Calendar calendar, int day) {
		Calendar targetCalendar = (Calendar) calendar.clone();
		targetCalendar.add(Calendar.DAY_OF_MONTH, day);
		targetCalendar.set(Calendar.HOUR_OF_DAY, 0);
		targetCalendar.set(Calendar.MINUTE, 0);
		targetCalendar.set(Calendar.SECOND, 0);
		targetCalendar.set(Calendar.MILLISECOND, 0);
		Date dayStart = targetCalendar.getTime();
		return date2String(dayStart);
	}
	
}
