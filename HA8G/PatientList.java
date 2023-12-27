import java.util.ArrayList;

public class PatientList { // Aufgabe 8 - Gesundheit
    private ArrayList<Patient> patients;

    public PatientList() {
        patients = new ArrayList<>();
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public int getNumPatients() {
        return patients.size();
    }

    public AgeMetrics getAgeMetrics() {
        AgeMetrics metrics = new AgeMetrics();
        for (Patient p : patients) {
            metrics.addAge(p.getAge());
        }
        return metrics;
    }

    public PatientList filterByStatus(char status) {
        PatientList filteredList = new PatientList();
        for (Patient p : patients) {
            if (p.getStatus() == status) {
                filteredList.addPatient(p);
            }
        }
        return filteredList;
    }
}
