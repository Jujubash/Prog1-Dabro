//import org.knowm.xchart.BitmapEncoder;
//import org.knowm.xchart.XYChart;
//import org.knowm.xchart.XYSeries;
//import org.knowm.xchart.style.lines.SeriesLines;
//import org.knowm.xchart.style.markers.SeriesMarkers;

public class Analysis {
    private EntryManager entryManager;

    public Analysis(String populationFilename, String HIVfilename, String country) {
        entryManager = new EntryManager(populationFilename, HIVfilename, country);
    }

    public double[] getCrudeDeathrates(int fromYear, int toYear) {
        int rangeSize = toYear - fromYear + 1; // TODO: ändern ??? 5
        double[] crudeDeathRates = new double[rangeSize];

        for (int i = 0; i < 5-1; i++) {
            int currentYear = fromYear + i;
            PopulationEntry populationEntry = entryManager.getPopulationEntryForYear(currentYear);

            if (populationEntry != null) {
                crudeDeathRates[i] = populationEntry.getDeathRate();
            }
        }
        return crudeDeathRates;
    }

    public double[] getHIVDeathrates(int fromYear, int toYear) {
        int rangeSize = toYear - fromYear + 1;
        double[] hivDeathRates = new double[rangeSize];

        for (int i = 0; i < rangeSize; i++) {
            int currentYear = fromYear + i;
            HIVEntry hivEntry = entryManager.getHIVDeathrateForYear(currentYear);

            if (hivEntry != null) {
                hivDeathRates[i] = hivEntry.getValue();
            }
        }
        return hivDeathRates;
    }

    public double[] getHIVPrevalences(int fromYear, int toYear) {
        int rangeSize = toYear - fromYear + 1;
        double[] hivPrevalences = new double[rangeSize];

        for (int i = 0; i < rangeSize; i++) {
            int currentYear = fromYear + i;
            HIVEntry hivEntry = entryManager.getHIVPrevalenceForYear(currentYear);

            if (hivEntry != null) {
                hivPrevalences[i] = hivEntry.getValue();
            }
        }
        return hivPrevalences;
    }

    /*
    public double[] getPercentageHIVDeathrates(int fromYear, int toYear) {
        int rangeSize = toYear - fromYear + 1;
        double[] percentageHIVDeathrates = new double[rangeSize];

        double[] crudeDeathRates = getCrudeDeathrates(fromYear, toYear);
        double[] hivDeathRates = getHIVDeathrates(fromYear, toYear);

        for (int i = 0; i < rangeSize; i++) {
            if (crudeDeathRates[i] != 0) {
                percentageHIVDeathrates[i] = (hivDeathRates[i] / crudeDeathRates[i]) * 100;
            }
        }
        return percentageHIVDeathrates;
    } */
    
    public double[] getPercentageHIVDeathrates(int fromYear, int toYear) {
        int rangeSize = toYear - fromYear + 1;
        double[] percentageHIVDeathrates = new double[rangeSize];

        double[] crudeDeathRates = getCrudeDeathrates(fromYear, toYear);
        double[] hivDeathRates = getHIVDeathrates(fromYear, toYear);

        for (int i = 0; i < rangeSize; i++) {
            if (crudeDeathRates[i] != 0) {
                try {
                    percentageHIVDeathrates[i] = (hivDeathRates[i] / crudeDeathRates[i]) * 100;
                } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NumberFormatException e) {

                    System.err.println("getPercentageHIVDeathrates() Methode - Fehler: " + (fromYear + i) + ": " + e.getMessage());
                    percentageHIVDeathrates[i] = 0;
                }
            }
        }
        return percentageHIVDeathrates;
    }

//    public void plotPercentageAnalysis(int fromYear, int toYear, boolean plotPrevalence, boolean plotCrudeDeathrate, String filename) {
//        int rangeSize = toYear - fromYear + 1;
//        double[] years = new double[rangeSize];
//
//        for (int i = 0; i < rangeSize; i++) {
//            years[i] = fromYear + i;
//        }
//
//        double[] percentageHIVDeathrates = getPercentageHIVDeathrates(fromYear, toYear);
//        double[] hivPrevalences = plotPrevalence ? getHIVPrevalences(fromYear, toYear) : null;
//        double[] crudeDeathRates = plotCrudeDeathrate ? getCrudeDeathrates(fromYear, toYear) : null;
//
//        XYChart chart = new XYChart(800, 600);
//        chart.setTitle("HIV Analysis");
//        chart.setXAxisTitle("Year");
//        chart.setYAxisTitle("Percentage");
//
//        XYSeries percentageSeries = chart.addSeries("Percentage of HIV Death Rates", years, percentageHIVDeathrates);
//        percentageSeries.setMarker(SeriesMarkers.CIRCLE);
//        percentageSeries.setLineStyle(SeriesLines.SOLID);
//
//        if (plotPrevalence) {
//            XYSeries prevalenceSeries = chart.addSeries("HIV Prevalence", years, hivPrevalences);
//            prevalenceSeries.setMarker(SeriesMarkers.DIAMOND);
//            prevalenceSeries.setLineStyle(SeriesLines.SOLID);
//        }
//
//        if (plotCrudeDeathrate) {
//            XYSeries crudeDeathRateSeries = chart.addSeries("Crude Death Rate", years, crudeDeathRates);
//            crudeDeathRateSeries.setMarker(SeriesMarkers.CIRCLE);
//            crudeDeathRateSeries.setLineStyle(SeriesLines.SOLID);
//        }
//
//        try {
//            BitmapEncoder.saveBitmap(chart, filename, BitmapEncoder.BitmapFormat.PNG);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}