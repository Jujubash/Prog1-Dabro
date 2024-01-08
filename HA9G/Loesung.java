public class Loesung { // Aufgabe 9 - Gesundheit
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

    public static void main(String[] args) {
        PatientList patientList = new PatientList();
        patientList.addFromCSVFile("./Berlin.csv"); // Falls es im selben Verzeichnis auf der Moodle Evaluation ist (vllt auch ohne ./ testen)

        int[] monthlyInfections = monthlyInfections(patientList, 2019, 2020, 1, 12);

        for (int i = 0; i < monthlyInfections.length; i++) {
            System.out.println("Month " + (i % 12 + 1) + ", Year " + (2019 + i / 12) + ": " + monthlyInfections[i]);
        }
    }
}
