import java.util.Comparator;
import java.util.Map;
import java.util.Scanner;

// Afegim implements Comparable per poder ordenar els productes
public class Producte implements Comparable<Producte> {
    protected String nom;
    protected double preu;
    protected String codiBarres;

    // Constructor per crear la base de qualsevol producte
    public Producte(String nom, double preu, String codiBarres) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
    }

    // Getters i Setters
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPreu() {
        return preu;
    }
    public void setPreu(double preu) {
        this.preu = preu;
    }
    public String getCodiBarres() {
        return codiBarres;
    }
    public void setCodiBarres(String codiBarres) {
        this.codiBarres = codiBarres;
    }

    @Override
    public int compareTo(Producte altre) {
        return this.codiBarres.compareTo(altre.getCodiBarres());
    }

    // 2. MENÚ PER TRIAR CATEGORIA
    public static void menuProducte(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        int op = -1;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("---------------");
            System.out.println("-- PRODUCTE ---\n---------------");
            System.out.println("1) Alimentació");
            System.out.println("2) Tèxtil");
            System.out.println("3) Electrònica");
            System.out.println("0) Tornar");

            if (sc.hasNextInt()) {
                op = sc.nextInt();
                switch (op) {
                    case 1:
                        //Cridem a la classe filla Alimentacio
                        Alimentacio.dadesAliment(carret, preus, noms);
                        break;
                    case 2:
                        //Cridem a la classe filla Textil
                        Textil.dadesTextil(carret, preus, noms);
                        break;
                    case 3:
                        //Cridem a la classe filla Electronica
                        Electronica.dadesElectronica(carret, preus, noms);
                        break;
                }
            } else {
                sc.next();
            }
        } while (op != 0); //Torna al menú del Main si premem 0
    }

    // Fem una funcio per guardar les dades als "recipients"
    protected static void afegirAlCarret(String nom, double preu, String codiStr, Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        // Creem una clau única ajuntant codi + preu
        String clau = codiStr + "_" + preu;

        if (carret.containsKey(clau)) {
            // Si el producte ja hi és, sumem una unitat més
            carret.put(clau, carret.get(clau) + 1);
        } else {
            // Si és la primera vegada que l'afegim, posem 1 unitat
            carret.put(clau, 1);
        }

        // Guardem el preu i el nom als seus recipients corresponents
        preus.put(clau, preu);
        noms.put(clau, nom);
    }

    //Aquí implementem el "Comparator"
    class comparar implements Comparator<Producte> {
        @Override
        public int compare(Producte p1, Producte p2) {
            // Comparem el preu del producte 1 amb el del producte 2
            return Double.compare(p1.getPreu(), p2.getPreu());
        }
    }
}