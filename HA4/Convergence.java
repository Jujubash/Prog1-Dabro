public class Convergence { // Aufgabe 4 - Begleitaufgaben
    
    public static void main(String[] args) {
        printHarmonicSeriesFormula(15);
    }

    public static double getHarmonicSeriesAt(int n) {
        if (n <= 0) {
            return 0.0;
        }

        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / i;
        }

        return sum;
    }

    public static void printHarmonicSeriesFormula(int n) {
        for (int i = 1; i <= n; i++) {
            System.out.print("(1/" + i + ") ");
            if (i % 10 == 0 || i == n) {
                System.out.println();
            } else {
                System.out.print("+ ");
            }
        }

        System.out.println("= " + getHarmonicSeriesAt(n));
    }
}
