package com.test.roc.core.util;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 时间工具类
 */
@Slf4j
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * yyyy-MM-dd
     */
    public static String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * yyyyMMdd
     */
    public static String DATE_FORMAT2 = "yyyyMMdd";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * HH:mm:ss
     */
    public static String TIME_FORMAT = "MM-dd HH:mm:ss";


    public static String DATE_SECONDS_FORMAT = "yy-MM-dd HH:mm";


    public static String DATE_HOURS_FORMAT = "yy-MM-dd HH";

    /**
     * 获取秒级时间戳
     *
     * @param localDateTime
     * @return
     */
    public static long localDateTimeToSecond(LocalDateTime localDateTime) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDateTime.atZone(zone).toInstant();
        return instant.getEpochSecond();
    }

    /**
     * String转LocalDateTime
     *
     * @param string
     * @param format
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String string, DateFormatEnum format) {
        if (string == null) {
            return null;
        }
        DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(format.getFormat());
        LocalDateTime localDateTime = LocalDateTime.parse(string, timeDtf);
        return localDateTime;
    }

    /**
     * LocalDateTime 转 String
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, DateFormatEnum format) {
        if (localDateTime == null) {
            return null;
        }
        DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(format.getFormat());
        return localDateTime.format(timeDtf);
    }

    /**
     * String转LocalDate
     *
     * @param string
     * @param format
     * @return
     */
    public static LocalDate stringToLocalDate(String string, DateFormatEnum format) {
        if (string == null) {
            return null;
        }
        DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(format.getFormat());
        LocalDate localDate = LocalDate.parse(string, timeDtf);
        return localDate;
    }

    /**
     * LocalDate 转 String
     *
     * @param localDate
     * @param format
     * @return
     */
    public static String localDateToString(LocalDate localDate, DateFormatEnum format) {
        if (localDate == null) {
            return null;
        }
        DateTimeFormatter timeDtf = DateTimeFormatter.ofPattern(format.getFormat());
        return localDate.format(timeDtf);
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(timestamp, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }

    /**
     * 日期转换string to date
     */
    public static Date stringToDate(String dateStr, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            date = sdf.parse(dateStr);
        } catch (Exception e) {
            log.error("日期转换错误");
        }
        return date;
    }

    /**
     * 日期转换date to String
     */
    public static String dateToString(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern = "yyyyMMdd";
        }
        String str = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            str = sdf.format(date);
        } catch (Exception e) {
            log.error("日期转换错误");
        }
        return str;
    }


    /**
     * 日期+days天数
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }


    /**
     * 获取月份天数
     */
    public static int getMonthMaxDays(Date date) {
        Calendar a = Calendar.getInstance();
        a.setTime(date);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        System.out.println(maxDate);
        return maxDate;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate 较小的时间
     * @param bdate  较大的时间
     * @return 相差天数
     */
    public static int daysBetween(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * LocalDate转Date
     *
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate) {
        if (null == localDate) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.systemDefault());
        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 指定起始日期 ： 指定结束日期 ：指定周几 ，算出区间内指定周几的日期
     *
     * @param dateBegin
     * @param dateEnd
     * @param weekDays
     */
    public static List<String> getDayOfWeekWithinDateInterval(String dateBegin, String dateEnd, int weekDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dateResult = new ArrayList<>();   //如果要换成日期类型的这里定义
//        List<Date> dateResult = new ArrayList<>();   //如果要换成日期类型的这里定义
        Calendar cal = Calendar.getInstance();
        String[] dateInterval = {dateBegin, dateEnd};
        Date[] dates = new Date[dateInterval.length];
        for (int i = 0; i < dateInterval.length; i++) {
            String[] ymd = dateInterval[i].split("[^\\d]+");
            cal.set(Integer.parseInt(ymd[0]), Integer.parseInt(ymd[1]) - 1, Integer.parseInt(ymd[2]));
            dates[i] = cal.getTime();
        }
        for (Date date = dates[0]; date.compareTo(dates[1]) <= 0; ) {
            cal.setTime(date);
            if (cal.get(Calendar.DAY_OF_WEEK) - 1 == weekDays) {
                String format = sdf.format(date);  //控制为 返回什么类型的 相反即可
                dateResult.add(format);  //控制为 返回什么类型的 相反即可
//                dateResult.add(date);
            }
            cal.add(Calendar.DATE, 1);
            date = cal.getTime();
        }
        return dateResult;
    }

    /**
     * 统计两个时间的时间差
     * 相差几秒几毫秒
     */
    public static String getDistanceTime(String startTime, String endTime) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;//天数差
        long hour = 0;//小时数差
        long min = 0;//分钟数差
        long second = 0;//秒数差
        long diff = 0;//毫秒差
        String result = null;
        try {
            final Calendar c = Calendar.getInstance();
            c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
            one = df.parse(startTime);
            c.setTime(one);
            two = df.parse(endTime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            diff = time2 - time1;
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            second = diff / 1000;
            result = second % 60 + "秒" + diff % 1000 + "毫秒";
            StringBuilder sb = new StringBuilder();
            if (day > 0) {
                sb.append(day).append("天");
            }
            if (hour > 0) {
                sb.append(hour).append("小时");
            }
            if (min > 0) {
                sb.append(min).append("分钟");
            }
            sb.append(second % 60).append("秒");
            result = sb.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前时间HH:mm:ss
     *
     * @return
     */
    public static String getCurrentTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime() {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        datestr = df.format(new Date());
        return datestr;
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrentDateTime(String Dateformat) {
        String datestr = null;
        SimpleDateFormat df = new SimpleDateFormat(Dateformat);
        datestr = df.format(new Date());
        return datestr;
    }

    public static String dateToDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(DateUtils.DATE_TIME_FORMAT);
        return df.format(date);
    }

    /**
     * 将日期格式转换为时间戳
     */
    public static long dataToTimeStamp(Date date, String dateformat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
        long time = sdf.parse(sdf.format(date)).getTime();
        return time;
    }

    /**
     * 获取日期的DAY值
     *
     * @param date 输入日期
     * @return
     */
    public static int getDayOfDate(Date date) {
        int d = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        d = cd.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    /**
     * 获取日期的MONTH值
     *
     * @param date 输入日期
     * @return
     */
    public static int getMonthOfDate(Date date) {
        int m = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        m = cd.get(Calendar.MONTH) + 1;
        return m;
    }

    /**
     * 获取日期的YEAR值
     *
     * @param date 输入日期
     * @return
     */
    public static int getYearOfDate(Date date) {
        int y = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        y = cd.get(Calendar.YEAR);
        return y;
    }

    /**
     * 获取星期几
     *
     * @param date 输入日期
     * @return
     */
    public static int getWeekOfDate(Date date) {
        int wd = 0;
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        wd = cd.get(Calendar.DAY_OF_WEEK) - 1;
        return wd;
    }

    /**
     * 获取输入日期的当月第一天
     *
     * @param date 输入日期
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        cd.set(Calendar.DAY_OF_MONTH, 1);
        return cd.getTime();
    }

    /**
     * 获得输入日期的当月最后一天
     *
     * @param date
     */
    public static Date getLastDayOfMonth(Date date) {
        return DateUtils.addDay(DateUtils.getFirstDayOfMonth(DateUtils.addMonth(date, 1)), -1);
    }

    /**
     * 判断是否是闰年
     *
     * @param date 输入日期
     * @return 是true 否false
     */
    public static boolean isLeapYEAR(Date date) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(date);
        int year = cd.get(Calendar.YEAR);

        if (year % 4 == 0 && year % 100 != 0 | year % 400 == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据整型数表示的年月日，生成日期类型格式
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return
     */
    public static Date getDateByYMD(int year, int month, int day) {
        Calendar cd = Calendar.getInstance();
        cd.set(year, month - 1, day);
        return cd.getTime();
    }

    /**
     * 获取年周期对应日
     *
     * @param date  输入日期
     * @param iyear 年数  負數表示之前
     * @return
     */
    public static Date getYearCycleOfDate(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    /**
     * 获取月周期对应日
     *
     * @param date 输入日期
     * @param i
     * @return
     */
    public static Date getMonthCycleOfDate(Date date, int i) {
        Calendar cd = Calendar.getInstance();
        cd.setTime(date);

        cd.add(Calendar.MONTH, i);

        return cd.getTime();
    }

    /**
     * 计算 fromDate 到 toDate 相差多少年
     *
     * @param fromDate
     * @param toDate
     * @return 年数
     */
    public static int getYearByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) - df.get(Calendar.YEAR);
    }

    /**
     * 计算 fromDate 到 toDate 相差多少个月
     *
     * @param fromDate
     * @param toDate
     * @return 月数
     */
    public static int getMonthByMinusDate(Date fromDate, Date toDate) {
        Calendar df = Calendar.getInstance();
        df.setTime(fromDate);

        Calendar dt = Calendar.getInstance();
        dt.setTime(toDate);

        return dt.get(Calendar.YEAR) * 12 + dt.get(Calendar.MONTH) -
                (df.get(Calendar.YEAR) * 12 + df.get(Calendar.MONTH));
    }

    /**
     * 计算年龄
     *
     * @param birthday 生日日期
     * @param calcDate 要计算的日期点
     * @return
     */
    public static int calcAge(Date birthday, Date calcDate) {

        int cYear = DateUtils.getYearOfDate(calcDate);
        int cMonth = DateUtils.getMonthOfDate(calcDate);
        int cDay = DateUtils.getDayOfDate(calcDate);
        int bYear = DateUtils.getYearOfDate(birthday);
        int bMonth = DateUtils.getMonthOfDate(birthday);
        int bDay = DateUtils.getDayOfDate(birthday);

        if (cMonth > bMonth || (cMonth == bMonth && cDay > bDay)) {
            return cYear - bYear;
        } else {
            return cYear - 1 - bYear;
        }
    }

    /**
     * 在输入日期上增加（+）或减去（-）天数
     *
     * @param date 输入日期
     * @param iday 要增加或减少的天数
     */
    public static Date addDay(Date date, int iday) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.DAY_OF_MONTH, iday);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）月份
     *
     * @param date   输入日期
     * @param imonth 要增加或减少的月分数
     */
    public static Date addMonth(Date date, int imonth) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.MONTH, imonth);

        return cd.getTime();
    }

    /**
     * 在输入日期上增加（+）或减去（-）年份
     *
     * @param date  输入日期
     * @param iyear 要增加或减少的年数
     */
    public static Date addYear(Date date, int iyear) {
        Calendar cd = Calendar.getInstance();

        cd.setTime(date);

        cd.add(Calendar.YEAR, iyear);

        return cd.getTime();
    }

    public static long getAgeByBirthday(String date) {

        Date birthday = stringToDate(date, "yyyy-MM-dd");
        long sec = System.currentTimeMillis() - birthday.getTime();

        long age = sec / (1000 * 60 * 60 * 24) / 365;

        return age;
    }

    /**
     * 格式化时间戳为指定格式
     *
     * @param timestamp
     * @param format
     * @return
     */
    public static String getDateFormat(String timestamp, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(new Date(Long.parseLong(timestamp)));
    }

    // 字符串时间转时间戳
    public static Long string2Millis(String dateStr, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
            return simpleDateFormat.parse(dateStr).getTime();
        } catch (Exception e) {
            return 0L;
        }
    } // 字符串时间转时间戳

    public static String millis2String(long millis, String formatStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatStr);
            return simpleDateFormat.format(new Date(millis));
        } catch (NullPointerException e) {
            log.error("时间格式化出错", e.getMessage());
            return "";
        }
    }

    public static boolean validateDatePattern(String dateStr, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        boolean flag = true;
        try {
            Date parse = simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            flag = false;
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        long l = System.currentTimeMillis();
        System.out.println(l);
        Date date1 = new Date(l);
        String s = dateToString(date1, DATE_TIME_FORMAT);
        System.out.println(s);
        String date = getDateFormat(l + "", DATE_TIME_FORMAT);
        Date date2 = DateUtils.stringToDate(date, DATE_TIME_FORMAT);
        System.out.println(date2.getTime());

        System.out.println(string2Millis("2022-06-28 10:56", "yyyy-MM-dd HH:mm"));

        System.out.println(millis2String(1658465280000L, DateFormatEnum.YYYY_MM_DD_HH_MM_SS.getFormat()));

    }
}
