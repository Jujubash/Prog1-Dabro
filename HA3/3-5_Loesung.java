/**
 *  Programmierung 1 - Pflichtabgabe
 *  Übung 3.5: Schachbrett
 *  Studentin: Júlia da Silva Soares Vetter
 */

public class Loesung {
    public static void main(String[] args) {
// Hier Lösung der Aufgabe eintragen:
        int zeilen = 9;

        for (int i = 1; i <= zeilen; i++) { // Zeilen durchgehen
            // Abwechseln zwischen Sternchen und Punkt:
            for (int j = 1; j <= zeilen; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("*");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println(); // Neue Zeile anfangen
        }
        // Schleife zu Ende!
    }
}
// Eigentlich haben Schachbrette
// eine Breite von 8 Zeilen,
// sowie 8 Spalten ;) aber na ja!