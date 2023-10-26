import java.util.Scanner;

public class TicTacToe {

    public static void main(String[] args) throws Exception {
        Scanner stdinput = new Scanner(System.in);
        char[][] tablero = new char[3][3];
        insertar(tablero, 0, 0, stdinput);
        stdinput.close();
        boolean[] solucionado = { false };
        if(completar(tablero, 0, 0, solucionado)){
            for(int i = 0; i < tablero.length; i++){
                for(int j = 0; j < tablero.length; j++){
                    System.out.print(tablero[i][j]);
                }
                System.out.println();
            }
        }else{
            System.out.print("No hay solución.");//esValido y esSolucion
        }
    }

    public static void insertar(char[][] tablero, int x, int y, Scanner stdinput){
        if(x < tablero.length){
            String filaInput = stdinput.nextLine();
            if (!filaInput.trim().isEmpty()) {
                char[] fila = filaInput.toCharArray();
                assert fila.length == tablero[y].length;
                rellenarFila(tablero, x, 0, fila);
                insertar(tablero, x+1, 0, stdinput);
            }
        }
    } 

    public static void rellenarFila(char[][] tablero, int x, int y, char[] fila){
        if(y < tablero[x].length){
            tablero[x][y] = fila[y];
            rellenarFila(tablero, x, y+1, fila);
        }
    }

    public static boolean completar(char[][] tablero, int x, int y, boolean[] solucionado){
        if(tablero[x][y] == '_'){
            if (x == tablero.length-1 && y == tablero.length-1){
                solucionado[0] = true;
                return true;
            }
            if(y == tablero.length-1){
                return completar(tablero, x+1, 0, solucionado);
            }else{
                return completar(tablero, x, y+1, solucionado);
            }
        }else {
            
        }
        return false;
    }
}
