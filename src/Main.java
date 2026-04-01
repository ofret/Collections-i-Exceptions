import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        //Aqui fem com 3 "recipients" que és el HashMap
        Map<String, Integer> carret = new HashMap<>();  //Aquest guarda quantes unitats has comprat de cada producte
        Map<String, Double> preus = new HashMap<>();    //Aquest guarda el preu unitari de cada producte per poder fer els càlculs
        Map<String, String> noms = new HashMap<>();     //Aquest guarda el nom del producte per poder imprimir el tiquet final
        int op = -1;
        Scanner sc = new Scanner(System.in);

        do {
            try {
                //Fem un bucle pel menu
                System.out.println("\nBENVINGUT AL SUPERMERCAT\n------------");
                System.out.println("1) Introduir producte");
                System.out.println("2) Passar per caixa");
                System.out.println("3) Mostrar carret de compra");
                System.out.println("0) Acabar");

                if (sc.hasNextInt()) {
                    op = sc.nextInt();
                    switch (op) {
                        case 1:
                            //Cridem el menu de la classe Producte
                            Producte.menuProducte(carret, preus, noms);
                            break;
                        case 2:
                            //Cridem aquesta funcio per pasar per caixa
                            pasarCaixa(carret, preus, noms);
                            break;
                        case 3:
                            //Cridem aquesta funcio per mostrar el carret
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
        } while (op != 0);  //Farà aquest bucle fins que s'introdueixi el número 0
    }

    // Funció per ensenyar què portem al carret
    public static void mostrarCarret(Map<String, Integer> carret, Map<String, String> noms) {
        System.out.println("\n--- Carret ---");
        if (carret.isEmpty()) { // Mirem si el "recipient" està buit
            System.out.println("El carret està buit");
        } else {
            // Recorrem el carret fent servir la clau (codi) per saber el nom i les unitats
            for (String clau : carret.keySet()) {
                String nom = noms.get(clau);
                System.out.println("Producte: " + nom + " | Unitats: " + carret.get(clau));
            }
        }
    }

    //Funció per veure el ticket complet i pagar
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
                // Calculem el preu de cada línia (unitats * preu unitari)
                for (String clau : carret.keySet()) {
                    int unitats = carret.get(clau);
                    double preuUnit = preus.get(clau);
                    String nomProd = noms.get(clau);
                    double totalProd = unitats * preuUnit;

                    // Imprimim la línia ben alineada i sumem al total
                    System.out.printf("%-15s %5d %10.2f %10.2f\n", nomProd, unitats, preuUnit, totalProd);
                    totalTicket += totalProd;
                }
                System.out.println("-------------------------------------------");
                System.out.printf("Total: %.3f\n", totalTicket);
            }

            //Buidem els "recipients"
            carret.clear();
            preus.clear();
            noms.clear();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}