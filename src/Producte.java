import java.util.Map;
import java.util.Scanner;

public class Producte {
    protected String nom;
    protected double preu;
    protected String codiBarres;

    public Producte(String nom, double preu, String codiBarres) {
        this.nom = nom;
        this.preu = preu;
        this.codiBarres = codiBarres;
    }

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
                        Alimentacio.dadesAliment(carret, preus, noms);
                        break;
                    case 2:
                        Textil.dadesTextil(carret, preus, noms);
                        break;
                    case 3:
                        Electronica.dadesElectronica(carret, preus, noms);
                        break;
                    case 0:
                        break;
                }
            } else {
                sc.next();
            }
        } while (op != 0);
    }

    protected static void afegirAlCarret(String nom, double preu, String codiStr, Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        String clau = codiStr + "_" + preu;
        if (carret.containsKey(clau)) {
            carret.put(clau, carret.get(clau) + 1);
        } else {
            carret.put(clau, 1);
        }
        preus.put(clau, preu);
        noms.put(clau, nom);
    }
}