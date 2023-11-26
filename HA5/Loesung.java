public class Loesung {
  public static void main(String[] args) {
    double[] ages = { 20, 45, 69, 70, 45, 16, 59, 45, 24, 54, 53, 65, 61, 3, 48, 47, 13, 85, 27, 69, 45, 20, 67, 63, 18,
        56, 25, 35, 35, 24, 70, 65, 34, 32, 23, 67, 53, 35, 33, 38, 22, 27, 69, 64, 36, 27, 51, 48, 46, 85, 38, 41, 24,
        22, 73, 69, 23, 41, 32, 40, 65, 56, 47, 65, 70, 23, 65, 37, 63, 59, 26, 63, 69, 23, 24, 33, 35, 70, 36, 53, 35,
        1, 60, 22, 18, 21, 21, 23, 70, 32, 38, 18, 74, 21, 20, 44, 38, 21, 42, 68, 29, 58, 1, 51, 45, 32, 34, 24, 48,
        35, 26, 73, 75, 67, 67, 65, 44, 62, 75, 60, 1, 80, 72, 71, 65, 66, 55, 22, 50, 80, 50, 45 };
    char[] stati = { 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd',
        'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'd', 'r', 'r',
        'd', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd',
        'r', 'r', 'd', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'd', 'r', 'r', 'r',
        'r', 'r', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'd', 'r', 'r', 'r', 'd', 'r', 'r', 'r', 'r', 'd', 'r', 'r', 'r',
        'd', 'r', 'r', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd', 'd',
        'd', 'd' };
    // Aufgabe 5.1: Kennzahlen
    // Teil 1 von 2: d für deceased => verstorbenen Patienten 
    double[] metricsDeceased = metrics(select(ages, filter(stati, 'd')));
    double minDeceased = metricsDeceased[0];
    double maxDeceased = metricsDeceased[1];
    double meanDeceased = metricsDeceased[2];

    // Aufgabe 5.1: Kennzahlen
    // Teil 2 von 2: r für recovered => genesenen Patienten 
    double[] metricsRecovered = metrics(select(ages, filter(stati, 'r')));
    double minRecovered = metricsRecovered[0];
    double maxRecovered = metricsRecovered[1];
    double meanRecovered = metricsRecovered[2];

    // Ausgabe der Ergebnisse
    System.out.println("Verstorben - Min: " + minDeceased + ", Max: " + maxDeceased + ", Mean: " + meanDeceased);
    System.out.println("Genesen - Min: " + minRecovered + ", Max: " + maxRecovered + ", Mean: " + meanRecovered);
  }

  // Aufgabe 5.1: Kennzahlen
  // Allgemeine
  public static double[] metrics(double[] values) {
    if (values == null || values.length == 0) {
      throw new IllegalArgumentException("Array darf nicht leer sein!");
    }

    double kleinsten = values[0];
    double groessten = values[0];
    double summe = values[0];

    for (int i = 1; i < values.length; i++) {
      if (values[i] < kleinsten) {
        kleinsten = values[i];
      }
      if (values[i] > groessten) {
        groessten = values[i];
      }
      summe += values[i];
    }

    double average = summe / values.length;

    return new double[] { kleinsten, groessten, average };
  }

  // Aufgabe 2: Filter
  public static int[] filter(char[] values, char toSelect) {
    int count = 0;
    for (char value : values) {
      if (value == toSelect) {
        count++;
      }
    }

    int[] indices = new int[count];
    int index = 0;

    for (int i = 0; i < values.length; i++) {
      if (values[i] == toSelect) {
        indices[index++] = i;
      }
    }

    return indices;
  }

  // Aufgabe 3: Select
  public static double[] select(double[] values, int[] indices) {
    double[] selectedValues = new double[indices.length];
    for (int i = 0; i < indices.length; i++) {
      selectedValues[i] = values[indices[i]];
    }
    return selectedValues;
  }
}