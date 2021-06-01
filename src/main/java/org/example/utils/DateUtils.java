package org.example.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import com.alibaba.fastjson.JSONObject;

import static sun.util.calendar.CalendarUtils.isGregorianLeapYear;

public class DateUtils {

    private static Logger logger= LoggerFactory.getLogger(DateUtils.class);


    // 默认日期格式
    public static final String DATE_FMT = "yyyy-MM-dd"; // 日期

    public static final String DATE_MM = "yyyy-MM"; // 年月

    public static final String TIME_FMT = "HH:mm:ss"; // 时间

    public static final String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss"; // 日期时间

    public static final String DATE_STRING_MM = "yyyyMM";

    public static final String DATE_STRING_MM_2 = "MM/dd";

    public static final String DATE_STRING_FMT = "yyyyMMdd";

    public static final String DATE_TIMESTAMP_SEC_FMT = "yyyyMMddHHmmss";// 时间戳时间(秒)

    public static final String DATE_TIMESTAMP_MI_FMT = "yyyyMMddHHmmssSSS"; // 时间戳时间（毫秒）

    public static final String DATE_TIME_FMT_MIN= "yyyy-MM-dd HH:mm"; // 日期格式

    public static Date getNextMonthDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
        return calendar.getTime();
    }
    /**
     * 各月份最大天数
     */
    static int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * 可以用于判断Object,String,Map,Collection,String,Array是否为空
     */
    public static boolean isNull(Object value) {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            if (((String) value).trim().replaceAll("\\s", "").equals("")) {
                return true;
            }
        } else if (value instanceof Collection) {
            if (((Collection) value).isEmpty()) {
                return true;
            }
        } else if (value.getClass().isArray()) {
            if (Array.getLength(value) == 0) {
                return true;
            }
        } else if (value instanceof Map) {
            if (((Map) value).isEmpty()) {
                return true;
            }
        } else {
            return false;
        }
        return false;

    }

    public static boolean isNull(Object value, Object... items) {
        if (isNull(value) || isNull(items)) {
            return true;
        }
        for (Object item : items) {
            if (isNull(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取日期
     */
    public static Date getCurrentDateTime() {

        return getCurrentDateTime(DATE_TIME_FMT);
    }

    public static Date getCurrentDate() {

        return getCurrentDate(DATE_FMT);
    }

    public static Date getCurrentTime() {

        return getCurrentTime(TIME_FMT);
    }

    public static Date getCurrentDateTime(String fmt) {

        return dateStrToDate(fmt, getCurrentDateTimeStr(fmt));
    }

    public static Date getCurrentDate(String fmt) {

        return dateStrToDate(fmt, getCurrentDateStr(fmt));
    }

    public static Date getCurrentTime(String fmt) {

        return dateStrToDate(fmt, getCurrentTimeStr(fmt));
    }

    public static String getCurrentDateTimeStr() {

        return getCurrentDateTimeStr(DATE_TIME_FMT);
    }

    public static String getCurrentTimeStr() {

        return getCurrentTimeStr(TIME_FMT);
    }

    public static String getCurrentDateStr() {

        return getCurrentDateStr(DATE_FMT);
    }

    public static String getCurrentDateTimeStr(String fmt) {

        String temp = new SimpleDateFormat(fmt).format(new Date());

        return temp;
    }

    public static String getCurrentTimeStr(String fmt) {

        String temp = new SimpleDateFormat(fmt).format(new Date());

        return temp;
    }

    public static String getCurrentDateStr(String fmt) {

        String temp = new SimpleDateFormat(fmt).format(new Date());

        return temp;
    }

    public static String dateToDateStr(Date date) {

        String temp = new SimpleDateFormat(DATE_TIME_FMT).format(date);

        return temp;
    }

    public static String dateToDateStr(String fmt, Date date) {
        if(date == null){
            return  null;
        }
        String temp = new SimpleDateFormat(fmt).format(date);

        return temp;
    }

    /**
     * 获取月份
     * @param date
     * @return
     */
    public static String getMonthStr(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Integer month = cal.get(Calendar.MONTH) + 1;
        if(month<10){
            return "0" + month;
        }
        return String.valueOf(month);
    }

    /**
     * 转换为日期对象
     */
    public static Date dateStrToDate(String date) {
        if(StringUtils.isEmpty(date)){
            return  null;
        }
        Date temp = null;
        try {
            temp = new SimpleDateFormat(DATE_FMT).parse(date);
        } catch (ParseException e) {
            logger.error("",e);
        }
        return temp;
    }

    public static Date dateStrToDate(String fmt, String date) {
        Date temp = null;
        try {
            temp = new SimpleDateFormat(fmt).parse(date);
        } catch (Exception e) {
            logger.error("",e);
        }
        return temp;
    }

    /**
     * 格式化日期
     */
    public static String formatDateTime(Date date) {

        return formatDateTime(DATE_TIME_FMT, date);
    }

    /**
     *
     * @Title: formatDate_yyyyMMdd
     * @Description:
     * @author lvxiaoxi lvxx@doordu.com
     * @date 2017年6月21日 上午11:02:19
     * @param date
     * @return
     * @return String
     */
    public static String formatDate_yyyyMMdd(Date date) {
        return formatDateTime(DATE_STRING_FMT, date);
    }

    public static String formatDateTime(String fmt, Date date) {
        if (isNull(fmt) || isNull(date)) {
            return null;
        }
        String temp = new SimpleDateFormat(fmt).format(date);

        return temp;
    }

    public static String formatTime(Date date) {
        return formatTime(TIME_FMT, date);
    }

    public static String formatTime(String fmt, Date date) {
        if (isNull(fmt) || isNull(date)) {
            return null;
        }
        String temp = new SimpleDateFormat(fmt).format(date);

        return temp;
    }

    public static String formatDate(Date date) {
        return formatDate(DATE_FMT, date);
    }

    public static String formatDateByDate(Date date) {
        return formatDate(DATE_TIME_FMT_MIN, date);
    }

    public static String formatDateMonth(Date date) {
        return formatDate(DATE_MM, date);
    }
    public static String formatDate(String fmt, Date date) {
        if (isNull(fmt) || isNull(date)) {
            return null;
        }
        String temp = new SimpleDateFormat(fmt).format(date);

        return temp;
    }

    public static String formatNumber(String fmt, Object value) {
        if (isNull(fmt) || isNull(value)) {
            return null;
        }
        String temp = new DecimalFormat(fmt).format(value);

        return temp;
    }

    /**
     * 交换两个日期
     */
    public static void changeDate(Date date1, Date date2) {
        if (isNull(date1, date2)) {
            return;
        }
        //判断两个时间的大小
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        if (c1.after(c2)) {
            date1 = c2.getTime();
            date2 = c1.getTime();
        }
    }

    /**
     * 获取date的年月日格式
     * @param date
     * @return
     */
    public static Date getYMD(Date date) {
        Date tmp = null;
        if (isNull(date)) {
            return date;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        try {
            tmp = sdf.parse(s);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return tmp;
    }

    /**
     * 获取date的0点
     * @param date
     * @return
     */
    public static Date getYMD000(Date date) {
        if (date == null) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        return c.getTime();
    }

    /**
     * 获取date的23:59:59
     * @param date
     * @return
     */
    public static Date getYMD235959(Date date) {
        if (date == null) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return c.getTime();
    }

    /**
     * 当天 yyyy-MM-dd 00:00:00
     * @return
     */
    public static Date getYMD000() {
        return getYMD000(null, null);
    }

    /**
     * 获取日期yyyy-MM-dd 00:00:00
     * field和amount都不是null这两个参数才有效
     * @param field 变化字段
     * @param amount 变化量
     * @return
     */
    public static Date getYMD000(Integer field, Integer amount) {
        Calendar c = Calendar.getInstance();
        if (field != null && amount != null) {
            c.add(field, amount);
        }
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 00, 00, 00);
        return c.getTime();
    }

    /**
     * 当天 yyyy-MM-dd 23:59:59
     * @return
     */
    public static Date getYMD235959() {
        return getYMD235959(null, null);
    }

    /**
     * 获取日期yyyy-MM-dd 23:59:59
     * field和amount都不是null这两个参数才有效
     * @param field 变化字段
     * @param amount 变化量
     * @return
     */
    public static Date getYMD235959(Integer field, Integer amount) {
        Calendar c = Calendar.getInstance();
        if (field != null && amount != null) {
            c.add(field, amount);
        }
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return c.getTime();
    }

    /**
     * 比较两个日期相差的年数
     */
    public static int compareYear(Date date1, Date date2) {
        if (isNull(date1) || isNull(date2)) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if (c1.equals(c2)) {
            return 0;
        }

        if (c1.after(c2)) {
            Calendar temp = c1;
            c1 = c2;
            c2 = temp;
        }
        //计算差值
        int result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return result;
    }

    /**
     * 比较两个日期相差的天数
     */
    public static int compareDay(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;

        Calendar d1 = Calendar.getInstance();
        d1.setTime(date1);
        Calendar d2 = Calendar.getInstance();
        d2.setTime(date2);
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        /*
         * 经过上面的处理，保证d2在d1之后
         * 下面这个days可能小于0，因为d2和d1可能不在同一年里，这样的话虽然d1的年份小，但其在一年中的"第几天"却可能比d2大。
         */
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {//如果不在同一年
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                /*
                 * 给定此 Calendar 的时间值，返回指定日历字段可能拥有的最大值。
                 * 例如，在某些年份中，MONTH 字段的实际最大值是 12，而在希伯来日历系统的其他年份中，该字段的实际最大值是 13。
                 * DAY_OF_YEAR：闰年366？
                 */
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;

    }

    /**
     * 比较两个日期相差的周数
     */
    public static int compareWeek(Date date1, Date date2) {
        if (isNull(date1) || isNull(date2)) {
            return 0;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);

        if (c1.equals(c2)) {
            return 0;
        }

        if (c1.after(c2)) {
            Calendar temp = c1;
            c1 = c2;
            c2 = temp;
        }
        //计算差值
        int result = c2.get(Calendar.WEEK_OF_MONTH) - c1.get(Calendar.WEEK_OF_MONTH);
        return result;
    }

    /**
     * 比较两个日期相差的月数
     */
    public static int compareMonth(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;

        int iMonth = 0;
        int flag = 0;
        try {
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(date1);

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(date2);

            if (objCalendarDate2.equals(objCalendarDate1))
                return 0;
            if (objCalendarDate1.after(objCalendarDate2)) {
                Calendar temp = objCalendarDate1;
                objCalendarDate1 = objCalendarDate2;
                objCalendarDate2 = temp;
            }

            if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR))
                iMonth = ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR)) * 12 + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
            else
                iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;

        } catch (Exception e) {
            logger.error("",e);
        }
        return iMonth;
    }

    /**
     * 比较两个日期相差的秒数
     */
    public static long compareTime(Date date1, Date date2) {
        if (date1 == null || date2 == null)
            return 0;

        Calendar c = Calendar.getInstance();

        c.setTime(date1);
        long l1 = c.getTimeInMillis();

        c.setTime(date2);
        long l2 = c.getTimeInMillis();

        return (l2 - l1) / 1000;
    }

    //设置时间
    private static Date addDateTime(Date date, int type, int num) {
        if (date == null) {
            return null;
        }
        //初始化日历对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //根据类型添加
        switch (type) {
            case 1:        //添加年
                cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + num);
                break;
            case 2:        //添加月
                cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + num);
                break;
            case 3:        //添加日
                cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
                break;
            case 4:        //添加时
                cal.set(Calendar.HOUR, cal.get(Calendar.HOUR) + num);
                break;
            case 5:        //添加分
                cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + num);
                break;
            case 6:        //添加秒
                cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) + num);
                break;
        }

        //返回操作结果
        return cal.getTime();
    }

    //设置日期时间
    private static Date setDateTime(Date date, int type, int num) {
        if (date == null) {
            return null;
        }
        //初始化日历对象
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        //根据类型添加
        switch (type) {
            case 1:        //添加年
                cal.set(Calendar.YEAR, num);
                break;
            case 2:        //添加月
                cal.set(Calendar.MONTH, num);
                break;
            case 3:        //添加日
                cal.set(Calendar.DATE, num);
                break;
            case 4:        //添加时
                cal.set(Calendar.HOUR_OF_DAY, num);
                break;
            case 5:        //添加分
                cal.set(Calendar.MINUTE, num);
                break;
            case 6:        //添加秒
                cal.set(Calendar.SECOND, num);
                break;
        }

        //返回操作结果
        return cal.getTime();
    }

    /**
     * 设置年、月、日
     */
    public static Date setYMD(Date date, int year, int month, int day) {

        return setYear(setMonth(setDate(date, day), month), year);
    }

    public static Date setYear(Date date, int num) {
        return addDateTime(date, 1, num);
    }

    public static Date setMonth(Date date, int num) {
        return addDateTime(date, 2, num);
    }

    public static Date setDate(Date date, int num) {
        return addDateTime(date, 3, num);
    }

    /**
     * 设置时、分、秒
     */
    public static Date setHMS(Date date, int hour, int minute, int second) {

        return setHour(setMinute(setSecond(date, second), minute), hour);
    }

    public static Date setHour(Date date, int num) {
        return setDateTime(date, 4, num);
    }

    public static Date setMinute(Date date, int num) {
        return setDateTime(date, 5, num);
    }

    public static Date setSecond(Date date, int num) {
        return setDateTime(date, 6, num);
    }

    /**
     * 添加年、月、日、时、分、秒
     */
    public static Date addYear(Date date, int num) {
        return addDateTime(date, 1, num);
    }

    public static Date addMonth(Date date, int num) {
        return addDateTime(date, 2, num);
    }

    public static Date addDate(Date date, int num) {
        return addDateTime(date, 3, num);
    }

    /**
     * 添加年、月、日
     */
    public static Date addYMD(Date date, int year, int month, int day) {

        return addYear(addMonth(addDate(date, day), month), year);
    }

    public static Date addHour(Date date, int num) {
        return addDateTime(date, 4, num);
    }

    public static Date addMinute(Date date, int num) {
        return addDateTime(date, 5, num);
    }

    public static Date addSecond(Date date, int num) {
        return addDateTime(date, 6, num);
    }

    /**
     * 添加时、分、秒
     */
    public static Date addHMS(Date date, int hour, int minute, int second) {

        return addHour(addMinute(addSecond(date, second), minute), hour);
    }

    /**
     * 月的第一天
     */
    public static Date getMonthFirstDay(int i) {
        // i = 0 是本月 ,1 上一月
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -i);
        c.set(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * 月的最后一天
     * @param i 0:本月 -n：前面N月 n:后面N月
     * @return
     */
    public static Date getMonthLastDay(int i) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, i);
        c.set(Calendar.DAY_OF_MONTH, 1);
        c.roll(Calendar.DAY_OF_MONTH, -1);

        return c.getTime();
    }

    /**
     * 将Date 转换为 java.time.LocalDate
     *
     * @param date
     * @return
     * @see java.time.LocalDate
     */
    public static LocalDate date2LocalDate(Date date) {
        LocalDate ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ld;
    }

    /**
     * 将Date 转换为 java.time.LocalDateTime
     *
     * @param date
     * @return
     * @see java.time.LocalDateTime
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        LocalDateTime ld = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ld;
    }

    /**
     *
     * @Title: getMSFrom1970
     * @Description: 获取指定时间距离1970年1月1日以来的秒数
     * @param time
     * @return
     */
//	public static long getSecFrom1970(LocalDateTime time){
//		String zoneid = DoorduEnv.get("timezone");
//		long s = time.toEpochSecond(ZoneOffset.of(zoneid));
//		return s;
//	}

    /**
     * @param @param  date 10位整数
     * @param @return 参数
     * @return String    返回类型
     * @throws
     * @Title: dateLong2dateString
     * @Description: 整数转日期
     */
    public static String dateLong2dateString(Long date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date(date * 1000));
    }

    /**
     * @param date 指定日期
     * @param num  相隔天数
     * @return
     * @Title:getAfterDateFromNumDay
     * @Description: 获取从指定日期向未来相隔天数的日期
     */
    public static Date getAfterDateFromNumDay(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + num);
        return c.getTime();

    }

    /**
     * @param date 指定日期
     * @param num  相隔天数
     * @return
     * @Title:getAfterDateFromNumMonth
     * @Description: 获取从指定日期向未来相隔月数的日期
     */
    public static Date getAfterDateFromNumMonth(Date date, int num) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        c.set(Calendar.MONTH, month + num);
        return c.getTime();

    }

    public static int getDayOfMonth(String yearMonth){
        String dd = getLastDayForMonth(yearMonth+"-01");
        String day =dd.split("-")[2];
        return Integer.parseInt(day);
    }



    /**
     * 获取指定日期所在月的最后一天
     *
     * @param datadate
     * @return
     * @throws Exception
     */
    public static String getLastDayForMonth(String datadate) {
        Date date = null;
        String day_last = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(datadate);
        } catch (ParseException e) {
            logger.error("",e);
        }
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);     //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天
        day_last = format.format(calendar.getTime());
        return day_last;
    }

    /***
     * 获取指定日期所在月的第一天
     * @param datadate
     * @return
     * @throws Exception
     */
    public static String getFirstDayForMonth(String datadate) {
        Date date = null;
        String day_first = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(datadate);
        } catch (ParseException e) {
            logger.error("",e);
        }
        //创建日历
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        day_first = format.format(calendar.getTime());
        return day_first;
    }

    /**
     * 获取某年某周的第一天 或 某年某周的最后一天
     *
     * @throws
     * @Title:getFirstDayOfWeek
     * @Description:
     * @param:@param year 某年
     * @param:@param week 某周
     * @param:@param type 类型 0-获取第一天  1-获取最后一天
     * @param:@return
     * @return:String
     */
    public static String getFirstAndLastDayOfWeek(int year, int week, int type) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置周
        cal.set(Calendar.WEEK_OF_YEAR, week);
        //设置该周第一天为星期一
        if (type == 0) {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        } else {
            cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        }
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String DayOfWeek = sdf.format(cal.getTime());

        return DayOfWeek;
    }

    /**
     * 根据传入日期获得当前日期所在周的第一天
     *
     * @throws
     * @Title:getFirstDayOfWeek
     * @Description:
     * @param:@param dateStr 传入日期字符串
     * @param:@return
     * @return:String
     */
    public static String getFirstDayOfWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_FMT); // 设置时间格式
        Date InputDate = null;
        try {
            InputDate = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("",e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(InputDate);
        // 判断要计算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        int day = cal.get(Calendar.DAY_OF_WEEK);// 获得当前日期是一个星期的第几天
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        return sdf.format(cal.getTime());
    }

    /**
     * 根据传入日期获得当前日期所在周的最后一天
     *
     * @return
     */
    public static String getLastDayOfWeek(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.DATE_FMT); // 设置时间格式
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            logger.error("",e);
        }
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        int m = c.get(Calendar.MONTH);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        return sdf.format(c.getTime());
    }

    /***
     *获取两个日期相隔的周数
     * @param date2
     * @param date1
     * @return
     */
    public static int getCompareWeek(Date date2, Date date1) {
        long dt1 = date1.getTime();
        long dt2 = date2.getTime();
        Long weeks = (dt2 - dt1) / (7 * 24 * 60 * 60 * 1000);
        return Integer.parseInt(String.valueOf(weeks));
    }

    public static List<String> range(Date start, Integer days) {
        List<String> dateRange = new ArrayList<>(days);
        for (int i = 0; i < days; i++) {
            dateRange.add(formatDate(addDate(start, i)));
        }
        return dateRange;
    }



    public static List<String> rangeMonth(Date start, Integer month) {
        List<String> dateRange = new ArrayList<>(month);
        for (int i = 0; i < month; i++) {
            dateRange.add(formatDateMonth(addMonth(start, i)));
        }
        return dateRange;
    }

    public static Date setDateStart(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }


    public static Date setDateStart(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }


    public static Date setDateEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**给日期加一天*/
    public static Date setAddDateEnd(Date date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);

        //如果是0时0分0秒，就自动补成23:59:59
        if (format.indexOf("00:00:00") != -1) {

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(Calendar.HOUR_OF_DAY, 23);
            c.set(Calendar.MINUTE, 59);
            c.set(Calendar.SECOND, 59);

            return c.getTime();
        }

        return date;
    }

    public static Date startOfToday() {
        return setDateStart(new Date());
    }

    public static Date endOfToday() {
        return setDateEnd(new Date());
    }

    public static Date getMetaDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1970);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 校验字符串是否是有效的日期
     * 支持格式：
     * yyyy-MM-dd
     * yyyy-MM-dd HH24
     * yyyy-MM-dd HH24:mi
     * yyyy-MM-dd HH24:mi:ss
     * @param date
     * @return
     */
    public static boolean isValidDate(String date) {
        try {
            int length = date.length();
            if(length!=19){
                switch (length){//自动补齐时间时分秒
                    case 10: date +=" 00:00:00"; break;
                    case 13: date +=":00:00";break;
                    case 16: date +=":00";break;
                    default:break;
                }
            }
            int year = Integer.parseInt(date.substring(0, 4));
            if (year <= 0)
                return false;
            int month = Integer.parseInt(date.substring(5, 7));
            if (month <= 0 || month > 12)
                return false;
            int day = Integer.parseInt(date.substring(8, 10));
            if (day <= 0 || day > DAYS[month])
                return false;
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
                return false;
            }
            int hour = Integer.parseInt(date.substring(11, 13));
            if (hour < 0 || hour > 23)
                return false;

            int minute = Integer.parseInt(date.substring(14, 16));
            if (minute < 0 || minute > 59)
                return false;

            int second = Integer.parseInt(date.substring(17, 19));
            if (second < 0 || second > 59)
                return false;
        } catch (Exception e) {
            logger.error("",e);
            return false;
        }
        return true;
    }
    public static int getIntegerCurrentDate(){
        Long nowTime=(new Date().getTime()/1000);
        int nowDate=nowTime.intValue();
        return nowDate;
    }

    public static Integer getIntegerDate(Date date){
        if(null==date){
            return null;
        }
        Long nowTime=(date.getTime()/1000);
        int nowDate=nowTime.intValue();
        return nowDate;
    }

    /**
     * 将毫秒时间格式化为HH:MM:SS
     * @param ms 毫秒时间
     * @return HH:MM:SS
     */
    public static String formatTimeToHHMMSS(long ms){
        //初始化Formatter的转换格式。
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
        String hms = formatter.format(ms);
        return hms;
    }


    /**
     * 时间字符串转换数值类型
     *
     * @param dateTime
     * @return
     */
    public static int dateTimeToInteger(String dateTime) {
        try{
            DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat(DATE_TIME_FMT);
            Date  date = YYYY_MM_DD_MM_HH_SS.parse(dateTime);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            long time = calendar.getTimeInMillis();

            Double dTime = time / 1000.00;
            return dTime.intValue();
        }catch (Exception ex){
            logger.error(JSONObject.toJSONString(ex));
        }
        return -1;
    }

    /**
     * 数值类型转换时间字符串
     *
     * @param seconds
     * @return
     */
    public static String integerToDateTimeStr(int seconds) {
        try{
            long millions = new Long(seconds).longValue() * 1000;
            return longToDateTimeStr(millions);
        }catch (Exception ex){
            logger.error("",ex);
        }
        return null;
    }
    public static String longToDateTimeStr(Long seconds) {
        long millions = seconds.longValue();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millions);

        String dateTime = DateUtils.formatDate(calendar.getTime());
        return dateTime;
    }

    public static int dateTimeToInteger(Date date) {
        try{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            long time = calendar.getTimeInMillis();

            Double dTime = time / 1000.00;
            return dTime.intValue();
        }catch (Exception ex){
            logger.error("",ex);
        }
        return 0;
    }

    /**
     * 判断时间是否在时间段内 lvxiaoxi
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
        } else if (nowTime.compareTo(beginTime) == 0 || nowTime.compareTo(endTime) == 0) {
            return true;
        } else{
            return false;
        }
    }

    /**
     * 获取每月1号凌晨零点
     * @return
     */
    public static Date initDateByMonth(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 检查当前时间是否账单生成时间  （每月一号凌晨0点~5点）
     * @param endHour
     * @return
     */
    public static boolean checkGenerateBillTime(Integer endHour){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, endHour);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date endTime = calendar.getTime();
        return DateUtils.belongCalendar(new Date(), initDateByMonth(), endTime);
    }

    /**
     * @param date 月份
     * @param type 0 第一天 非0 最后一天
     * @return 获取 201901 月份的第一条或者最后一天
     */
    public static String getIntToDateString(Integer date,Integer type){
        String s = dateToDateStr(dateStrToDate(DATE_STRING_MM, date.toString()));
        if(type==0){
            return getFirstDayForMonth(s);
        }
        return getLastDayForMonth(s);
    }


//    public static void main(String args[]) throws ParseException {
////        System.out.println(formatDateTime(DATE_FMT, getMonthFirstDay(0)));
////        System.out.println(formatDateTime(DATE_FMT, getMonthLastDay(0)));
////        System.out.println(formatDateTime(DATE_FMT, addDate(new Date(), -1)));
////        System.out.println(dateStrToDate(DATE_TIME_FMT, "2017-03-20 15:30:00"));
////        System.out.println(getFirstDayForMonth("2017-02-20"));
////        System.out.println(getLastDayForMonth("2017-02-20"));
////        System.out.println(getFirstAndLastDayOfWeek(2017, 5, 0));
////        System.out.println(getFirstAndLastDayOfWeek(2017, 5, 1));
////        System.out.println(getFirstDayOfWeek("2017-02-27"));
////        System.out.println(getLastDayOfWeek("2017-02-27"));
////        System.out.print("s");
//        //System.out.println(formatDateTime(DATE_FMT,getCurrentDate()));
//
//       // System.out.println(dateToDateStr(setDateStart(new Date(),-7)));
//
////        System.out.println(getYMD000(new Date()));
////        System.out.println(getYMD235959(new Date()));
////        System.out.println(integerToDateTimeStr(1552547083));
//       // System.out.println(getYMD000(new Date()));
//       // System.out.println(getYMD235959(new Date()));
//        //账单
////    	Integer nowCycle = Integer.parseInt(DateUtils.formatDate(DateUtils.DATE_STRING_MM,new Date()));
//    	System.out.println(getYMD(new Date()));
//    }


    public static Date setBeginDate(String beginDateStr) {
        //如果时间字符串中只精确到分，那么开始时间加起00秒
        if(beginDateStr.length()<19){
            return DateUtils.dateStrToDate(DateUtils.DATE_TIME_FMT,beginDateStr+":00");
        }else
            return DateUtils.dateStrToDate(DateUtils.DATE_TIME_FMT,beginDateStr);
    }


    public static Date setEndDate(String endDateStr) {
        //如果时间字符串中只精确到分，那么结束时间加起59秒
        if(endDateStr.length()<19){
            return DateUtils.dateStrToDate(DateUtils.DATE_TIME_FMT,endDateStr+":59");
        }else
            return DateUtils.dateStrToDate(DateUtils.DATE_TIME_FMT,endDateStr);
    }

    public static Date strToDate(String dateString) {
        Date date = null;
        try {
            DateFormat YYYY_MM_DD_MM_HH_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = YYYY_MM_DD_MM_HH_SS.parse(dateString);
        } catch (ParseException ex) {
            date = new Date();
            logger.error("",ex);
        }
        return date;
    }
    /**
     * 获取两个时间之间的天数
     * @return
     * @throws Exception
     */
    public static int getDay(String beginTime,String endTime) {
        int compareTo = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);
            compareTo = date1.compareTo(date2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return compareTo;
    }

    public  static Date  dateStrToDateMin(String datesStr)
    {
        Date date=null;
        date=dateStrToDate(DATE_TIME_FMT_MIN,datesStr);
        if (null==date)
        {
            date=dateStrToDate(DATE_TIME_FMT,datesStr);
        }
        if(null==date){
            dateStrToDate(datesStr);
        }
        return date;
    }
    public static void main(String[] args) throws  Exception{

//       String d1 = "2030-08-08 23:10:00";
//       String d2 = "2040-08-08 12:00:00";
//
//       int result = getDay(d1,d2);
//        System.out.println(result);
        String endTime = "2020-08-09";
        Date dd=DateUtils.dateStrToDate(DATE_TIME_FMT_MIN,endTime);
        if(null==dd)
        {
            dd=DateUtils.dateStrToDate(endTime);
        }
        System.out.println(dateToDateStr(dd));
    }
}

