package TresEnRaya;

//El juego hará lo siguiente para lo que creará los métodos convenientes

//Creará un clase tablero que estara formado por una matrix de 2x2 y el turno que inicia
//cada celda de la matriz valdrá O,X o vacío. el jugador 1 rellenarán con 0 y el jugador 2 con X

//En cada tirada se comprobará si existe alguna fila, o columna o la diagonal con el mismo contenido
//en caso afirmativo se acabará el juego y se proclamará al vencedor. Si no hya vencedor se comprobará si el tablero
//ya está lleno por que también se acabaría el juego sin declarar vencedor: "tablas"

//se creará un menú para indicar si se quiere seguir jugando o desea parar
//cada vez que un jugador juegue, se mostrará el estado del tablero


import java.util.Scanner;

public class Jugar {
    private static final char NO = 'n';
    private static final char SI = 's';
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Tablero tablero = new Tablero();//creamos un tablero
        boolean jugar = pedirOpcion();//pedimos al usuario si quiere jugar o no
        boolean hayGanador = false, tableroLleno = false, finPartida = false;

        while (jugar) {//mientras quiera seguir jugando partidas
            tablero.mostrar();//mostramos el tablero
            while (!finPartida) {////mientras el tablero no esté lleno o no haya ningún ganador

                tablero.mueveJugador(tablero.getJugador());//avisa al jugador para que mueva y el jugador...
                //...mueve. Se valida que el movimiento sea válido y se modifica el tablero

                finPartida = tablero.compruebaEstadoJuego(tablero.getJugador());//se comprueba si ha finalizado la partida
                if (!finPartida) {
                    tablero.mueveJugador(tablero.getJugador());//avisa al otro jugador para que mueva
                    finPartida = tablero.compruebaEstadoJuego(tablero.getJugador());
                }
            }
            jugar = pedirOpcion();          //preguntamos  al usuario si quiere seguir jugando o no
            if (jugar) {                    //si quiere seguir jugando se limpiar el tablero
                tablero = new Tablero();
                finPartida=false;
            }
        }
        System.out.println("GAME OVER");
    }


    private static boolean pedirOpcion() {
        System.out.println("Desea Jugar al tres en raya?, diga Sí o No");
        boolean jugar = true, opcValida = false;
        char teclado;
        while (!opcValida) {
            try {
                teclado = Character.toLowerCase(sc.nextLine().charAt(0));
                if (teclado == NO) {
                    jugar = false;
                    opcValida = true;
                } else {
                    if (teclado == SI) {
                        opcValida = true;
                    } else {
                        System.out.println("introduzca Sí o No");
                    }
                }
            } catch (Exception e) {
                System.out.println("introduzca sí o no");
            }
        }
        return jugar;
    }
}