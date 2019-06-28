package org.cdut.tzg.utils;

import java.util.Arrays;
import java.util.Date;

/**
 * @author anlan
 * @date 2019/6/28 10:09
 */
public class TimeUtils {

    /**
     * 返回年月日
     *  2019-6-28
     */
    public static String date2Day(Date date){
        String d = date.toLocaleString();
        String[] split = d.split(" ");
        return split[0];
    }

    /**
     * 返回时分秒
     *  18:10:56
     */
    public static String date2Second(Date date){
        String d = date.toLocaleString();
        String[] split = d.split(" ");
        return split[1];
    }

    /**
     * 返回年月日 时分秒
     *  2019-6-28 18:10:56
     */
    public static String date(Date date){
        return date.toLocaleString();
    }
}
