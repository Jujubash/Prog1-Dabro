/**
 *  Programmierung 1 - Pflichtabgabe
 *  Übung 3.3: Dreieck mit Strich
 *  Studentin: Júlia da Silva Soares Vetter
 */

public class Loesung {
    public static void main(String[] args) {
        // Hier Lösung der Aufgabe eintragen:
        for (int zeile_symbole = 7; zeile_symbole >= 1; zeile_symbole--) {
            for (int sternchen = 1; sternchen <= zeile_symbole; sternchen++) {
                if (zeile_symbole != 5) {
                    System.out.print("*");
                }
                if (zeile_symbole == 5 && sternchen <= 5) {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
