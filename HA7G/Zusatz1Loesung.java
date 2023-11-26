import java.util.Arrays;

public class Zusatz1Loesung {

    // Aufgabe 7.1: Basen finden
    public static int countBases(char[] sequence, char toFind) {
        int count = 0;
        for (char base : sequence) {
            if (base == toFind) {
                count++;
            }
        }
        return count;
    }

    // Aufgabe 7.2: Purine/Pyrimidine zählen
    public static int countPurines(char[] sequence) {
        return countBases(sequence, 'A') + countBases(sequence, 'G');
    }

    public static int countPyrimidines(char[] sequence) {
        return countBases(sequence, 'T') + countBases(sequence, 'C');
    }

    // Aufgabe 7.3: Alle Basen zählen
    public static int[] countBases(char[] sequence) {
        int[] counts = new int[4]; // A, C, G, T
        for (char base : sequence) {
            switch (base) {
                case 'A':
                    counts[0]++;
                    break;
                case 'C':
                    counts[1]++;
                    break;
                case 'G':
                    counts[2]++;
                    break;
                case 'T':
                    counts[3]++;
                    break;
            }
        }
        return counts;
    }

    // Aufgabe 7.4: Komplementäre Sequenz berechnen
    public static char[] complementSequence(char[] sequence) {
        char[] complement = new char[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            switch (sequence[i]) {
                case 'A':
                    complement[i] = 'T';
                    break;
                case 'T':
                    complement[i] = 'A';
                    break;
                case 'G':
                    complement[i] = 'C';
                    break;
                case 'C':
                    complement[i] = 'G';
                    break;
            }
        }
        return complement;
    }

    // Aufgabe 7.5: Reverse Sequenz berechnen
    public static char[] reverseSequence(char[] sequence) {
        char[] reverse = new char[sequence.length];
        for (int i = 0; i < sequence.length; i++) {
            reverse[i] = sequence[sequence.length - 1 - i];
        }
        return reverse;
    }

    // Aufgabe 7.6: Revers-komplementäre Sequenz berechnen
    public static char[] reverseComplementSequence(char[] sequence) {
        char[] reverse = reverseSequence(sequence);
        return complementSequence(reverse);
    }

    // Aufgabe 7 Optional: Sequenz-Klasse
    public static class Sequence {
        private char[] sequence;

        /*
         * Legt die übergebene Sequenz in der Klasse als Attribut ab. Vorsicht: Kopieren
         * Sie den Inhalt des Arrays in ein neues Array, sonst verändert sich die
         * Sequenz der Klasse, wenn das Array irgendwo anders im Programm geändert wird
         * (wir erinnern uns an Heap vs. Stack)
         */
        public Sequence(char[] seq) {
            this.sequence = Arrays.copyOf(seq, seq.length); // Testen ob es reicht, wegen Heap vs. Stack
        }

        /*
         * Gibt die Länge der Sequenz zurück
         */
        public int getLength() {
            return sequence.length;
        }

        /*
         * Gibt die Anzahl der Pyrimidine in der Sequenz zurück
         */
        public int getNumPyrimidines() {
            return countPyrimidines(sequence);
        }

        /*
         * Gibt die Anzahl der Purine in der Sequenz zurück
         */
        public int getNumPurines() {
            return countPurines(sequence);
        }

        /*
         * Gibt ein neues Sequence-Objekt zurück, das die revers-komplementierte Version
         * der Sequenz enthält
         */
        public Sequence reverseComplement() {
            char[] revcomp = reverseComplementSequence(sequence);
            return new Sequence(revcomp);
        }
    }
}
