import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> carret = new HashMap<>();
        Map<String, Double> preus = new HashMap<>();
        Map<String, String> noms = new HashMap<>();
        int op = -1;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                System.out.println("\nBENVINGUT AL SUPERMERCAT\n------------");
                System.out.println("1) Introduir producte");
                System.out.println("2) Passar per caixa");
                System.out.println("3) Mostrar carret de compra");
                System.out.println("0) Acabar");

                if (sc.hasNextInt()) {
                    op = sc.nextInt();
                    switch (op) {
                        case 1:
                            Producte.menuProducte(carret, preus, noms);
                            break;
                        case 2:
                            pasarCaixa(carret, preus, noms);
                            break;
                        case 3:
                            mostrarCarret(carret, noms);
                            break;
                    }
                } else {
                    sc.next();
                }
            } catch (Exception e) {
                System.out.println("Error en el menú: " + e.getMessage());
                sc.nextLine();
            }
        } while (op != 0);
    }

    public static void mostrarCarret(Map<String, Integer> carret, Map<String, String> noms) {
        System.out.println("\n--- Carret ---");
        if (carret.isEmpty()) {
            System.out.println("El carret està buit");
        } else {
            for (String clau : carret.keySet()) {
                String nom = noms.get(clau);
                System.out.println("Producte: " + nom + " | Unitats: " + carret.get(clau));
            }
        }
    }

    public static void pasarCaixa(Map<String, Integer> carret, Map<String, Double> preus, Map<String, String> noms) {
        try {
            LocalDate dataCompra = LocalDate.now();
            double totalTicket = 0;

            System.out.println("\n-------------------------------------------");
            System.out.println("SAPAMERCAT");
            System.out.println("-------------------------------------------");
            System.out.println("Data: " + dataCompra);
            System.out.println("-------------------------------------------");

            if (carret.isEmpty()) {
                System.out.println("El carret està buit.");
            } else {
                for (String clau : carret.keySet()) {
                    int unitats = carret.get(clau);
                    double preuUnit = preus.get(clau);
                    String nomProd = noms.get(clau);
                    double totalProd = unitats * preuUnit;

                    System.out.printf("%-15s %5d %10.2f %10.2f\n", nomProd, unitats, preuUnit, totalProd);
                    totalTicket += totalProd;
                }
                System.out.println("-------------------------------------------");
                System.out.printf("Total: %.3f\n", totalTicket);
            }

            carret.clear();
            preus.clear();
            noms.clear();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}