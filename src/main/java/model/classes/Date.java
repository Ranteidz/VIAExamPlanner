package model.classes;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Represents a date
 */
public class Date {
    private int day;
    private int month;
    private int year;

    /**
     * Constructs a new Date object in memory.
     * @param day day of the month
     * @param month month of the year
     * @param year year of the time
     */
    public Date(int day, int month, int year) {
        set(day, month, year);
    }

    /**
     * Sets the date to parameters.
     * @param day day of the month
     * @param month month of the year
     * @param year year of the time
     */
    public void set(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Returns the day of month.
     * @return day of the month
     */
    public int getDay() {
        return day;
    }

    /**
     * Return the month of the year.
     * @return month of the year
     */
    public int getMonth() {
        return month;
    }

    /**
     * Updates the day to parameter.
     * @param day new day of the month
     */
    public void setDay(int day)
    {
        this.day = day;
    }

    /**
     * Updates the month to parameter.
     * @param month new month of the year
     */
    public void setMonth(int month)
    {
        this.month = month;
    }

    /**
     * Update the year.
     * @param year new year
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * Return the year of the date.
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * Returns a copy of the date
     * @return copy of date
     */
    public Date copy()
    {
        return new Date(day, month, year);
    }

    /**
     * Compares the date to parameter object
     * @param obj object to be compared to
     * @return true if the objects are the same
     */
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Date))
            return false;
        Date otherDate = (Date) obj;
        return otherDate.day == this.day && otherDate.month == this.month
                && otherDate.year == this.year;
    }

    /**
     * Returns a StringProperty of the date, formatted for SQL (yyyy-mm-dd)
     * @return date as StringProperty
     */
    public StringProperty dateProperty() {
        StringProperty date = new SimpleStringProperty();
        date.set(toString());
        return date;
    }

    /**
     * Returns a StringProperty of the date, formatted in dd/mm/yyyy
     * @return formatted date as StringProperty
     */
    public StringProperty formattedDateProperty() {
        StringProperty date = new SimpleStringProperty();
        date.set(String.format("%d/%02d/%d", day, month, year));
        return date;
    }

    public String toString() {
        return year+"-"+((month < 10) ? ("0" + month) : month)+"-"+day;
    }
}
