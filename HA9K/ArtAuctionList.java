import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtAuctionList { // Aufgabe 9 - Kultur
    private List<ArtAuction> auctions;

    public ArtAuctionList() {
        auctions = new ArrayList<>();
    }

    public void addAuction(ArtAuction a) {
        auctions.add(a);
    }

    public void addFromCSVFile(String filepath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                ArtAuction auction = ArtAuction.parseCSVLine(line);
                addAuction(auction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumAuctions() {
        return auctions.size();
    }

    public double getTotalSales() {
        double totalSales = 0;
        for (ArtAuction auction : auctions) {
            if (auction.getSale() != -1) {
                totalSales += auction.getSale();
            }
        }
        return totalSales;
    }

    public ArtAuctionList filterByLots(int minLots, int maxLots) {
        ArtAuctionList filteredList = new ArtAuctionList();
        for (ArtAuction auction : auctions) {
            int lots = auction.getLots();
            if (lots != -1 && lots >= minLots && lots <= maxLots) {
                filteredList.addAuction(auction);
            }
        }
        return filteredList;
    }

    public ArtAuctionList filterBySale(double minSale, double maxSale) {
        ArtAuctionList filteredList = new ArtAuctionList();
        for (ArtAuction auction : auctions) {
            double sale = auction.getSale();
            if (sale != -1 && sale >= minSale && sale <= maxSale) {
                filteredList.addAuction(auction);
            }
        }
        return filteredList;
    }

    public ArtAuctionList filterByYear(int year) {
        ArtAuctionList filteredList = new ArtAuctionList();
        for (ArtAuction auction : auctions) {
            if (auction.getYear() == year) {
                filteredList.addAuction(auction);
            }
        }
        return filteredList;
    }

    public ArtAuctionList filterByMonth(int month) {
        ArtAuctionList filteredList = new ArtAuctionList();
        for (ArtAuction auction : auctions) {
            if (auction.getMonth() == month) {
                filteredList.addAuction(auction);
            }
        }
        return filteredList;
    }

    public ArtAuctionList filterComplete() {
        ArtAuctionList filteredList = new ArtAuctionList();
        for (ArtAuction auction : auctions) {
            if (auction.getLots() != -1 && auction.getSale() != -1) {
                filteredList.addAuction(auction);
            }
        }
        return filteredList;
    }
}