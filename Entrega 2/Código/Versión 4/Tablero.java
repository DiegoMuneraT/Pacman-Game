
/**
 * Write a description of class Tablero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;

public class Tablero
{
    private ArrayList<int[]> ruta = new ArrayList<>();
    /** Aquí dibujamos el tablero o laberinto
       De esta forma para no tener que utilizar otras clases para asignarle caracteres*/
    public char[][] archivo = {
            //"15 17",
            {'*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*'},
            {'*','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*'},
            {'*',' ','*','*','*','*','*','*',' ','*','*','*','*','*','*',' ','*'},
            {'*',' ','*',' ',' ',' ',' ','*',' ','*',' ',' ',' ',' ','*',' ','*'},
            {'*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*'},
            {'*',' ','*',' ',' ',' ',' ','*',' ','*',' ',' ',' ',' ','*',' ','*'},
            {'*',' ','*','*','*','*','*','*',' ','*','*','*','*','*','*',' ','*'},
            {'*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*'},
            {'*',' ','*','*','*','*','*','*',' ','*','*','*','*','*','*',' ','*'},
            {'*',' ','*',' ',' ',' ',' ','*',' ','*',' ',' ',' ',' ','*',' ','*'},
            {'*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','*'},
            {'*',' ','*',' ',' ',' ',' ','*',' ','*',' ',' ',' ',' ','*',' ','*'},
            {'*',' ','*','*','*','*','*','*',' ','*','*','*','*','*','*',' ','*'},
            {'*',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','O','*'},
            {'*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*','*'},
            //"P 1 1",
            // "O 13 15"
        }; 
    /**En este constructor instanciamos un nuevo arrayList para guardar la ruta
    Y definimos la salida del tablero y el punto de inicio*/
    public Tablero(){
        ruta = new ArrayList<>();
        archivo[13][15] = 'O'; 
        completar(1, 1);
    }
    /** Aquí se introducen las coordenadas desde las cuales empezará el pacman*/
    public void completar(int fila, int columna){
        if(camino(fila,columna)){
            archivo[fila][columna] = '˄';
        }
    }
    /** Aquí validamos si hemos llegado a la meta
       En este metodo también, comprobamos que el pacman no haya llegado a un punto muerto por así decirlo en el que se quede entre
       muros y lugares ya visitados*/
    private boolean camino(int fila, int columna){
        int[] pos = {fila,columna};
        if(archivo[fila][columna]=='O'){
            return true;
        }
        if(archivo[fila][columna]=='*'||archivo[fila][columna]=='x'||archivo[fila][columna]=='|'){
            return false;
        }
        /** Aquí vamos marcando las posiciones que nos han servido como visitadas y las guardamos en la ruta*/
        archivo[fila][columna]='x';
        ruta.add(pos);
        boolean resultado;

        resultado=camino(fila,columna+1);
        /** Aquí movemos al pacman a la izquierda, derecha, arriba y abajo según se considere*/
        if(resultado) return true;
        resultado=camino(fila-1,columna);
        if(resultado) return true;
        resultado=camino(fila,columna-1);
        if(resultado) return true;
        resultado=camino(fila+1,columna);

        if(resultado) return true;
        /** Aquí se comprueba las casillas que ya han sido visitadas y se marcan con un '|' */
        archivo[fila][columna]='|';
        ruta.add(pos);
        return false;
    }
    /** En este metodo imprimimos el tablero con la ruta ya trazada*/
    public void imprimir(){
        for(int x=0;x<archivo.length;x++){
            for(int y=0;y<archivo[x].length;y++){
                System.out.print(archivo[x][y]);
            }
            System.out.println("\t");
        }
    }
}
