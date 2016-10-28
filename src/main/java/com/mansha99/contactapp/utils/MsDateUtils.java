package com.mansha99.contactapp.utils;

import com.mansha99.contactapp.exceptions.MsInvalidDateTimeException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MsDateUtils {

    public static Timestamp toTimeStamp(String s) throws MsInvalidDateTimeException {
        try {
            java.util.Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
            return new java.sql.Timestamp(date.getTime());
        } catch (Exception ex) {
            throw new MsInvalidDateTimeException(ex.getMessage());
        }
    }

    public static String CurrentTimeStamp() throws MsInvalidDateTimeException {
        try {
            Date d = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df.format(d);
        } catch (Exception ex) {
            throw new MsInvalidDateTimeException(ex.getMessage());
        }
    }

}
