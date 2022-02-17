
package TresEnRaya;
//El juego hará lo siguinete para lo que creará los métodos convenientes.

//Creará un clase tablero que estara formado por una matriz de 3x3 y el turno que inicia.
//cada celda de la matriz valdrá O,X o vacío. el jugador 1 rellenarán con 0 y el jugador 2 con X

//en cada tirada se comprobará si existe alguna fila, o columna o la diagonal con el mismo contenido
//en caso afirmativo se acabará el juego y se proclamará al vencedor. Si no hya vencedor se comprobará
// si el tablero ya está lleno por que también se acabaría el juego sin declarar vencedor "tablas"

//se creará un menú para indicar si se quiere seguir jugando o desea parar

//cada vez que un jugador juegue, se mostrará el estado del tablero


import javax.management.ObjectInstance;
import java.util.Scanner;

public class Jugar {
    public static Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        Tablero tablero=new Tablero();//creamos un tablero
        boolean jugar=pedirOpcion();//pedimos al usuario si quiere jugar o no
        boolean hayGanador=false, tableroLleno=false;
        tablero.mostrar();//mostramos el tablero vacío

        while (jugar){//mientras quiera seguir jugando partidas
        while (!tableroLleno && !hayGanador ){//mientras el tablero no esté lleno o no haya ningún ganador

            tablero.avisaAJugador(tablero.isJugador());//avisa al jugador para que mueva y recoge la opción
            tablero.mueveJugador (tablero.isJugador());//El jugador mueve. Se valida que el movimiento sea válido

            hayGanador=tablero.comprobarSiGanador();//se comprueba si con la última jugada hay algún ganador
            if (!hayGanador) {
                tableroLleno = tablero.comprobarSiLleno();//si no hay ganador se comprueba si el tablero ya está lleno
                if (!tableroLleno){
                    if (tablero.isJugador()){
                        tablero.setJugador(false);
                    }
                    else {
                        tablero.setJugador(true);
                    }
                }
            }
        }
        jugar=pedirOpcion();
    }
}

    private static boolean pedirOpcion() {
        boolean jugar=false;
        char teclado ='s';
        System.out.println("Indique si quiere jugar al tres en raya: Sí/No");
        try {
            teclado=Character.toLowerCase(sc.nextLine().charAt(0));
            if (teclado=='s'){
               jugar=true;
            }
        }
        catch (Exception e){
    }
        return jugar;
    }
}

