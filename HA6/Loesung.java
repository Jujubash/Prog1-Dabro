public class Loesung {
    // Aufgabe 6.1: Vergleich von Werten
    public static int compare(int x, int y) {
        if (x < y) {
            return -1;
        } else if (x == y) {
            return 0;
        } else {
            return 1;
        }
    }

    /*
     * Aufgabe 6.2: Gefährliche Operationen
     ** 'a': Addition
     ** 's': Substraktion
     */
    public static boolean isSafe(int a, int b, char operation) {
        if (operation != 'a' && operation != 's') {
            return false;
        }

        long result;

        if (operation == 'a') {
            result = (long) a + b;
        } else {
            result = (long) a - b;
        }

        return result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE;
    }

    // Aufgabe 6.3: Primzahlen erkennen (ohne Libraries)
    public static boolean isPrime(int val) {
        if (val <= 1) {
            return false;
        }

        if (val == 2) {
            return true;
        }

        if (val % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= val / 2; i += 2) {
            if (val % i == 0) {
                return false;
            }
        }

        return true;
    }

    /*
     * Aufgabe 4: Primzahlen zählen
     ** isPrime() Methode anwenden
     */
    public static int countPrimes(int from, int to) {
        int count = 0;

        for (int i = from; i <= to; i++) {
            if (isPrime(i)) {
                count++;
            }
        }

        return count;
    }

    // Aufgabe 6.x: Testen
    public static void main(String[] args) {
        // Aufgabe 6.1: Vergleich von Werten
        System.out.println(compare(5, 10));

        // Aufgabe 6.2: Gefährliche Operationen
        System.out.println(isSafe(100000, 200000, 'a'));

        // Aufgabe 6.3: Primzahlen erkennen (ohne Libraries)
        System.out.println(isPrime(17));

        // Aufgabe 4: Primzahlen zählen
        System.out.println(countPrimes(10, 50));
    }
}
