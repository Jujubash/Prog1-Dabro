public class Aufgabe7Gesundheit {
    public static void main(String[] args) {
      double[] ages = {20, 45, 69, 70, 45, 16, 59, 45, 24, 54, 53, 65, 61, 3, 48, 47, 13, 85, 27, 69, 45, 20, 67, 63, 18, 56, 25, 35, 35, 24, 70, 65, 34, 32, 23, 67, 53, 35, 33, 38, 22, 27, 69, 64, 36, 27, 51, 48, 46, 85, 38, 41, 24, 22, 73, 69, 23, 41, 32, 40, 65, 56, 47, 65, 70, 23, 65, 37, 63, 59, 26, 63, 69, 23, 24, 33, 35, 70, 36, 53, 35, 1, 60, 22, 18, 21, 21, 23, 70, 32, 38, 18, 74, 21, 20, 44, 38, 21, 42, 68, 29, 58, 1, 51, 45, 32, 34, 24, 48, 35, 26, 73, 75, 67, 67, 65, 44, 62, 75, 60, 1, 80, 72, 71, 65, 66, 55, 22, 50, 80, 50, 45};
		  char[] stati = {'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd'};
    }

    public static double[] ageMetrics(Patient[] patients) {
        double[] metrics = new double[3];

        double minAge = Double.MAX_VALUE;
        double maxAge = Double.MIN_VALUE;
        double sumAge = 0;

        for (Patient patient : patients) {
            if (patient.age < minAge) {
                minAge = patient.age;
            }

            if (patient.age > maxAge) {
                maxAge = patient.age;
            }

            sumAge += patient.age;
        }

        metrics[0] = minAge;
        metrics[1] = maxAge;
        metrics[2] = sumAge / patients.length;

        return metrics;
    }

    public static Patient[] filterByStatus(Patient[] patients, char status) {
        int count = 0;
        for (Patient patient : patients) {
            if (patient.status == status) {
                count++;
            }
        }

        Patient[] filteredPatients = new Patient[count];
        int index = 0;
        for (Patient patient : patients) {
            if (patient.status == status) {
                filteredPatients[index] = patient;
                index++;
            }
        }

        return filteredPatients;
    }

    public static Patient[] createPatients(char[] stati, double[] ages) {
        if (stati.length != ages.length) {
            throw new IllegalArgumentException("Arrays müssen die gleiche Länge haben");
        }

        Patient[] patients = new Patient[stati.length];
        for (int i = 0; i < stati.length; i++) {
            patients[i] = new Patient(stati[i], ages[i]);
        }

        return patients;
    }

    public static String[] showMetrics(Patient[] patients) {
        double minAgeDeceased = Double.MAX_VALUE;
        double maxAgeDeceased = Double.MIN_VALUE;
        double sumAgeDeceased = 0;
        int countDeceased = 0;

        double minAgeRecovered = Double.MAX_VALUE;
        double maxAgeRecovered = Double.MIN_VALUE;
        double sumAgeRecovered = 0;
        int countRecovered = 0;

        for (Patient patient : patients) {
            if (patient.status == 'D') {
                if (patient.age < minAgeDeceased) {
                    minAgeDeceased = patient.age;
                }

                if (patient.age > maxAgeDeceased) {
                    maxAgeDeceased = patient.age;
                }

                sumAgeDeceased += patient.age;
                countDeceased++;
            } else if (patient.status == 'G') {
                if (patient.age < minAgeRecovered) {
                    minAgeRecovered = patient.age;
                }

                if (patient.age > maxAgeRecovered) {
                    maxAgeRecovered = patient.age;
                }

                sumAgeRecovered += patient.age;
                countRecovered++;
            }
        }
        // TODO: Result anpassen (fehlt 1 Punkt)
        String[] result = new String[2];
        
        double meanVerstorben = (double) sumAgeDeceased / countDeceased;
        double meanGenesen = (double) sumAgeRecovered / countRecovered;

        result[0] = "Verstorben - Min: " + minAgeDeceased + ", Max: " + maxAgeDeceased +
                    ", Mean: " + meanVerstorben;
        
        result[1] = "Genesen - Min: " + minAgeRecovered + ", Max: " + maxAgeRecovered +
                    ", Mean: " + meanGenesen;

        return result;
    }
}
