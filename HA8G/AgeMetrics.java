public class AgeMetrics { // Aufgabe 8 - Gesundheit
    private double minAge;
    private double maxAge;
    private double sumAge;
    private int count;

    public AgeMetrics() {
        minAge = Double.MAX_VALUE;
        maxAge = Double.MIN_VALUE;
        sumAge = 0;
        count = 0;
    }

    public void addAge(double age) {
        minAge = Math.min(minAge, age);
        maxAge = Math.max(maxAge, age);
        sumAge += age;
        count++;
    }

    public double getMinAge() {
        return minAge;
    }

    public double getMaxAge() {
        return maxAge;
    }

    public double getAverageAge() {
        return count == 0 ? 0 : sumAge / count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public String toString() {
        return "Min: " + minAge + ", Max: " + maxAge + ", Mean: " + getAverageAge();
    }
}
