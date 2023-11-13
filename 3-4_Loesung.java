/**
 *  Programmierung 1 - Pflichtabgabe
 *  Übung 3.4: Gleichschenkliges Dreieck
 *  Studentin: Júlia da Silva Soares Vetter
 */

public class Loesung {
    public static void main(String[] args) {
    int zeilen = 5; 
        for (int i = 1; i <= zeilen; i++) { // Erste Hälfte des Dreiecks: h1
            for (int h1 = 1; h1 <= i; h1++) {
                System.out.print("*");
            }
            System.out.println(); // Zur neue Zeile gehen
        }

        for (int i = zeilen - 1; i >= 1; i--) { // Zweite Hälfte des Dreiecks: h2
            for (int h2 = 1; h2 <= i; h2++) {
                System.out.print("*");
            }
            System.out.println(); // Zur neue Zeile gehen
        }
    }
}
