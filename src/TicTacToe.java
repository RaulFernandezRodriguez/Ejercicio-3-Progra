import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        Scanner stdinput = new Scanner(System.in);
        int[][] tablero = new int[3][3];

        insertar();
        completar();

        
    }

    public static void insertar(int[][] matriz, int x, int y, Scannner stdinput){
        if(x < matriz.length){
            String filaInput = stdinput.nextLine();
            if (!filaInput.trim().isEmpty()) {
                String[] fila = filaInput.split(" ");
                assert fila.length == matriz[y].length;
                rellenarFila(matriz, x, 0, fila);
                insertar(matriz, x+1, 0, stdinput);
            }
        }
    } 

    public static void rellenarFila(int[][] matriz, int x, int y, String[] fila){
        if(y < matriz[x].length){
            matriz[x][y] = (int) Math.sqrt(Integer.parseInt(fila[y]));
            rellenarFila(matriz, x, y+1, fila);
        }
    }

}
