import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) throws Exception {
        Scanner stdinput = new Scanner(System.in);
        
        char[][] tablero = new char[][];
        insertar(tablero, 0, 0, stdinput);
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
            if(solucion(tablero, x , y, 'x') && completar(tablero, newX, newY)){
                return true;
            }
            tablero[x][y] = 'o';
            if(solucion(tablero, x , y, 'o') && completar(tablero, newX, newY)){
                return true;
            }
            tablero[x][y] = '_';
        } 
        return false;
    }

    public static boolean solucion(char[][] tablero, int x, int y, char ficha){
        int contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[x][i] == ficha) {
                contador++;
                if (contador == 3) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }

        contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i][y] == ficha) {
                contador++;
                if (contador == 3) {
                    return true;
                }
            } else {
                contador = 0;
            }
        }

        if (x == y) {
            contador = 0;
            for (int i = 0; i < tablero.length; i++) {
                if (tablero[i][i] == ficha) {
                    contador++;
                    if (contador == 3) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            } // No puede haber mas 2 de x o os seguidas y tiene que haber le mismo numero de x en cada fila y columna
        }

        if (x + y == tablero.length - 1) {
            contador = 0;
            for (int i = 0; i < tablero.length; i++) {
                if (tablero[i][tablero.length - 1 - i] == ficha) {
                    contador++;
                    if (contador == 3) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }

        int xNum = 0, oNum = 0;
        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                if(tablero[i][j] == 'x'){
                    xNum++;
                } else if (tablero[i][j] == 'o'){
                    oNum++;
                }
                if(xNum > 2 || oNum > 2){
                    return false;
                }
            }
            xNum = oNum = 0;
        }

        for(int i = 0; i < tablero.length; i++){
            for(int j = 0; j < tablero.length; j++){
                if(tablero[j][i] == 'x'){
                    xNum++;
                } else if (tablero[j][i] == 'o'){
                    oNum++;
                }
                if(xNum > 2 || oNum > 2){
                    return false;
                }
            }
            xNum = oNum = 0;
        }

        // int xNum = 0, yNum = 0;
        // for(int i = 0; i < tablero.length; i++){
        //     if(tablero[x][i] == 'x'){
        //         xNum++;
        //     }else if(tablero[x][i] == 'o'){
        //         yNum++;
        //     }
        //     if(xNum > 2 || yNum > 2){
        //         return false;
        //     }
        // }

        // xNum = 0;
        // yNum = 0;
        // for(int i = 0; i < tablero.length; i++){
        //     if(tablero[i][y] == 'x'){
        //         xNum++;
        //     }else if(tablero[i][y] == 'o'){
        //         yNum++;
        //     }
        //     if(xNum > 2 || yNum > 2){
        //         return false;
        //     }
        // }
        
        int xOs = 0, yOs = 0;
        for(int i = 0; i < tablero.length; i++){
            if(tablero[x][i] == 'o'){
                xOs++;
            }
            if(tablero[i][y] == 'o'){
                yOs++;
            }
        }
        return (xOs <= tablero.length/2) && (yOs <= tablero.length/2);
    }
}
