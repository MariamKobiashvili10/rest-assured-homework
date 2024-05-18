package ge.tbcacademy.data.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalendarHelper {
    static XMLGregorianCalendar date;

    public static XMLGregorianCalendar createXMLGregorianCalendar(int year, int month, int day) {
        try {
            date = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(year, month, day, DatatypeConstants.FIELD_UNDEFINED);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        return date;
    }
}
