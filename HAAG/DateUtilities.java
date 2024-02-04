public class DateUtilities { // Aufgabe 10 - Gesundheit
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int daysInYear(int year) {
        return isLeapYear(year) ? 366 : 365;
    }

    public static int daysInMonth(int month, int year) {
        int[] daysPerMonth = {0, 31, isLeapYear(year) ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return daysPerMonth[month];
    }
}
