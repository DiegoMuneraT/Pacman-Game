
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
    public static final int PUNTOS_VIDA_INICIALES = 10;
    public static int puntosVida = 10; 
    Tablero tablero;
    Pacman pacman;
    Fantasma fantasma;
    /**
     * Constructor
     * Se crea un tablero
     */
    public Juego() {
        tablero = new Tablero(this);
    }

    /**
     * Interacción con el usuario
     */
    public void jugar() {
        boolean ganaElJuego = false;
        tablero.dibujarTablero();
        Scanner in = new Scanner(System.in);
        String linea = in.nextLine();
        while (!linea.equals("q") && !ganaElJuego) {
            int filaf = fantasma.posicion.fila;
            int colf = fantasma.posicion.col;
            int nuevaFilaf = filaf;
            int nuevaColf = colf;;
            int fila = pacman.posicion.fila;
            int col = pacman.posicion.col;
            int nuevaFila = fila;
            int nuevaCol = col;
            Random m = new Random(); 
            int movimiento = m.nextInt(2); 
            switch (movimiento) {
                case 0: 
                nuevaColf = colf - 1;
                break;
                case 1: 
                nuevaColf = colf + 1;
                break;
            }
            switch (linea) {
                // En este punto se inserta el código para las teclas
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
            if (validarCasilla(nuevaFila, nuevaCol, nuevaFilaf, nuevaColf)) {
                Celda anterior = tablero.tablero[fila][col];
                Celda anteriorf = tablero.tablero[filaf][colf];
                Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
                Celda nuevaf = tablero.tablero[nuevaFilaf][nuevaColf];
                if (nueva.caracterCelda() == '*'){
                    nueva.esMuro = true; 
                    nuevaf.esMuro = true; 
                    pacman.posicion = new Posicion(fila, col);
                    anterior.caracter = pacman; 
                    nueva.caracter = null;
                    fantasma.posicion = new Posicion(filaf, colf);
                    anteriorf.caracter = fantasma; 
                    nuevaf.caracter = null;
                }
                if (nueva.caracterCelda() == '.') {
                    nueva.tieneArepita = false;
                    pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                    anterior.caracter = null; 
                    nueva.caracter = pacman;
                    nuevaf.tieneArepita = false;
                    fantasma.posicion = new Posicion(nuevaFilaf - 1, nuevaColf - 1);
                    anteriorf.caracter = null; 
                    nuevaf.caracter = fantasma;
                    Random r = new Random(); 
                    int arepita = r.nextInt(11);
                    if (arepita<4){
                        puntosVida++;
                    } else {puntosVida--;}
                } 
                if (nueva.caracterCelda() == 'O' && puntosVida >= 1) {
                    ganaElJuego = true;
                    break; 
                } else if (puntosVida <= 0 || nueva.caracterCelda() == 'F') {
                    ganaElJuego = false; 
                    break; 
                }
                nueva.caracter = pacman;
                anterior.caracter = null;
                nuevaf.caracter = fantasma;
                anteriorf.caracter = null;
                pacman.posicion = new Posicion(nuevaFila, nuevaCol);
                fantasma.posicion = new Posicion(nuevaFilaf, nuevaColf);
            }

            tablero.dibujarTablero();
            linea = in.nextLine();
        }
        if(ganaElJuego) {
            System.out.println("Has ganado el juego, ¡felicitaciones!");
        } else {
            System.out.println("Haz perdido el juego, ¡intenta de nuevo!");
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
    private boolean validarCasilla(int nuevaFila, int nuevaCol, int nuevaFilaf, int nuevaColf) {
        // Aquí hay que verificar que sea un movimiento válido
        // Ver los comentarios del método
        Celda nueva = tablero.tablero[nuevaFila][nuevaCol];
        Celda nuevaf = tablero.tablero[nuevaFilaf][nuevaColf];
        if (nueva.caracterCelda() == '*' || nuevaf.caracterCelda() == '*'){
            return false; 
        }
        if (nueva.caracterCelda() == '.'){
            return true; 
        }
        if (nueva.caracterCelda() == 'M'){
            return true; 
        }
        return true;
    }
}
