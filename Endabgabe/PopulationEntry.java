public class PopulationEntry {
    private String country;
    private int fromYear;
    private int toYear;
    private double deathRate;

    public PopulationEntry(String country, int fromYear, int toYear, double deathRate) {
        this.country = country;
        this.fromYear = fromYear;
        this.toYear = toYear;
        this.deathRate = deathRate;
    }

    public boolean isValidForYear(int year) {
        return year >= fromYear && year < toYear;
    }

    public String getCountry() {
        return country;
    }

    public int getFromYear() {
        return fromYear;
    }

    public int getToYear() {
        return toYear;
    }

    public double getDeathRate() {
        return deathRate;
    }
}
