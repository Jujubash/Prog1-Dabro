public class Aufgabe7Kultur {
    public static void main(String[] args) {
        char[] stati = {'s', 's', 's', 'u', 'u', 'u', 's', 's', 'u', 'u', 'u', 'u', 's', 's', 'u', 's', 's', 's',
                's', 'u', 'u', 's', 's', 's', 's', 'u', 's', 's', 'u', 'u', 's', 'u', 'u', 's', 's', 'u', 'u', 's', 's', 'u'};
        double[] prices = {600, 6000, 1500, 1000, 1000, 1500, 2500, 1800, 1500, 400, 4000, 2000, 5000, 1000,
                2000, 6000, 800, 1000, 1500, 1000, 1500, 500, 1500, 1500, 1500, 8000, 3000, 8000, 2500, 5000, 1500, 5000, 10000,
                2000, 1500, 3500, 1500, 2500, 2500, 2500};

        ArtObject[] artObjects = createArtObjects(stati, prices);
        double[] metrics = priceMetrics(artObjects);
        ArtObject[] filteredObjects = filterByStatus(artObjects, 's');
        String[] showMetricsResult = showMetrics(artObjects);
    }

    public static double[] priceMetrics(ArtObject[] objects) {
        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        double total = 0;

        for (ArtObject object : objects) {
            double price = object.price;
            minPrice = Math.min(minPrice, price);
            maxPrice = Math.max(maxPrice, price);
            total += price;
        }

        double meanPrice = total / objects.length;

        return new double[]{minPrice, maxPrice, meanPrice};
    }

    public static ArtObject[] filterByStatus(ArtObject[] objects, char status) {
        int count = 0;

        for (ArtObject object : objects) {
            if (object.status == status) {
                count++;
            }
        }

        ArtObject[] filteredObjects = new ArtObject[count];
        int index = 0;

        for (ArtObject object : objects) {
            if (object.status == status) {
                filteredObjects[index++] = object;
            }
        }

        return filteredObjects;
    }

    public static ArtObject[] createArtObjects(char[] stati, double[] prices) {
        if (stati.length != prices.length) {
            throw new IllegalArgumentException("Input arrays must have the same length");
        }

        ArtObject[] artObjects = new ArtObject[stati.length];

        for (int i = 0; i < artObjects.length; i++) {
            artObjects[i] = new ArtObject(stati[i], prices[i]);
        }

        return artObjects;
    }

    public static String[] showMetrics(ArtObject[] objects) {
        ArtObject[] soldObjects = filterByStatus(objects, 's');
        ArtObject[] unsoldObjects = filterByStatus(objects, 'u');

        double[] soldMetrics = priceMetrics(soldObjects);
        double[] unsoldMetrics = priceMetrics(unsoldObjects);

        String[] result = new String[2];

        result[0] = String.format("Verkauft - Min: " + soldMetrics[0] + ", Max:" + soldMetrics[1] + ", Mean: " + soldMetrics[2]);
        result[1] = String.format("Nicht verkauft - Min: " + unsoldMetrics[0] + ", Max:" + unsoldMetrics[1] + ", Mean: " + unsoldMetrics[2]);

        return result;
    }
}
