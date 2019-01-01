package com.xhMall.common.util;

import com.xhMall.common.constant.Constant;
import com.xhMall.exception.PrjException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sheting on Administrator
 * DateTime  2018/9/10,22:02
 */
public class CommonDateUtil {

    public static final String DATE_PATTERN_SIMPLE = "yyyyMMdd";

    public static final String DATE_PATTERN_FULL= "yyyyMMddHHmmss";

    public static final String DATE_PATTERN_FULL_SSS= "yyyyMMddHHmmssSSS";

    public static final String DB_DATE_FORMAT = "YYYY/MM/DD";

    public static final String DB_DATE_FORMAT_1 = "YYYYMMDD";

    public static final String DB_DATE_TIME_FORMAT = "YYYY/MM/DD HH24:MI:SS";

    public static final String DB_DATE_TIME_FORMAT_1 = "YYYY/MM/DD HH24:MI";

    public static final String DB_DATE_TIME_1 = "000000";

    public static final String DB_DATE_TIME_2 = "235959";






    /**
     *
     * @param date yyyyMMdd
     * @return
     */
    public static String formatDateToSimpleString(Date date) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_SIMPLE);
            return sdf.format(date);
        }else {
            return "";
        }
    }

    /**
     *
     * @param date yyyyMMddHHmmss
     * @return
     */
    public static String formatDateToSimpleStringFull4(Date date) {
        if (null != date) {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_FULL);
            return sdf.format(date);
        }else {
            return "";
        }
    }

    /**
     *
     * @param value yyyyMMddHHmmss
     * @return
     */
    public static Date formatStringToDate2(String value) {
        if (null != value || Constant.EMPTY.equals(value)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_FULL);
        try {
            return sdf.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     *
     * @param
     * @return
     */
    public static String getCurrentDate() {
        Date date = new Date();
        return formatDateToSimpleString(date);
    }

    /**
     *
     * @param
     * @return
     */
    public static String getCurrentYear() {
        return getCurrentDate().substring(Constant.number_0,Constant.number_4);
    }

    /**
     *
     * @param
     * @return
     */
    public static String getCurrentMonth() {
        return getCurrentDate().substring(Constant.number_4,Constant.number_6);
    }

    /**
     *
     * @param
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN_FULL);
        return format.format(date);
    }

    /**
     * 检查日期格式（“yyyyMMdd”）
     * @param strDate
     * @return
     */
    public static boolean checkSimpleDate(String strDate){

        if (strDate == null ||
                (!(CommonStringUtil.getByteLength(strDate) == Constant.number_8 &&
                CommonStringUtil.isNumber(strDate)))){
            return false;
        } else {
            int year = Integer.parseInt(strDate.substring(Constant.number_0,Constant.number_4));
            int mm = Integer.parseInt(strDate.substring(Constant.number_4,Constant.number_6));
            int dd = Integer.parseInt(strDate.substring(Constant.number_6,Constant.number_8));

            if (year < Constant.YEAR_MIN || year > Constant.YEAR_MAX) {
                return  false;
            }
            if (mm < Constant.number_1 || mm > Constant.number_12) {
                return false;
            }

            if (dd < Constant.number_1 || mm > getLastDayOfMonth(year,mm)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查时间格式（“HHmm”）
     * @param strTime
     * @return
     */
    public static boolean checkTime(String strTime){

        if (strTime == null ||
                (!(CommonStringUtil.getByteLength(strTime) == Constant.number_4 &&
                        CommonStringUtil.isNumber(strTime)))){
            return false;
        } else {
            int hh = Integer.parseInt(strTime.substring(Constant.number_0,Constant.number_2));
            int mm = Integer.parseInt(strTime.substring(Constant.number_2,Constant.number_4));

            if (hh  > Constant.number_23) {
                return  false;
            }
            if (mm > Constant.number_59 ) {
                return false;
            }
        }
        return true;
    }

    /**
     * 转换时间形式 （HH:MM）
     * @param strTime
     * @return
     */
    public static String formatStringToTime(String strTime){

        if (checkTime(strTime)) {
            String hh = strTime.substring(Constant.number_0,Constant.number_2);
            String mm = strTime.substring(Constant.number_2,Constant.number_4);
            strTime = hh + Constant.COLON + mm;
        }

        return strTime;
    }

    /**
     * 转换时间形式 （HHMM）
     * @param strTime
     * @return
     */
    public static String formatTimeToString(String strTime){

        if (CommonStringUtil.isNullOrEmpty(strTime)) {
            strTime = "";
        } else {
            strTime = strTime.replace(Constant.COLON,"");
        }

        return strTime;
    }

    /**
     * 检查日期格式（yyyyMMddHHmmss）
     * @param strDate
     * @return
     */
    public static boolean checkSimpleDate2(String strDate) {
        if (!(CommonStringUtil.getByteLength(strDate) == Constant.number_14 &&
        CommonStringUtil.isNumber(strDate))) {
            return false;
        }else {
            int year = Integer.parseInt(strDate.substring(Constant.number_0,Constant.number_4));
            int mm = Integer.parseInt(strDate.substring(Constant.number_4,Constant.number_6));
            int dd = Integer.parseInt(strDate.substring(Constant.number_6,Constant.number_8));
            int hh = Integer.parseInt(strDate.substring(Constant.number_8,Constant.number_10));
            int mi = Integer.parseInt(strDate.substring(Constant.number_10,Constant.number_12));
            int ss = Integer.parseInt(strDate.substring(Constant.number_12,Constant.number_14));

            if (year < Constant.YEAR_MIN || year > Constant.YEAR_MAX) {
                return  false;
            }
            if (mm < Constant.number_1 || mm > Constant.number_12) {
                return false;
            }

            if (dd < Constant.number_1 || mm > getLastDayOfMonth(year,mm)) {
                return false;
            }

            if (hh  > Constant.number_23) {
                return  false;
            }
            if (mi > Constant.number_59 ) {
                return false;
            }

            if (ss > Constant.number_59 ) {
                return false;
            }
        }

        return true;
    }

    /**
     * 获取月的最后一天
     * @param year
     * @param month
     * @return
     */
    private static int getLastDayOfMonth(int year,int month){
        if (year < Constant.YEAR_MIN || year > Constant.YEAR_MAX) {
            return  0;
        }
        if (month < Constant.number_1 || month > Constant.number_12) {
            return 0;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month-1);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

    }

    /**
     * 计算两个日期间相差的天数
     * @param starDate
     * @param endDate
     * @return
     * @throws ParseException
     * @throws PrjException
     */
    public static int getDiffDate(String starDate,String endDate)throws ParseException ,PrjException {

        if (!checkSimpleDate(starDate) || !checkSimpleDate(endDate)) {
            throw new PrjException();
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_SIMPLE);
        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(starDate));
        long startTime = cal.getTimeInMillis();

        cal.setTime(sdf.parse(endDate));
        long endTime = cal.getTimeInMillis();

        long diffDays = (endTime - startTime)/(1000*3600*24);
        return Integer.parseInt(String.valueOf(diffDays));
    }

    /**
     * yyyyMMdd日期格式转yyyy/MM/dd
     * @param date
     * @return
     */
    public static String formateDateToString(String date ) {
        if (!checkSimpleDate(date)) {
            return "";
        }

        String year = date.substring(Constant.number_0,Constant.number_4);
        String month = date.substring(Constant.number_4,Constant.number_6);
        String day = date.substring(Constant.number_6,Constant.number_8);
        String newDate = year + Constant.SLASH + month + Constant.SLASH + day;
        return  newDate;
    }




    /**
     * 检查有效时间
     * @param starDate yyyyMMdd
     * @param endDate yyyyMMdd
     * @param intervalMonth  间隔月份
     * @return startDate - endDate > 0(1)
     *         startDate + intervalMonth >= endDate(0)
     *         startDate + intervalMonth < endDate(2)
     */
    public static int effectiveTimeCheck(String starDate,String endDate,int intervalMonth) throws ParseException ,PrjException{

        if (!checkSimpleDate(starDate) || !checkSimpleDate(endDate)){
            throw new PrjException();
        }

        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_SIMPLE);
        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(starDate));
        long startTime = cal.getTimeInMillis();

        cal.add(Calendar.MONTH,intervalMonth);
        if (intervalMonth > 0) {
            cal.add(Calendar.DATE,-1);
        }
        long effectiveTime = cal.getTimeInMillis();

        cal.setTime(sdf.parse(endDate));
        long endTime = cal.getTimeInMillis();

        if (endTime - startTime < 0 ) {
            return Constant.number_1;
        } else if (effectiveTime - endTime < 0 ) {
            return Constant.number_2;
        }

        return Constant.number_0;
    }


}
