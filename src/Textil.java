import java.util.Scanner;
import java.util.Map;

public class Textil extends Producte {
    private String composicio;

    // Passem les dades comunes a la mare amb "super"
    public Textil(String nom, double preu, String codiBarres, String composicio) {
        super(nom, preu, codiBarres);
        this.composicio = composicio;
    }

    public String getComposicio() {
        return composicio;
    }
    public void setComposicio(String composicio) {
        this.composicio = composicio;
    }

    // Funció per demanar les dades de la roba i validar-les
    public static void dadesTextil(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Afegir tèxtil");

            // Validem que el nom no tingui números
            System.out.println("Nom producte: ");
            String nom = sc.next();
            while (!nom.matches("[a-zA-Z-ZáéíóúàèòÁÉÍÓÚÀÈÒñÑçÇ]+")) {
                nom = sc.next();
            }

            // El preu ha de ser positiu
            System.out.println("Preu: ");
            int preu = sc.nextInt();
            while (preu <= 0) {
                preu = sc.nextInt();
            }

            // Validem que la composició només tingui lletres
            System.out.println("Composició: ");
            String comp = sc.next();
            while (!comp.matches("[a-zA-Z-ZáéíóúàèòÁÉÍÓÚÀÈÒñÑçÇ]+")) {
                comp = sc.next();
            }

            // El codi de barres només pot tenir números
            System.out.println("Codi de barres: ");
            String codiStr = sc.next();
            while (!codiStr.matches("[0-9]+")) {
                codiStr = sc.next();
            }

            // Cridem a la funció de la mare per guardar el producte al "recipient" (HashMap)
            afegirAlCarret(nom, preu, codiStr, carret, preus, noms);
            System.out.println("Producte afegit correctament");

        } catch (Exception e) {
            // Missatge si hi ha un error inesperat
            System.err.println("Error! " + e.getMessage());
        }
    }
}