import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientList { // Aufgabe 10 - Gesundheit
    private List<Patient> patients;

    public PatientList() {
        this.patients = new ArrayList<>();
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void addFromCSVFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Patient patient = Patient.parseCSVLine(line);
                addPatient(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumPatients() {
        return patients.size();
    }

    public PatientList filterByFromAge(int minFromAge, int maxFromAge) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getFromAge() >= minFromAge && patient.getFromAge() <= maxFromAge) {
                result.addPatient(patient);
            }
        }
        return result;
    }

    public PatientList filterByToAge(int minToAge, int maxToAge) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getToAge() >= minToAge && patient.getToAge() <= maxToAge) {
                result.addPatient(patient);
            }
        }
        return result;
    }

    public PatientList filterBySex(String sex) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getSex().equals(sex)) {
                result.addPatient(patient);
            }
        }
        return result;
    }

    public PatientList filterByYear(int year) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getYear() == year) {
                result.addPatient(patient);
            }
        }
        return result;
    }

    public PatientList filterByMonth(int month) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getMonth() == month) {
                result.addPatient(patient);
            }
        }
        return result;
    }

    public PatientList filterByDayOfYear(int day) {
        PatientList result = new PatientList();
        for (Patient patient : patients) {
            if (patient.getDayOfYear() == day) {
                result.addPatient(patient);
            }
        }
        return result;
    }
}
