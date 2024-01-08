public class ArtAuction { // Aufgabe 9 - Kultur
    private int lots;
    private double sale;
    private int day;
    private int month;
    private int year;

    private ArtAuction() {}

    public static ArtAuction parseCSVLine(String line) {
        String[] values = line.split(",");
        ArtAuction auction = new ArtAuction();

        auction.day = Integer.parseInt(values[0].split("-")[0]);
        auction.month = Integer.parseInt(values[0].split("-")[1]);
        auction.year = Integer.parseInt(values[0].split("-")[2]);

        auction.lots = parseValue(values[1]);
        auction.sale = parseSaleValue(values[2]);

        return auction;
    }

    private static int parseValue(String value) {
        return value.equals("NA") ? -1 : Integer.parseInt(value);
    }

    private static double parseSaleValue(String value) {
        return value.equals("NA") ? 0 : Double.parseDouble(value);
    }

    public int getLots() {
        return lots;
    }

    public double getSale() {
        return sale;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
