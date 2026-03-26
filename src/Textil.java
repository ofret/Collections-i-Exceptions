import java.util.Scanner;
import java.util.Map;

public class Textil extends Producte {
    private String composicio;

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

    public static void dadesTextil(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Afegir tèxtil");
            System.out.println("Nom producte: ");
            String nom = sc.next();
            while (!nom.matches("[a-zA-Z-ZáéíóúàèòÁÉÍÓÚÀÈÒñÑçÇ]+")) {
                nom = sc.next();
            }
            System.out.println("Preu: ");
            int preu = sc.nextInt();
            while (preu <= 0) {
                preu = sc.nextInt();
            }
            System.out.println("Composició: ");
            String comp = sc.next();
            while (!comp.matches("[a-zA-Z-ZáéíóúàèòÁÉÍÓÚÀÈÒñÑçÇ]+")) {
                comp = sc.next();
            }
            System.out.println("Codi de barres: ");
            String codiStr = sc.next();
            while (!codiStr.matches("[0-9]+")) {
                codiStr = sc.next();
            }
            afegirAlCarret(nom, preu, codiStr, carret, preus, noms);
            System.out.println("Producte afegit correctament");
        } catch (Exception e) {
            System.err.println("Error! " + e.getMessage());
        }
    }
}