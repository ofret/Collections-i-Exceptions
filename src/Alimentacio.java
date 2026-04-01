import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Alimentacio extends Producte {
    private LocalDate dataCaducitat;

    // Passem les dades comunes a la mare amb "super"
    public Alimentacio(String nom, double preu, String codiBarres, LocalDate dataCaducitat) {
        super(nom, preu, codiBarres);
        this.dataCaducitat = dataCaducitat;
    }

    public LocalDate getDataCaducitat() {
        return dataCaducitat;
    }
    public void setDataCaducitat(LocalDate dataCaducitat) {
        this.dataCaducitat = dataCaducitat;
    }

    // Funció per demanar dades i validar que tot estigui bé
    public static void dadesAliment(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Afegir aliment");

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

            // El codi de barres només pot tenir números
            System.out.println("Codi de barres: ");
            String codiStr = sc.next();
            while (!codiStr.matches("[0-9]+")) {
                codiStr = sc.next();
            }

            // Validem que la data tingui el format correcte (dd/mm/aaaa)
            System.out.println("Data de caducitat (dd/mm/aaaa): ");
            String dataStr = sc.next();
            while (!dataStr.matches("[0-9]{2}/[0-9]{2}/[0-9]{4}")) {
                dataStr = sc.next();
            }

            // Convertim el text de la data a un objecte de tipus data
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate data = LocalDate.parse(dataStr, formato);

            // Guardem el producte al recipient (HashMap) cridant a la funció de la mare
            afegirAlCarret(nom, preu, codiStr, carret, preus, noms);
            System.out.println("Producte afegit correctament");

        } catch (Exception e) {
            // Missatge si hi ha un error inesperat
            System.err.println("Error! " + e.getMessage());
        }
    }
}