package model.beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Date {
    private int day;
    private int month;
    private int year;

    private int monthDays[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Date(int day, int month, int year) {
        set(day, month, year);
    }

    public void set(int day, int month, int year) /*throws InvalidDate*/{
//        if (year < 0)
//            throw new InvalidDate();
//
//        if (month < 1 || month > 12)
//            throw new InvalidDate();
//
//        if (day < 1 || day > numberOfDaysInMonth(month))
//            throw new InvalidDate();

        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }


    private int numberOfDaysInMonth(int monthNumber)
    {
        if (monthNumber < 1 || monthNumber > 12)
            return -1;
        else if (isLeapYear() && monthNumber == 2)
            return monthDays[1] + 1;
        else
            return monthDays[monthNumber - 1];
    }

    private boolean isLeapYear()
    {
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public Date copy()
    {
        return new Date(day, month, year);
    }

    public boolean equals(Object obj)
    {
        if (!(obj instanceof Date))
            return false;
        Date otherDate = (Date) obj;
        return otherDate.day == this.day && otherDate.month == this.month
                && otherDate.year == this.year;
    }

    public Date getDate() {
        return new Date(day, month, year);
    }

    public StringProperty dateProperty() {
        StringProperty date = new SimpleStringProperty();
        date.set(toString());
        return date;
    }

    public String toString()
    {
        return year+""+((month < 10) ? ("0" + month) : month)+""+day;

    }
}
