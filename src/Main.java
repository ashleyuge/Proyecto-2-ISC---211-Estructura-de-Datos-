public class Main {

    public static void main(String[] args) {

        try {
            Arbol<String> arbolito = new Arbol<String>();
            arbolito.CREA("Raiz", arbolito.CREA("A", arbolito.CREA("C"), arbolito.CREA("D")),
            arbolito.CREA("B"));

            var t = arbolito.ORD_PRE(arbolito.RAIZ());
            System.out.println("Orden Previo");
            for (String ele : t) {
                System.out.println(ele);
            }

            var t2 = arbolito.ORD_SIM(arbolito.RAIZ());
            System.out.println("\n Orden Simetrico (INORDEN)");
            for (String ele : t2) {
                System.out.println(ele);
            }

            var t3 = arbolito.ORD_POST(arbolito.RAIZ());
            System.out.println("\nOrden Posterior");
            for (String ele : t3) {
                System.out.println(ele);
            }
        } catch (RangoInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

}
