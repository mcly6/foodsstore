package com.shuxin.foodsstore.commons.utils;


import com.shuxin.foodsstore.commons.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 类名:  DateUtil
 * 功能:  时间格式化
 * 详细:
 */
@Slf4j
public class DateUtil {

    static long CONST_WEEK = 3600 * 1000 * 24 * 7;

    /**
     * 默认 日期时间 格式  yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";
    /**
     * 默认 日期格式  yyyy-MM-dd
     */
    public static final String PATTERN_DATE = "yyyy-MM-dd";
    /**
     * 默认 时间格式
     */
    public static final String PATTERN_TIME = "HH:mm:ss";

    /**
     * 时分时间格式
     */
    public static final String PATTERN_HH_mm = "HH:mm";
    /**
     * 每月1日
     */
    public static final String PATTERN_MONTH = "yyyy-MM-01";
    /**
     * 自动匹配字符串格式
     */
    public static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyyMMddHHmmssSSS", "E MMM dd yyyy HH:mm:ss z", "EEE, dd MMM yyyy HH:mm:ss zZ", "EEE, dd MMM yyyy HH:mm:ss Z"};

    /**
     * Timestamp 格式化成字符串，使用默认格式  yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp
     * @return
     */
    public static String timestamp2String(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return DateFormatUtils.format(timestamp, PATTERN_STANDARD);
    }

    /**
     * Timestamp 格式化 自定义格式
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public static String timestamp2String(Timestamp timestamp, String pattern) {
        if (timestamp == null) {
            return null;
        }
        return DateFormatUtils.format(timestamp, pattern);
    }

    /**
     * Date 格式化成字符串，使用默认格式  yyyy-MM-dd
     *
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, PATTERN_DATE);
    }

    /**
     * Date 格式化 自定义格式
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String date2String(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static Timestamp currentTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 获取当前日期yyyy-MM-dd String
     *
     * @return
     */
    public static String currentDateToString() {

        return date2String(new Date());
    }

    /**
     * 获取当前时间 HH:mm:ss String类型
     *
     * @return
     */
    public static String currentTimeToString() {

        return date2String(new Date(), PATTERN_TIME);

    }

    /**
     * 获取当前日期yyyy-MM-dd  HH:mm:ss String
     *
     * @return
     */
    public static String currentDateTimeToString() {

        return date2String(new Date(), PATTERN_STANDARD);
    }

    /**
     * 获取当前日期yyyy-MM-dd  HH:mm:ss String
     *
     * @return
     */
    public static String currentDateTimeLittleThan1MinToString() {
        //new Calendar
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 1);
        return date2String(c.getTime(),PATTERN_STANDARD);
    }

    public static String getDateTimeAddedMinutes(Date date, Integer minutes) {
        //new Calendar
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, minutes);
        return date2String(c.getTime(), PATTERN_STANDARD);
    }

    /**
     * 两个时间相减
     *
     * @param firsttime
     * @param secondtime
     * @return
     */
    public static long subtractTime(String firsttime, String secondtime) {

        return string2Date(firsttime, PATTERN_TIME).getTime() - string2Date(secondtime, PATTERN_TIME).getTime();

    }

    /**
     * 字符串转换为 Timestamp  自动匹配格式
     *
     * @param strDateTime
     * @return 如果传入字符串为null，或者空字符串，则返回null
     */
    public static Timestamp string2Timestamp(String strDateTime) {

        return new Timestamp(string2Date(strDateTime).getTime());
    }

    /**
     * 字符串 转换为 Timestamp  传入字符串格式
     *
     * @param strDateTime
     * @param pattern
     * @return
     */
    public static Timestamp string2Timestamp(String strDateTime, String pattern) {

        return new Timestamp(string2Date(strDateTime, pattern).getTime());
    }

    /**
     * 字符串转换为 Date  自动匹配格式
     *
     * @param strDate
     * @return 如果传入字符串为null，或者空字符串，则返回null
     */
    public static Date string2Date(String strDate) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        try {
            return DateUtils.parseDate(strDate.trim(), parsePatterns);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MyRuntimeException("日期类型转换错误");
        }
    }

    /**
     * 字符串转换为 Date  传入字符串格式
     *
     * @param strDate 时间
     * @param pattern 字符串 格式
     * @return 如果传入字符串为null，或者空字符串，则返回null
     */
    public static Date string2Date(String strDate, String pattern) {

        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        try {
            return DateUtils.parseDate(strDate.trim(), new String[]{pattern});
        } catch (ParseException e) {
            e.printStackTrace();
            throw new MyRuntimeException("日期类型转换错误");
        }
    }

    /**
     * 前后移动日期
     *
     * @param date
     * @param move -1:向前移动一天，1:向后移动一天
     * @return
     */
    public static Date moveDate(Date date, int move) {

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.DATE, move);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime(); //这个时间就是日期移动之后的时间

    }

    /***
     * 时间字符串转换成long
     * @param strDate
     * @param pattern
     * @return
     */
    public static long string2long(String strDate, String pattern) {
        if (strDate == null || strDate.equals("")) {
            return 0l;
        }
        if (pattern == null || pattern.equals("")) {
            pattern = DateUtil.PATTERN_DATE;
        }
        Date d = string2Date(strDate, pattern);
        if (d == null) {
            return 0L;
        }
        return d.getTime();
    }

    /**
     * 获得当前月初日期 yyyy-MM-01
     *
     * @return
     */
    public static String currentMonthDateToString() {

        return date2String(new Date(),PATTERN_MONTH);
    }

    /**
     * 获取当天开始时间
     *
     * @return
     */
    public static String currentDayDateStartToString(String stime) {
        //(stime.split(" ")[0], DateUtil.PATTERN_DATE);
        Date d = string2Date(stime.split(" ")[0],  DateUtil.PATTERN_DATE);

        return date2String(d, "yyyy-MM-dd 00:00:00");
    }

    /**
     * 获取当天结束时间
     *
     * @return
     */
    public static String currentDayEndDateToString(String stime) {
        //(stime.split(" ")[0], DateUtil.PATTERN_DATE);
        Date d = string2Date(stime.split(" ")[0], DateUtil.PATTERN_DATE);

        return date2String(d, "yyyy-MM-dd 23:59:59");
    }

    /**
     * 名称:  dateMinDiff
     * 功能:  获取两个日期的分钟差
     * 参数:  @param stime
     * 参数:  @param etime
     * 参数:  @param format
     * 参数:  @return
     */
    public static long dateMinDiff(String stime, String etime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        //获得两个时间的毫秒时间差异
        long diff;
        long min = 0;
        try {
            diff = sd.parse(etime).getTime() - sd.parse(stime).getTime();
            min = diff % nd % nh / nm;//计算差多少分钟
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return min;
    }

    /**
     * 名称:  dateSecDiff
     * 功能:  获取两个时间之间之差换算成秒数
     * 参数:  @param stime
     * 参数:  @param etime
     * 参数:  @param format
     * 参数:  @return
     */
    public static long dateSecDiff(String stime, String etime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 60 * 1000;//一分钟的秒数
        long ns = 1000;//一秒钟的毫秒数
        //获得两个时间的毫秒时间差异
        long diff;
        long sec = 0;
        try {
            diff = sd.parse(etime).getTime() - sd.parse(stime).getTime();
            sec = diff / ns;//计算差多少秒
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sec;
    }

    /**
     * 描述:  获取两个时间之间之差换算成毫秒数
     * 名称:  dateMselDiff
     * 参数:  @param stime
     * 参数:  @param etime
     * 参数:  @param format
     * 参数:  @return
     */
    public static long dateMselDiff(String stime, String etime, String format) {
        SimpleDateFormat sd = new SimpleDateFormat(format);
        //获得两个时间的毫秒时间差异
        long diff = 0;
        try {
            diff = sd.parse(etime).getTime() - sd.parse(stime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 名称:  getWeekendNum
     * 功能:  获取两个日期之间的周末天数
     * 参数:  @param startDate
     * 参数:  @param endDate
     * 参数:  @param format
     * 参数:  @return
     */
    public static int getWeekendNum(String startDate, String endDate, String format) {
        List<String> yearMonthDayList = new ArrayList<String>();
        Date start = null, stop = null;
        try {
            start = new SimpleDateFormat(format).parse(startDate);
            stop = new SimpleDateFormat(format).parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start.after(stop)) {
            Date tmp = start;
            start = stop;
            stop = tmp;
        }
        //将起止时间中的所有时间加到List中
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime() <= stop.getTime()) {
            yearMonthDayList.add(new SimpleDateFormat(format).format(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        Collections.sort(yearMonthDayList);
        int num = 0;//周六，周日的总天数
        int size = yearMonthDayList.size();
        int week = 0;
        for (int i = 0; i < size; i++) {
            String day = (String) yearMonthDayList.get(i);
            week = getWeek(day, format);
            if (week == 6 || week == 0) {//周六，周日
                num++;
            }
        }
        return num;
    }

    /**
     * 名称:  getWeekNum
     * 功能:  计算两个日期内周几的天数
     * 参数:  @param startDate
     * 参数:  @param endDate
     * 参数:  @param format
     * 参数:  @param week
     * 参数:  @return
     */
    public static int getWeekNum(String startDate, String endDate, String format, int theWeek) {
        List<String> yearMonthDayList = new ArrayList<String>();
        Date start = null, stop = null;
        try {
            start = new SimpleDateFormat(format).parse(startDate);
            stop = new SimpleDateFormat(format).parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (start.after(stop)) {
            Date tmp = start;
            start = stop;
            stop = tmp;
        }
        //将起止时间中的所有时间加到List中
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(start);
        while (calendarTemp.getTime().getTime() <= stop.getTime()) {
            yearMonthDayList.add(new SimpleDateFormat(format).format(calendarTemp.getTime()));
            calendarTemp.add(Calendar.DAY_OF_YEAR, 1);
        }
        Collections.sort(yearMonthDayList);
        int num = 0;//周六，周日的总天数
        int size = yearMonthDayList.size();
        int week = 0;
        for (int i = 0; i < size; i++) {
            String day = (String) yearMonthDayList.get(i);
            week = getWeek(day, format);
            if (week == theWeek) {
                num++;
            }
        }
        return num;
    }

    public static int getWeek(String date, String format) {
        Calendar calendarTemp = Calendar.getInstance();
        try {
            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = calendarTemp.get(Calendar.DAY_OF_WEEK);
        int value = i - 1;//0-星期日
        return value;
    }

    /**
     * 名称:  getDaysOfMonth2
     * 功能:  获取每月天数
     * <p>
     * 参数:  @param year
     * 参数:  @param month
     * 参数:  @return
     */
    public static int getDaysOfMonth2(int year, int month) {
        int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int days = 0;
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            arr[1] = 29; // 闰年2月29天
        }
        try {
            days = arr[month - 1];
        } catch (Exception e) {
            e.getStackTrace();
        }
        return days;
    }

    /**
     * 名称:  getWeekOfDate
     * 功能:  获取当前日期是周几
     * 参数:  @param dt
     * 参数:  @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    public static String getWeekByDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 名称:  ifWeek
     * 功能:  判断是否为周末
     * 参数:  @param bDate
     * 参数:  @return
     */
    public static boolean ifWeek(String bDate) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date bdate;
        try {
            bdate = format1.parse(bDate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(bdate);
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 名称:  compare_date
     * 功能:  判断两个日期大小
     * 参数:  @param DATE1
     * 参数:  @param DATE2
     * 参数:  @param format
     * 参数:  @return
     */
    public static int compare_date(String DATE1, String DATE2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 名称:  getWeeksByDates
     * 功能:  根据两个日期计算出该时间段有几周
     * 参数:  @param strBefore
     * 参数:  @param strAfter
     * 参数:  @return
     * 参数:  @throws Exception
     */
    public static int getWeeksByDates(String strBefore, String strAfter) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar before = Calendar.getInstance();
        Calendar after = Calendar.getInstance();
        before.setTime(sdf.parse(strBefore));
        after.setTime(sdf.parse(strAfter));
        int week = before.get(Calendar.DAY_OF_WEEK);
        before.add(Calendar.DATE, -week);
        week = after.get(Calendar.DAY_OF_WEEK);
        after.add(Calendar.DATE, 7 - week);
        int interval = (int) ((after.getTimeInMillis() - before
                .getTimeInMillis()) / CONST_WEEK);
        return interval;
    }

    /**
     * 名称:  findDates
     * 功能:  获取指定时间段内的所有日期
     * 参数:  @param dBegin
     * 参数:  @param dEnd
     * 参数:  @return
     */
    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    /**
     * 名称:  findweeksOfYear
     * 功能:  获取指定日期属于该年的第几周
     * 参数:  @param date
     * 参数:  @param format
     * 参数:  @return
     */
    public static Integer findweeksOfYear(String date, String format) {
        Calendar calendarTemp = Calendar.getInstance();
        try {
            calendarTemp.setTime(new SimpleDateFormat(format).parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int i = calendarTemp.get(Calendar.WEEK_OF_YEAR);
        return i;
    }

    /**
     * 名称:  getDatesByweek
     * 功能:  根据某年的第几周的第几天获得该天的日期
     * 参数:  @param week
     * 参数:  @param year
     * 参数:  @param dayOfweek
     * 参数:  @return
     */
    public static String getDatesByweek(String week, String year, Integer dayOfweek) {
        Calendar da = Calendar.getInstance();
        da.setWeekDate(Integer.parseInt(year), Integer.parseInt(week), dayOfweek);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(da.getTime());
    }

    /**
     * 名称:  getMonthsByDates
     * 功能:  通过两个时间段获取该时间段内的月数
     * 参数:  @param dBegin
     * 参数:  @param dEnd
     * 参数:  @return
     *
     * @throws ParseException
     */
    public static Integer getMonthsByDates(String strBefore, String strAfter) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dates = findDates(sdf1.parse(strBefore), sdf1.parse(strAfter));
        Set<String> dateSet = new HashSet<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Date date : dates) {
            dateSet.add(sdf.format(date));
        }
        return dateSet.size();
    }

    /**
     * 名称:  getYearsByDates
     * 功能:  根据指定时间段获取该时间段内的年数
     * 参数:  @param dBegin
     * 参数:  @param dEnd
     * 参数:  @return
     *
     * @throws ParseException
     */
    public static Integer getYearsByDates(String strBefore, String strAfter) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> dates = findDates(sdf1.parse(strBefore), sdf1.parse(strAfter));
        Set<String> dateSet = new HashSet<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        for (Date date : dates) {
            dateSet.add(sdf.format(date));
        }
        return dateSet.size();
    }

    /**
     * 名称:  isDateBefore
     * 功能:  判断时间date1是否在时间date2之前
     * 参数:  @param date1
     * 参数:  @param date2
     * 参数:  @return
     */
    public static boolean isDateBefore(String date1, String date2) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date1).before(df.parse(date2));
        } catch (ParseException e) {
            System.out.print("[SYS] " + e.getMessage());
            return false;
        }
    }

    /**
     * 名称:  daysBetween
     * 功能:  计算两个日期之间相差的天数
     * 参数:  @param smdate 较小的时间
     * 参数:  @param bdate 较大的时间
     * 参数:  @return 相差天数
     * 参数:  @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 名称:  daysBetween
     * 功能:  计算两个日期之间相差天数（字符串的日期格式的计算）
     * 参数:  @param smdate
     * 参数:  @param bdate
     * 参数:  @return
     * 参数:  @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 名称:  formatDate.js
     * 功能:  将毫秒数换算成x天x时x分x秒x毫秒
     * 参数:  @param ms
     * 参数:  @return
     */
    public static String formatDate(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day;
        String strHour = hour < 10 ? "0" + hour : "" + hour;
        String strMinute = minute < 10 ? "0" + minute : "" + minute;
        String strSecond = second < 10 ? "0" + second : "" + second;
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;
        return strDay + "天" + strHour + "时" + strMinute + "分" + strSecond + "秒";
    }

    /**
     * 名称:  getMonthFirstDay
     * 功能:  获取月第一天
     * 参数:  @param c
     * 参数:  @return
     */
    public static String getMonthFirstDay(Calendar c) {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "cn"));
        if (c == null) {
            c = Calendar.getInstance();
        }
        c.set(Calendar.DAY_OF_MONTH, 1);
        return dformat.format(c.getTime());

    }

    /**
     * 名称:  getMonthLastDay
     * 功能:  得到月最后一天
     * 参数:  @param c
     * 参数:  @return
     */
    public static String getMonthLastDay(Calendar c) {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "cn"));
        if (c == null) {
            c = Calendar.getInstance();
        }
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dformat.format(c.getTime());

    }

    /**
     * 名称:  getWeekFirstDay
     * 功能:  获取周第一天
     * 参数:  @param c
     * 参数:  @return
     */
    public static String getWeekFirstDay(Calendar c) {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "cn"));
        if (c == null) {
            c = Calendar.getInstance();
        }
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return dformat.format(c.getTime());
    }

    /**
     * 名称:  getFrontNDay
     * 功能:  得到当前的前N天
     * 参数:  @param c
     * 参数:  @param n
     * 参数:  @return
     */
    public static String getFrontNDay(Calendar c, Integer n) {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("zh", "cn"));
        if (c == null) {
            c = Calendar.getInstance();
        }
        c.add(c.DATE, -n);
        return dformat.format(c.getTime());
    }

    /**
     * 名称:  getTomorrowNDay
     * 功能:  得到当前的未来N天
     * 参数:  @param c
     * 参数:  @param n
     * 参数:  @return
     */
    public static String getTomorrowNDay(Calendar c, Integer n) {
        SimpleDateFormat dformat = new SimpleDateFormat("yyyy-MM-dd", new Locale("zh", "cn"));
        if (c == null) {
            c = Calendar.getInstance();
        }
        c.add(c.DATE, n);
        return dformat.format(c.getTime());
    }

    public static String string2StringHHmm(String stringDate) throws Exception {
        SimpleDateFormat tformat = new SimpleDateFormat("HH:mm", new Locale("zh", "cn"));
        Date date = DateUtils.parseDate(stringDate, parsePatterns);
        Date date2 = DateUtil.string2Date(stringDate, DateUtil.PATTERN_STANDARD);
        return tformat.format(date);
    }

    /**
     * 名称:  getAfterMonth
     * 功能:  获取当前日期后几个月的时间
     * 参数:  @param month
     * 参数:  @param startDate
     * 参数:  @return
     */
    public static String getAfterMonth(int month, String startDate) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(startDate);//初始日期
        } catch (Exception e) {

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, month);//在日历的月份上增加6个月
        String strDate = sdf.format(c.getTime());//的到你想要得6个月后的日期
        return strDate;
    }

    public static void main(String[] args) throws Exception {
        Date currentTime = new Date();//获取当前时间
        //Date stateDate=DateUtil.string2Timestamp("");
        //Long min=currentTime.getTime()-stateDate.getTime()/(1000*60*60);
//		  Long min=(string2Date("20150128095306873").getTime()-System.currentTimeMillis())/(60*1000*60);
//		  System.out.println(string2Date("20150128095306873").getTime());
//		  System.out.println(System.currentTimeMillis());
//		  System.out.println(min);

/*		  boolean flag = isDateBefore("2006-09-09 12:12:12","2006-09-09 12:12:12");
          System.out.println(flag);

		  int i = getWeekNum("2015-04-01", "2015-04-30", "yyyy-MM-dd", 1);
		  System.out.println("周一天数---------------->"+i);*/

        String str1 = "2016-09-03 10:06:05";
        String str2 = "2016-09-03 11:19:22";


        /*long l = dateMselDiff(str1, str2, "yyyy-MM-dd HH:mm:ss");
        System.out.println("毫秒数---------->" + l);*/

        int i = compare_date(str1, str2, parsePatterns[1]);

        System.out.println(i);

		  /*long l1 = string2Date(str2,"yyyy-MM-dd HH:mm").getTime() -string2Date(str1,"yyyy-MM-dd HH:mm").getTime();

		  System.out.println("相差天数---------->"+formatDate.js(l1));

		  System.out.println(getAfterMonth(1, "2016-01-30"));*/
    }
}
