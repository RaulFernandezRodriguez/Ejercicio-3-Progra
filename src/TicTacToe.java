import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) throws Exception {
        Scanner stdinput = new Scanner(System.in);
        String Filainput = stdinput.nextLine();
        char [] fila = Filainput.toCharArray();
        char[][] tablero = new char[fila.length][fila.length];
        for(int i = 0; i < fila.length; i++){
            tablero[0][i] = fila[i];
        }
        insertar(tablero, 1, 0, stdinput);
        stdinput.close();
        if(completar(tablero, 0, 0)){
            for(int i = 0; i < tablero.length; i++){
                for(int j = 0; j < tablero.length; j++){
                    System.out.print(tablero[i][j]);
                }
                System.out.println();
            }
        }else{
            System.out.print("No hay solución.");
        }
    }

    public static void insertar(char[][] tablero, int x, int y, Scanner stdinput){
        if(x < tablero.length){
            String filaInput = stdinput.nextLine();
            char[] fila = filaInput.toCharArray();
            rellenarFila(tablero, x, 0, fila);
            insertar(tablero, x+1, 0, stdinput);
        }
    } 

    public static void rellenarFila(char[][] tablero, int x, int y, char[] fila){
        if(y < tablero[x].length){
            tablero[x][y] = fila[y];
            rellenarFila(tablero, x, y+1, fila);
        }
    }

    public static boolean completar(char[][] tablero, int x, int y){
        if (x == tablero.length){
            return comprobarOs(tablero) && solucion(tablero, x, y);
        }
        int newX = x, newY = y;
        if(y == tablero[y].length -1){
            newX++;
            newY = 0;
        }else{
            newY++;
        }
        if(tablero[x][y] != '_'){
            return completar(tablero, newX, newY);
        }else{
            tablero[x][y] = 'x';
            if(solucion(tablero, x , y) && completar(tablero, newX, newY)){
                return true;
            }
            tablero[x][y] = 'o';
            if(solucion(tablero, x , y) && completar(tablero, newX, newY)){
                return true;
            }
            tablero[x][y] = '_';
        } 
        return false;
    }

    public static boolean solucion(char[][] tablero, int x, int y){
// No puede haber mas 2 de x o os seguidas y tiene que haber le mismo numero de x en cada fila y columna
        int xConsecutivos = 0, oConsecutivas = 0;
        for(int i = 0; i < tablero.length; i++){
            xConsecutivos = oConsecutivas = 0;
            for(int j = 0; j < tablero.length; j++){
                if(tablero[i][j] == 'x'){
                    xConsecutivos++;
                    oConsecutivas = 0;
                }else if (tablero[i][j] == 'o'){
                    oConsecutivas++;
                    xConsecutivos = 0;
                }else{
                    xConsecutivos = oConsecutivas = 0;
                }

                if(xConsecutivos > 2 || oConsecutivas > 2){
                    return false;
                }
            }
        }

        for(int i = 0; i < tablero.length; i++){
            xConsecutivos = oConsecutivas = 0;
            for(int j = 0; j < tablero.length; j++){
                if(tablero[j][i] == 'x'){
                    xConsecutivos++;
                    oConsecutivas = 0;
                }else if (tablero[j][i] == 'o'){
                    oConsecutivas++;
                    xConsecutivos = 0;
                }else{
                    xConsecutivos = oConsecutivas = 0;
                }

                if(xConsecutivos > 2 || oConsecutivas > 2){
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean comprobarOs(char[][] tablero){
        int[] oFila = new int[tablero.length];
        int[] oColumna = new int[tablero.length];

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[i][j] == 'o') {
                    oFila[i]++;
                }
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (tablero[j][i] == 'o') {
                    oColumna[i]++;
                }
            }
        }

        for(int i = 0; i < tablero.length; i++){
            int conFila = oFila[0], conColumna = oColumna[0];
            if(conFila != oFila[i] || conColumna != oColumna[i]){
                return false;
            }
        }

        for (int i = 0; i < tablero.length; i++) {
            if (oFila[i] != oColumna[i]) {
                return false;
            }
        }
        return true;
    }
} 
