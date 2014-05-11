package lms.model.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Mikhail Perepletchikov
 */

public class DateUtil {

    private static DateUtil dateUtil;
    private String date = null;

    /** NOTE:
     *  the following code fragment illustrates an example of using a Singleton design pattern 
     **/    
    // private constructor prevents instantiation from other classes
    private DateUtil() {}    
    // make sure that only one instance of DateUtil can be created
    public static DateUtil getInstance() {
        if (dateUtil == null)
            dateUtil = new DateUtil();
        return dateUtil;
    }    
    /** end of Singleton example **/ 
    
    public String getDate() {
        return date;
    }

    public void setDate(String currentDate) {
        this.date = currentDate;
    }

    /**
     * calculates the difference (in number of days) between the provided borrow
     * date and current date. make sure that the provided borrow date is kept in
     * the required format (dd-MM-yyyy e.g. "25-01-2014")
     **/
    public int getElapsedDays(String borrowDate) {
        // creates two calendars instances
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        /** sets the dates for both calendar instances **/
        // first date is the holding borrow date
        cal1.setTime(createDate(borrowDate));
        // second date is the return (i.e. current) date
        cal2.setTime(createDate(date));

        // calculates difference in milliseconds
        long diff = cal2.getTimeInMillis() - cal1.getTimeInMillis();
        // calculates difference in days
        long diffDays = diff / (24 * 60 * 60 * 1000);    
        return (int) diffDays;
    }

    private Date createDate(String dateString) {
        DateFormat formatter;
        try {
            formatter = new SimpleDateFormat("dd-MM-yyyy");
            return (Date) formatter.parse(dateString);
        } catch (ParseException e) {
            e.getStackTrace();
        }
        return null;
    }
}
