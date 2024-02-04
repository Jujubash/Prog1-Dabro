import org.knowm.xchart.*;
import org.knowm.xchart.style.*;
import org.knowm.xchart.style.markers.*;
import org.knowm.xchart.style.lines.*;
import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.style.PieStyler;
import java.io.IOException;

public class Loesung { // Aufgabe 10 - Gesundheit
    public static int[] monthlyInfections(PatientList pl, int fromYear, int toYear, int fromMonth, int toMonth) {
        int[] monthlyInfections = new int[(toYear - fromYear + 1) * 12];

        for (int currentYear = fromYear; currentYear <= toYear; currentYear++) {
            int startMonth = (currentYear == fromYear) ? fromMonth : 1;
            int endMonth = (currentYear == toYear) ? toMonth : 12;

            for (int currentMonth = startMonth; currentMonth <= endMonth; currentMonth++) {
                PatientList filteredList = pl.filterByYear(currentYear).filterByMonth(currentMonth);
                monthlyInfections[(currentYear - fromYear) * 12 + currentMonth - 1] = filteredList.getNumPatients();
            }
        }
        return monthlyInfections;
    }

    public static int[] dailyInfections(PatientList pl, int fromYear, int toYear, int fromDay, int toDay, boolean cumulative) {
        int[] dailyInfections = new int[(toYear - fromYear + 1) * DateUtilities.daysInYear(toYear)];
        int cumulativeInfections = 0;

        for (int currentYear = fromYear; currentYear <= toYear; currentYear++) {
            int startDay = (currentYear == fromYear) ? fromDay : 1;
            int endDay = (currentYear == toYear) ? toDay : DateUtilities.daysInYear(currentYear);

            for (int currentDay = startDay; currentDay <= endDay; currentDay++) {
                PatientList filteredList = pl.filterByYear(currentYear).filterByDayOfYear(currentDay);
                int infections = filteredList.getNumPatients();

                if (cumulative) {
                    cumulativeInfections += infections;
                    dailyInfections[(currentYear - fromYear) * DateUtilities.daysInYear(currentYear) + currentDay - 1] = cumulativeInfections;
                } else {
                    dailyInfections[(currentYear - fromYear) * DateUtilities.daysInYear(currentYear) + currentDay - 1] = infections;
                }
            }
        }
        return dailyInfections;
    }

    public static void plotInfections(int[][] infections, String out, String[] locations) {
        XYChart chart = new XYChartBuilder().width(800).height(600).title("Daily Infections").xAxisTitle("Day").yAxisTitle("Infections").build();
    
        for (int i = 0; i < infections.length; i++) {
            String seriesName = locations[i];
            int[] data = infections[i];
            chart.addSeries(seriesName, IntStream.range(0, data.length).toArray(), data);
        }
    
        try {
            BitmapEncoder.saveBitmap(chart, out, BitmapEncoder.BitmapFormat.PNG);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    

    public static void main(String[] args) {
        PatientList patientList = new PatientList();
        patientList.addFromCSVFile("Berlin.csv"); // ohne ./ lassen

        int[] monthlyInfections = monthlyInfections(patientList, 2019, 2020, 1, 12);

        for (int i = 0; i < monthlyInfections.length; i++) {
            System.out.println("Month " + (i % 12 + 1) + ", Year " + (2019 + i / 12) + ": " + monthlyInfections[i]);
        }

        int[][] dailyInfectionsData = new int[3][];
        dailyInfectionsData[0] = dailyInfections(patientList, 2020, 2020, 1, 155, false);
        dailyInfectionsData[1] = dailyInfections(patientList, 2020, 2020, 1, 155, false);
        dailyInfectionsData[2] = dailyInfections(patientList, 2020, 2020, 1, 155, false);

        int[][] cumulativeInfectionsData = new int[3][];
        cumulativeInfectionsData[0] = dailyInfections(patientList, 2020, 2020, 1, 155, true);
        cumulativeInfectionsData[1] = dailyInfections(patientList, 2020, 2020, 1, 155, true);
        cumulativeInfectionsData[2] = dailyInfections(patientList, 2020, 2020, 1, 155, true);

        String[] locations = {"Berlin", "Germany", "Italy"};

        plotInfections(dailyInfectionsData, "daily_infections.png", locations);
        plotInfections(cumulativeInfectionsData, "cumulative_infections.png", locations);
    }
}
