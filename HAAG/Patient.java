import java.util.Arrays;

public class Patient { // Aufgabe 10 - Gesundheit
    private int fromAge;
    private int toAge;
    private String sex;
    private int day;
    private int month;
    private int year;

    private Patient() {
    }

    public static Patient parseCSVLine(String line) {
        String[] values = line.split(",");
        Patient patient = new Patient();

        if (values[0].equals("N/A")) {
            patient.fromAge = -1;
            patient.toAge = -1;
        } else if (values[0].contains("-")) {
            String[] ageRange = values[0].split("-");
            patient.fromAge = Integer.parseInt(ageRange[0]);
            patient.toAge = Integer.parseInt(ageRange[1]);
        } else {
            patient.fromAge = Integer.parseInt(values[0]);
            patient.toAge = Integer.parseInt(values[0]);
        }

        patient.sex = values[1];

        String[] dateValues = values[2].split("-");
        patient.day = Integer.parseInt(dateValues[0]);
        patient.month = Integer.parseInt(dateValues[1]);
        patient.year = Integer.parseInt(dateValues[2]);

        return patient;
    }

    public int getFromAge() {
        return fromAge;
    }

    public int getToAge() {
        return toAge;
    }

    public String getSex() {
        return sex;
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
    
    public int getDayOfYear() {
        int days = 0;
        for (int month = 1; month < this.month; month++) {
            days += DateUtilities.daysInMonth(month, this.year);
        }
        days += this.day;
        return days;
    }
}
