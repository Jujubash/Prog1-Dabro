public class CaesarCipher { // Aufgabe 4 - Begleitaufgaben
    public static void main(String[] args) {
    }

    public static char encryptCharacter(char c, int offset) {
        if (isLetter(c)) {
            char base = isLowerCase(c) ? 'a' : 'A';
            return (char) ((c - base + offset) % 26 + base);
        }
        return c;
    }

    public static char decryptCharacter(char c, int offset) {
        if (isLetter(c)) {
            char base = isLowerCase(c) ? 'a' : 'A';
            int result = (c - base - offset) % 26;
            result = result < 0 ? result + 26 : result;
            return (char) (result + base);
        }
        return c;
    }

    public static boolean isLetter(char c) {
        return Character.isLetter(c);
    }

    public static boolean isLowerCase(char c) {
        return Character.isLowerCase(c);
    }

    public static boolean isUpperCase(char c) {
        return Character.isUpperCase(c);
    }
}
