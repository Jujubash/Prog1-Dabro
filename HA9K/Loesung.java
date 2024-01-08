public class Loesung { // Aufgabe 9 - Kultur
    public static int[] monthlySales(ArtAuctionList pl, int fromYear, int toYear, int fromMonth, int toMonth) {
        int[] monthlySales = new int[(toYear - fromYear + 1) * 12];
        int index = 0;

        for (int year = fromYear; year <= toYear; year++) {
            int startMonth = (year == fromYear) ? fromMonth : 1;
            int endMonth = (year == toYear) ? toMonth : 12;

            ArtAuctionList filteredList = pl.filterByYear(year).filterByMonthRange(startMonth, endMonth);
            double totalSales = filteredList.getTotalSales();
            monthlySales[index++] = (int) totalSales;
        }

        return monthlySales;
    }

    public static void main(String[] args) {
        ArtAuctionList auctionList = new ArtAuctionList();
        auctionList.addFromCSVFile("bidtoart.csv");

        int[] monthlySales = monthlySales(auctionList, 2020, 2021, 1, 12);

        for (int i = 0; i < monthlySales.length; i++) {
            System.out.println("Month " + (i + 1) + ": " + monthlySales[i]);
        }
    }
}