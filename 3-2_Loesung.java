/**
 *  Programmierung 1 - Pflichtabgabe
 *  Übung 3.2: Abwechselndes Viereck
 *  Studentin: Júlia da Silva Soares Vetter
 */

public class Loesung {
    public static void main(String[] args) {
// Hier Lösung der Aufgabe eintragen:
        int zeile = 4;
        int spalte = 7;

        // For-Schleife anfangen
        for (int z = 0; z < zeile; z++) {
            for (int s = 0; s < spalte; s++) {
                if (z % 2 == 0) {
                    // Zwei ungeraden Zeilen 7-mal mit * printen:
                    System.out.print("*");
                } else {
                    // Zwei geraden Zeilen 7-mal mit . printen:
                    System.out.print(".");
                }
            }
            // Wenn eine Zeile fertig ist, dann zur nächste Zeile gehen:
            System.out.println();
        }
        // Schleife fertig!
        // Zwei ungeraden plus zwei geraden Zeilen ergeben vier Zeilen
    }
}
