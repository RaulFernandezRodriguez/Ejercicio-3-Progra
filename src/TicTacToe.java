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
            return true;
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
        // for (int i = 0; i < tablero.length; i++) {
        //     int consecutivasFila = 0;
        //     int consecutivasColumna = 0;
        //     int oCountFila = 0;
        //     int oCountColumna = 0;

        //     for (int j = 0; j < tablero.length; j++) {
        //         if (tablero[x][j] == 'x') {
        //             consecutivasFila++;
        //             consecutivasColumna = 0;
        //         } else if (tablero[x][j] == 'o') {
        //             consecutivasFila = 0;
        //             consecutivasColumna++;
        //             oCountFila++;
        //             oCountColumna++;
        //         } else {
        //             consecutivasFila = 0;
        //             consecutivasColumna = 0;
        //         }

        //         if (consecutivasFila > 2 || consecutivasColumna > 2) {
        //             return false;
        //         }
        //     }

        //     if (oCountFila > tablero.length / 2 || oCountColumna > tablero.length / 2) {
        //         return false;
        //     }
        // }

        int xConsecutivos = 0, oConsecutivas = 0, oFila = 0, oColumna = 0;
        for(int i = 0; i < tablero.length; i++){
            if(tablero[x][i] == 'x'){
                xConsecutivos++;
                oConsecutivas = 0;
            } else if(tablero[x][i] == 'o'){
                oConsecutivas++;
                xConsecutivos = 0;
                oFila++;
            } else{
                oConsecutivas = 0;
                xConsecutivos = 0;
            }
            if (xConsecutivos > 2 || oConsecutivas > 2) {
                return false;
            }
        }

        xConsecutivos = oConsecutivas = 0;
        for(int i = 0; i < tablero.length; i++){
            if(tablero[i][y] == 'x'){
                xConsecutivos++;
                oConsecutivas = 0;
            } else if(tablero[i][y] == 'o'){
                oConsecutivas++;
                xConsecutivos = 0;
                oColumna++;
            } else{
                oConsecutivas = 0;
                xConsecutivos = 0;
            }
            if (xConsecutivos > 2 || oConsecutivas > 2) {
                return false;
            }
        }

        if ((oFila > tablero.length / 2) || (oColumna > tablero.length / 2)) {
            return false;
        }
        // int xNum = 0, oNum = 0;
        // for(int i = 0; i < tablero.length; i++){
        //     for(int j = 0; j < tablero.length; j++){
        //         if(tablero[i][j] == 'x'){
        //             xNum++;
        //         } else if (tablero[i][j] == 'o'){
        //             oNum++;
        //         }
        //         if(xNum > 2 || oNum > 2){
        //             return false;
        //         }
        //     }
        //     xNum = oNum = 0;
        // }

        // for(int i = 0; i < tablero.length; i++){
        //     for(int j = 0; j < tablero.length; j++){
        //         if(tablero[j][i] == 'x'){
        //             xNum++;
        //         } else if (tablero[j][i] == 'o'){
        //             oNum++;
        //         }
        //         if(xNum > 2 || oNum > 2){
        //             return false;
        //         }
        //     }
        //     xNum = oNum = 0;
        // }
  
        // int xOs = 0, yOs = 0;
        // for(int i = 0; i < tablero.length; i++){
        //     if(tablero[x][i] == 'o'){
        //         xOs++;
        //     }
        //     if(tablero[i][y] == 'o'){
        //         yOs++;
        //     }
        // }

        return true;
    }
}
