import java.util.Scanner;
import java.util.Map;

public class Electronica extends Producte {
    private int garantia;

    // Passem les dades comunes a la mare amb "super"
    public Electronica(String nom, double preu, String codiBarres, int garantia) {
        super(nom, preu, codiBarres);
        this.garantia = garantia;
    }

    public int getGarantia() {
        return garantia;
    }
    public void setGarantia(int garantia) {
        this.garantia = garantia;
    }

    // Funció per demanar les dades i validar-les
    public static void dadesElectronica(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Afegir electrònica");

            // Validem que el nom no tingui números
            System.out.println("Nom producte: ");
            String nom = sc.next();
            while (!nom.matches("[a-zA-Z-ZáéíóúàèòÁÉÍÓÚÀÈÒñÑçÇ]+")) {
                nom = sc.next();
            }

            // El preu ha de ser més gran que zero
            System.out.println("Preu: ");
            int preu = sc.nextInt();
            while (preu <= 0) {
                preu = sc.nextInt();
            }

            // La garantia no pot ser negativa
            System.out.println("Garantia (dies): ");
            int garantia = sc.nextInt();
            while (garantia < 0) {
                garantia = sc.nextInt();
            }

            // El codi de barres només pot tenir números
            System.out.println("Codi de barres: ");
            String codiStr = sc.next();
            while (!codiStr.matches("[0-9]+")) {
                codiStr = sc.next();
            }

            // Cridem a la funció de la "mare" per guardar-lo al recipient (HashMap)
            afegirAlCarret(nom, preu, codiStr, carret, preus, noms);
            System.out.println("Producte afegit correctament");

        } catch (Exception e) {
            // Missatge si hi ha un error inesperat
            System.err.println("Error! " + e.getMessage());
        }
    }
}