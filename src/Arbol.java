import java.util.ArrayList;
import java.util.List;

public class Arbol<T> {
    private int raiz;
    private Cursor<T> memoria;

    Arbol(){
        memoria = new Cursor<T>();
        raiz = -1;
    }

    public int RAIZ(){return raiz;}
    //CASO BASE
    public int CREA(T etiqueta){
        Nodo<T> nuevoNodo = new Nodo<T>(etiqueta);

        memoria.insertarNodo(nuevoNodo);
        int indiceNodo = nuevoNodo.getIndice();

        if (raiz==-1) {raiz = indiceNodo;}

        return indiceNodo;
    }
    //CASO RECURSIVO
    public int CREA(T etiqueta, int ... args ) throws RangoInvalidoException{
        Nodo<T> nuevoNodo = new Nodo<T>(etiqueta);

        memoria.insertarNodo(nuevoNodo);
        int indiceNodo = nuevoNodo.getIndice();

        Nodo<T> padre = memoria.getNodo(indiceNodo);
        padre.setHijoIzq(args[0]);


        int actual = args[0];
        for(int i=1; i< args.length; i++){
            int hijoSiguiente = args[i];
            memoria.getNodo(actual).setHermanoDer(hijoSiguiente);
            actual = hijoSiguiente;
        }

        memoria.getNodo(actual).setHermanoDer(-1);

        raiz = indiceNodo;

        return indiceNodo;
    }

    //METODOS PARA ORDEN PREVIO
    private void orden_previo(int nodo, ArrayList<T> lista) throws RangoInvalidoException{
        if (nodo==-1) {return;}

        var papunodo = memoria.getNodo(nodo);
        if (papunodo == null || papunodo.getEtiqueta() == null) {return;}
        
        lista.add(papunodo.getEtiqueta());
        orden_previo(papunodo.getHijoIzq(), lista);
        orden_previo(papunodo.getHermanoDer(), lista);

    }

    public ArrayList<T> ORD_PRE(int nodo) throws RangoInvalidoException{
        ArrayList<T> orden = new ArrayList<T>();
        orden_previo(nodo, orden);
        return orden;
    }
    //METODO PARA ORDEN SIMETRICO
     private void orden_Simetrico(int nodo, ArrayList<T> Lista) throws RangoInvalidoException{
        if(nodo == -1) {return;}

        var papupronodo = memoria.getNodo(nodo);
        if (papupronodo == null || papupronodo.getEtiqueta() == null) {return;}
        int hijoActual = papupronodo.getHijoIzq();

        if(hijoActual == -1){
            Lista.add(papupronodo.getEtiqueta());
            return;
        }
        //visita simetricamente el primer hijo
        orden_Simetrico(hijoActual,Lista);
        //Visitar raiz actual
         Lista.add(papupronodo.getEtiqueta());

         hijoActual = memoria.getNodo(hijoActual).getHermanoDer();
         while(hijoActual != -1){
             orden_Simetrico(hijoActual,Lista);
             hijoActual = memoria.getNodo(hijoActual).getHermanoDer();
         }
     }

     public ArrayList<T> ORD_SIM(int nodo) throws RangoInvalidoException{
         ArrayList<T> orden = new ArrayList<T>();
         orden_Simetrico(nodo, orden);
         return orden;
     }


        //ORDEN POSTERIOR
    private void orden_posterior(int nodo, ArrayList<T> lista) throws RangoInvalidoException{
    if(nodo == -1){return;}

    var papuhackernodo = memoria.getNodo(nodo);
        if (papuhackernodo == null || papuhackernodo.getEtiqueta() == null) { return; }

        //Visitar todos los hijos de izquierda a derecha
        int hijoActual = papuhackernodo.getHijoIzq();
        while (hijoActual != -1){
            orden_posterior(hijoActual, lista);
            hijoActual = memoria.getNodo(hijoActual).getHermanoDer();
        }
        //por ultimo la raiz
        lista.add(papuhackernodo.getEtiqueta());
    }
    public ArrayList<T> ORD_POST(int nodo) throws RangoInvalidoException{
        ArrayList<T> orden = new ArrayList<T>();
        orden_posterior(nodo,orden);
        return orden;
    }
}
