import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    private List<PopulationEntry> readPopulationData(String filename) {
        List<PopulationEntry> entries = new ArrayList<>();
        boolean success = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String ersteLinie = reader.readLine();
            String[] headerParts = ersteLinie.split(",");

            int locationIndex = getIndex("Location", headerParts);
            int timeIndex = getIndex("Time", headerParts);
            int deathRateIndex = getIndex("CDR", headerParts);

            if (locationIndex != -1 && timeIndex != -1 && deathRateIndex != -1) {
                success = true;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    try {
                        String[] yearArr = parts[timeIndex].trim().split("-");
                        int fromYear = Integer.parseInt(yearArr[0]);
                        int toYear = Integer.parseInt(yearArr[1]);
                        String currentLocation = parts[locationIndex].trim();
                        double currentDeathRate = Double.parseDouble(parts[deathRateIndex].trim());

                        if (currentLocation.equals(getCountry())) {
                            entries.add(new PopulationEntry(currentLocation, fromYear, toYear, currentDeathRate));
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Ungültige Daten in der Zeile: " + line);
                    }

                }
            } else {
                System.out.println("Erforderliche Spalten nicht gefunden.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!success) {
            return Collections.emptyList();
        }

        return entries;
    }


    private int getIndex(String columnName, String[] headers) {
        for (int i = 0; i < headers.length; i++) {
            if (headers[i].equalsIgnoreCase(columnName.trim())) {
                return i;
            }
        }
        return -1;
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
        boolean success = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String ersteLinie = reader.readLine();
            String[] headerParts = ersteLinie.split(",");

            int measureIndex = getIndex("measure_name", headerParts);
            int yearIndex = getIndex("year", headerParts);
            int valIndex = getIndex("val", headerParts);
            int countryIndex = getIndex("location_name", headerParts);
            if (measureIndex != -1 && yearIndex != -1 && valIndex != -1){
                success = true;
                while ((line = reader.readLine()) != null) {
                    try {
                        String[] parts = line.split(",");
                        int year = Integer.parseInt(parts[yearIndex].trim());
                        String currentMeasure = parts[measureIndex].trim();
                        double value = Double.parseDouble(parts[valIndex].trim());
                        if (parts[countryIndex].equals(getCountry()) && currentMeasure.equals(measure)) {
                            entries.add(new HIVEntry(currentMeasure, year, value));
                        }
                    }catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Ungültige Daten in der Zeile: " + line);
                    }
                }
            }else {
                System.out.println("Erforderliche Spalten nicht gefunden.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!success){
            return Collections.emptyList();
        }
        return entries;
    }

    public HIVEntry getHIVDeathrateForYear(int year) {
        for (HIVEntry entry : getHivEntriesDeaths()) {
            if (entry.getYear() == year && entry.getMeasure().equals("Deaths")) {
                return entry;
            }
        }
        return null;
    }

    public HIVEntry getHIVPrevalenceForYear(int year) {
        for (HIVEntry entry : getHivEntriesPrevalence()) {
            if (entry.getYear() == year && entry.getMeasure().equals("Prevalence")) {
                return entry;
            }
        }
        return null;
    }

    public String getCountry() {
        return country;
    }
    
    public List<HIVEntry> getHivEntriesDeaths() {
        return hivEntriesDeaths;
    }

    public List<HIVEntry> getHivEntriesPrevalence() {
        return hivEntriesPrevalence;
    }
}