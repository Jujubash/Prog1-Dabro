public class HIVEntry {
    private String measure;
    private int year;
    private double value;

    public HIVEntry(String measure, int year, double value) {
        this.measure = measure;
        this.year = year;
        this.value = value;
    }

    public String getMeasure() {
        return measure;
    }
    
    public int getYear() {
        return year;
    }

    public double getValue() {
        return value;
    }
}
