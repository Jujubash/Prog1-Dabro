public class Patient { // Aufgabe 8 - Gesundheit
    private char status;
    private double age;

    public Patient(char status, double age) {
        this.status = status;
        this.age = age;
    }

    public double getAge() {
        return age;
    }

    public char getStatus() {
        return status;
    }
}
