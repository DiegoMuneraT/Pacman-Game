
/**
 * Esta clase maneja el juego
 * Se tiene una referencia al tablero y al pacman
 * En esta clase se hace la interacción con el usuario
 * @author Helmuth Trefftz
 */
import java.util.Scanner;
import java.util.Random;

public class Juego {

    /**
     * El número de puntos iniciales de vida del pacman
     */
    public static int puntosVida = 10;
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
     * Interacción con el usuario.
     * En este método suceden varias cosas.
     * Primero, se verifican los movimientos que realizó el usuario
     * y si estos son válidos.
     * Segundo, se verifica si hay arepitas en el siguiente movimiento
     * para que el pacman se las coma.
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
            /** Teclas a, d, w, s para mover el pacman*/
            switch (linea) {
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
            /** Valida si la casilla siguiente es muro, o es arepita o la salida.*/
            if(validarCasilla(nuevaFila, nuevaCol)) {
                Celda anterior = tablero.tablero[fila][col];
                Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
                if(nueva.caracterCelda() == '.') {
                    nueva.tieneArepita = false;
                    pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                    anterior.caracter = null;
                    nueva.caracter = pacman;
                    /**Aquí pondremos arepitas buenas y malas aleatoriamente*/
                    Random r = new Random();
                    int arepita = r.nextInt(11);
                    if(arepita<4){
                        puntosVida ++;
                    }else{ puntosVida--; }
                }
                /**Aquí comprobamos que el jugador haya llegado a la meta
                  *Si llega con un punto de vida o más, gana el juego
                  *Si alcanza menos de un punto de vida pierde instantaneamente */
                if(nueva.caracterCelda() == 'O' && puntosVida>=1){
                    ganaElJuego=true;
                    break;
                }else if(puntosVida<=0){
                    ganaElJuego=false;
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
        }else{ System.out.println("Has perdido el nivel, ¡intentalo de nuevo!"); }
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

