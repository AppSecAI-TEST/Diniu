package com.workapp.auto.carterminal.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 字符串工具类
 */
public class StringUtil {
    public static String getPhoneNumberWithHide(String phone) {
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    public static String geIdCardNumberWithHide(String IdCardNumber) {
        return IdCardNumber.replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2");
    }

    public static String TimeStamp2YearMonth(long timestamp) {
        return new SimpleDateFormat("yyyy-MM").format(new Date(
                timestamp * 1000));
    }

    public static String TimeStamp2Day(long timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date(
                timestamp * 1000));
    }

    public static String TimeStamp2DayDot(long timestamp) {
        return new SimpleDateFormat("yyyy.MM.dd").format(new Date(
                timestamp * 1000));
    }

    public static String TimeStamp2Date(long timestamp) {
        return new SimpleDateFormat("MM-dd HH:mm").format(new Date(
                timestamp * 1000));
    }

    public static String TimeStamp2Year(long timestamp) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(
                timestamp * 1000));
    }

    public static String TimeStamp2HourMin(long timestamp) {
        return new SimpleDateFormat("HH:mm").format(new Date(
                timestamp * 1000));
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.replace(" ", "").isEmpty();
    }

    public static boolean isPhoneNumber(String string) {
        if (string.length() != 11) {
            return false;
        }

        if (string.charAt(0) != '1') {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLoginPasswordOk(String password) {
        String regex = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$";
        return password.matches(regex);
    }

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isToday(String day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = simpleDateFormat.parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean isYesterday(String day) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = simpleDateFormat.parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 把日期换成时间戳
     */
    public static long string2time(String arg) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = simpleDateFormat.parse(arg);
        return date.getTime();
    }


    /**
     * 保留两位小数
     */
    public static String decimalFormat2(double money) {
        return new DecimalFormat("#0.00").format(money);
    }
    /**
     * 保留1位小数
     */
    public static String decimalFormat1(double money) {
        return new DecimalFormat("#0.0").format(money);
    }
}
