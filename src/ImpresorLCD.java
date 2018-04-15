import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImpresorLCD {

    // Puntos fijos
    private final int[] pf1;
    private final int[] pf2;
    private final int[] pf3;
    private final int[] pf4;
    private final int[] pf5;
    
    //Variables estáticas, constantes dentro de la clase
    static final String CARACTER_VERTICAL = "|";
    static final String CARACTER_HORIZONTAL = "-";
    static final String POSICION_X = "X";
    static final String POSICION_Y = "Y";

    //Declara variables del objeto
    private int size;
    private int filasDig;
    private int columDig;
    private int totalFilas;
    private int totalColum; 
    private String[][] matrizImpr;

    public ImpresorLCD() {
        // Inicializa variables
        this.pf1 = new int[2];
        this.pf2 = new int[2];
        this.pf3 = new int[2];
        this.pf4 = new int[2];
        this.pf5 = new int[2];
    }
    

    /**
     *
     * Metodo encargado de añadir una linea a la matriz de Impresion
     *
     * @param matriz Matriz Impresion
     * @param punto Punto Pivote
     * @param posFija Posicion Fija
     * @param size Tamaño Segmento
     * @param caracter Caracter Segmento
     */    
    private void adicionarLinea(String[][] matriz, int[] punto, String posFija,
            int size, String caracter) {

        if (posFija.equalsIgnoreCase(POSICION_X)) 
        {
            for (int y = 1; y <= size; y++) 
            {
                int valor = punto[1] + y;
                matriz[punto[0]][valor] = caracter;
            }
        } 
        else 
        {
            for (int i = 1; i <= size; i++) 
            {
                int valor = punto[0] + i;
                matriz[valor][punto[1]] = caracter;
            }
        }
    }

    /**
     *
     * Metodo encargado de un segmento a la matriz de Impresion
     *
     * @param segmento Segmento a adicionar
     */  
    private void adicionarSegmento(int segmento) {

        switch (segmento) {
            case 1:
                adicionarLinea(matrizImpr, pf1, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 2:
                adicionarLinea(matrizImpr, pf2, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 3:
                adicionarLinea(matrizImpr, pf5, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 4:
                adicionarLinea(matrizImpr, pf4, POSICION_Y,
                        size, CARACTER_VERTICAL);
                break;
            case 5:
                adicionarLinea(matrizImpr, pf1, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 6:
                adicionarLinea(matrizImpr, pf2, POSICION_X,
                        size, CARACTER_HORIZONTAL);
                break;
            case 7:
                adicionarLinea(matrizImpr, pf3, POSICION_X,
                        this.size, CARACTER_HORIZONTAL);
                break;
            default:
                break;
        }
    }

    /**
     *
     * Metodo encargado de definir los segmentos que componen un digito y
     * a partir de los segmentos adicionar la representacion del digito a la matriz
     *
     * @param numero Digito
     */
    private void adicionarDigito(int numero) {

        // Establece los segmentos de cada numero
        List<Integer> segList = new ArrayList<>();

        switch (numero) {
            case 1:
                segList.add(3);
                segList.add(4);
                break;
            case 2:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                break;
            case 3:
                segList.add(5);
                segList.add(3);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 4:
                segList.add(1);
                segList.add(6);
                segList.add(3);
                segList.add(4);
                break;
            case 5:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(4);
                segList.add(7);
                break;
            case 6:
                segList.add(5);
                segList.add(1);
                segList.add(6);
                segList.add(2);
                segList.add(7);
                segList.add(4);
                break;
            case 7:
                segList.add(5);
                segList.add(3);
                segList.add(4);
                break;
            case 8:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 9:
                segList.add(1);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(6);
                segList.add(7);
                break;
            case 0:
                segList.add(1);
                segList.add(2);
                segList.add(3);
                segList.add(4);
                segList.add(5);
                segList.add(7);
                break;
            default:
                break;
        }

        Iterator<Integer> iterator = segList.iterator();

        while (iterator.hasNext()) {
            adicionarSegmento(iterator.next());
        }
    }

    /**
     *
     * Metodo encargado de imprimir un numero
     *
     * @param size Tamaño Segmento Digitos
     * @param numeroImp Numero a Imprimir
     * @param espacio Espacio Entre digitos
     */    
    private void imprimirNumero(int size, String numeroImp, int espacio) 
    {
        int pivotX = 0;
        char[] digitos;

//        Solo es necesaraio usar el keyword this para el atributo size, debido a su homonimia con el parámetro
//        size del método
        this.size = size;
        
        // Calcula el numero de filas cada digito
        filasDig = (2 * this.size) + 3;

        // Calcula el numero de columna de cada digito
        columDig = this.size + 2;

        // Calcula el total de filas de la matriz en la que se almacenaran los digitos
        totalFilas = filasDig;

        // Calcula el total de columnas de la matriz en la que se almacenaran los digitos
        totalColum = (columDig * numeroImp.length())
                + (espacio * numeroImp.length());

        // crea matriz para almacenar los numero a imprimir
        matrizImpr = new String[totalFilas][totalColum];

        // crea el arreglo de digitos
        digitos = numeroImp.toCharArray();

        // Inicializa matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {        //error de espaciado      to talColum -> totalColum
                matrizImpr[i][j] = " ";
            }
        }

        for (char digito : digitos) {
            
            //Valida que el caracter sea un digito
            if( ! Character.isDigit(digito))
            {
                throw new IllegalArgumentException("Caracter " + digito
                    + " no es un digito");
            }

            int numero = Integer.parseInt(String.valueOf(digito));
            
            calculaPuntosFijos(filasDig,columDig,pivotX);
            pivotX = pivotX + columDig + espacio;

            adicionarDigito(numero);
        }

        // Imprime matriz
        for (int i = 0; i < totalFilas; i++) {
            for (int j = 0; j < totalColum; j++) {
                System.out.print(matrizImpr[i][j]);
            }
            System.out.println();
        }
    }

     /**
     *
     * Metodo encargado de procesar la entrada que contiene el size del segmento
 de los digitos y los digitos a imprimir
     *
     * @param comando Entrada que contiene el size del segmento de los digito
     * y el numero a imprimir
     * @param espacioDig Espacio Entre digitos
     */  
    public void procesarEntrada(String comando, int espacioDig) {           //Se renombra el métoto a ProcesarEntrada con el fin de dar un entendimiento inicial
        
        String[] parametros;
        int tam;
        if (!comando.contains(",")) {
            throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene caracter ,");
        }
        
        //Se hace el split de la cadena
        parametros = comando.split(",");
        
        //Valida la cantidad de parametros
        if(parametros.length>2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " contiene mas caracter ,"); 
        }
        
        //Valida la cantidad de parametros
        if(parametros.length<2)
        {
           throw new IllegalArgumentException("Cadena " + comando
                    + " no contiene los parametros requeridos"); 
        }
        
        //Valida que el parametro size sea un numerico
        if(isNumeric(parametros[0]))
        {
            tam = Integer.parseInt(parametros[0]);
            
            // se valida que el size este entre 1 y 10
            if(tam <1 || tam >10)
            {
                throw new IllegalArgumentException("El parametro size ["+tam
                        + "] debe estar entre 1 y 10");
            }
        }
        else
        {
            throw new IllegalArgumentException("Parametro Size [" + parametros[0]
                    + "] no es un numero");
        }

        // Realiza la impresion del numero
        imprimirNumero(tam, parametros[1],espacioDig);
    }

    /**
     *
     * Metodo encargado de validar si una cadena es numerica
     *
     * @param cadena Cadena
     */  
    static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    //Método que calcula los puntos fijos, para restar responsabilidad a imprimirNúmero() y modularizar
    private void calculaPuntosFijos(int filas, int columnas, int pivotX) {
        
        pf1[0] = 0;
        pf1[1] = 0 + pivotX;

        pf2[0] = (filasDig / 2);
        pf2[1] = 0 + pivotX;

        pf3[0] = (filasDig - 1);
        pf3[1] = 0 + pivotX;

        pf4[0] = (columDig - 1);
        pf4[1] = (filasDig / 2) + pivotX;

        pf5[0] = 0;
        pf5[1] = (columDig - 1) + pivotX;
        
       
        
    }

}
