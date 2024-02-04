import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntryManager {
    private String country;
    private List<PopulationEntry> populationEntries;
    private List<HIVEntry> hivEntriesDeaths;
    private List<HIVEntry> hivEntriesPrevalence;

    public EntryManager(String populationFilename, String HIVfilename, String country) {
        this.country = country;
        this.populationEntries = readPopulationData(populationFilename);
        this.hivEntriesDeaths = readHIVData(HIVfilename, "Deaths");
        this.hivEntriesPrevalence = readHIVData(HIVfilename, "Prevalence");
    }
    
    /*
    public PopulationEntry getPopulationEntryForYear(int year) {
        try {
            for (PopulationEntry entry : populationEntries) {
                if (entry.isValidForYear(year)) {
                    return entry;
                }
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.err.println("getPopulationEntryForYear() Methode - Fehler: " + year + ": " + e.getMessage());
            return null;
        }
        return null;
    } */

    private List<PopulationEntry> readPopulationData(String filename) {
        List<PopulationEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String location = parts[0].trim();
                int year = Integer.parseInt(parts[1].trim());
                double deathRate = Double.parseDouble(parts[2].trim());

                if (location.equals(country)) {
                    entries.add(new PopulationEntry(country, year, year + 1, deathRate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }
    
    public PopulationEntry getPopulationEntryForYear(int year) {
        for (PopulationEntry entry : populationEntries) {
            if (entry.isValidForYear(year)) {
                return entry;
            }
        }
        return null;
    }

    private List<HIVEntry> readHIVData(String filename, String measure) {
        List<HIVEntry> entries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String currentMeasure = parts[0].trim();
                if (!currentMeasure.equals(measure)) {
                    continue;
                }

                String location = parts[1].trim();
                int year = Integer.parseInt(parts[3].trim());
                double value = Double.parseDouble(parts[4].trim());

                if (location.equals(country)) {
                    entries.add(new HIVEntry(measure, year, value));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    public HIVEntry getHIVDeathrateForYear(int year) {
        for (HIVEntry entry : hivEntriesDeaths) {
            if (entry.getYear() == year) {
                return entry;
            }
        }
        return null;
    }

    public HIVEntry getHIVPrevalenceForYear(int year) {
        for (HIVEntry entry : hivEntriesPrevalence) {
            if (entry.getYear() == year) {
                return entry;
            }
        }
        return null;
    }

    public String getCountry() {
        return country;
    }
}