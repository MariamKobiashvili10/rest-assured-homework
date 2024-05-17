package ge.tbcacademy.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

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
