
/**
 * Esta clase maneja el juego
 * Se tiene una referencia al tablero y al pacman
 * En esta clase se hace la interacción con el usuario
 * @author Helmuth Trefftz
 */
import java.util.Scanner;

public class Juego {

    /**
     * El número de puntos iniciales de vida del pacman
     */
    public static final int PUNTOS_VIDA_INICIALES = 10;
    Tablero tablero;
    Pacman pacman;

    /**
     * Constructor
     * Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
    }

    /**
     * Interacción con el usuario
     * aquí se reciben los movimientos que haga el usuario
     * y se comprueba que sean movimientos validos
     */
    public void jugar() {
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego) {
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            switch (linea) {
                // En este punto se inserta el código para las teclas
                // "a" y "d"
                case "a":
                nuevaCol = col - 1;
                break;
                case "d":
                nuevaCol = col + 1;
                break;
                case "w":
                nuevaFila = fila - 1;
                break;
                case "s":
                nuevaFila = fila + 1;
                break;
            }
            if(validarCasilla(nuevaFila, nuevaCol)) {
                Celda anterior = tablero.tablero[fila][col];
                Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
                if(nueva.caracterCelda() == 'O'){
                    ganaElJuego=true;
                    break;
                }
                nueva.caracter = pacman;
                anterior.caracter = null;
                pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                // aquí se debe validar si el usuario llegó a la meta y 
                // ganó el juego
            }
            tablero.dibujarTablero();
            linea = in.nextLine();

        }
        if(ganaElJuego) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        }
    }

    /**
     * En este metodo se debe chequear las siguientes condiciones:
     * (i) Que el usuario no se salga de las filas del tablero
     * (ii) Que el usuario no se salga de las columnas del tablero
     * (iii) Que la posición no sea un muro
     * (iv) Que no haya un caracter en esa posición
     * 
     * @param nuevaFila Fila hacia donde se quiere mover el usuario
     * @param nuevaCol Columna hacia donde se quiere mover el usuario
     * @return true si es una jugada válida, false de lo contrario
     */
    /** Este método sirve para que el pacman no se salga del mapa*/
    private boolean validarCasilla(int nuevaFila, int nuevaCol){
        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
        if(nueva.caracterCelda() == '*'){
            return false;
        }
        // Aquí hay que verificar que sea un movimiento válido
        // Ver los comentarios del método
        return true;
    }
}

