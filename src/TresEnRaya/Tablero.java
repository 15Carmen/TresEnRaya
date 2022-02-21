package TresEnRaya;

import java.util.Scanner;

public class Tablero {

    //

    static final Character circulo = 'O';
    static final Character cruz = 'X';
    static final Character vacio = ' ';
    static final String jugador1 = "JUGADOR 1";
    static final String jugador2 = "JUGADOR 2";
    static final String fila = "Fila";
    static final String columna = "Columna";
    static final String diagonal = "DIAGONAL";
    static char nulo;
    static Scanner scanner = new Scanner(System.in);

    private char[][] tablero = new char[3][3];
    private boolean jugador = true;//true es el jugador1 y false el jugador2

    public Tablero() {//el constructor limpia el tablero y da el turno al primer jugador
        // limpiarTablero(tablero);
        jugador = true;

    }

    //getter y setter

    public boolean getJugador() {
        return jugador;
    }

    public void setJugador(boolean jugador) {
        this.jugador = jugador;
    }

    public void limpiarTablero(Character[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                tablero[i][j] = vacio;
            }
        }
    }

    public void mostrar() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("el estado actual del tablero es el siguiente:\n");
        stringBuilder.append("   1 2 3 \n");
        for (int i = 0; i < tablero.length; i++) {
            stringBuilder.append(" "+ (i+1)+ " ");
            for (int j = 0; j < tablero[0].length; j++) {
                stringBuilder.append(this.tablero[i][j] + " ");
            }
            stringBuilder.append("\n");
        }
        System.out.println(stringBuilder);
    }

    /**
     * en este método se le avisa al jugador que tiene que mover
     *
     * @param jugador

    public void avisaAJugador(boolean jugador) {

    }*/

    /**
     * se le pide al jugador que toque que introduzca un dato válido de fila y comlumna
     *
     * @param jugador
     */
    public void mueveJugador(boolean jugador) {
        boolean tiradaValida = false;//servirá para ver si el jugador  ha seleccionado una celda ya ocupada
        String filaColumna = ""; //valdra Fila o Columna para no repetir código
        int fila = 0, columna = 0;
        if (jugador) {
            System.out.println("Es el turno del " + jugador1);
        } else {
            System.out.println("Es el turno del " + jugador2);
        }
        while (!tiradaValida) {
            filaColumna = String.valueOf(fila);//fila
            fila = pedirDato(filaColumna);//se pide la fila
            filaColumna = String.valueOf(columna);//columna
            columna = pedirDato(filaColumna);//se pide la columna
            if (this.tablero[fila-1][columna-1] == nulo) {//no han tirado a una posición ya ocupada
                tiradaValida = true;
            } else {
                System.out.println("Esa celda ya está ocupada");
                mostrar();
            }
        }
        if (jugador) {
            this.tablero[fila-1][columna-1] = circulo;
        } else {
            this.tablero[fila-1][columna-1] = cruz;
        }
    }


    private int pedirDato(String filaColumna) {
        boolean valida = false;
        int tirada = 0;
        while (!valida) {
            System.out.println("Indique, primero la fila y luego la columna, donde colocar el símbolo " + filaColumna + ", valores válidos 1,2 y 3");
            try {
                tirada = scanner.nextInt();
                if (tirada < 1 || tirada > 3) {
                    System.out.println("Indique donde colocar el símbolo " + filaColumna + " entre 1 y 3");
                } else {
                    valida = true;
                }
            } catch (Exception e) {
                System.out.println("Indique donde colocar el símbolo " + filaColumna + " entre 1 y 3");
                scanner.nextLine();
            }

        }
        return tirada;
    }

    public boolean comprobarSiGanador(boolean jugador) {
        boolean ganador = false;
        ganador = recorre(jugador, fila);
        if (!ganador) {
            ganador = recorre(jugador, columna);
            if (!ganador) {
                ganador = recorre(jugador, diagonal);
            }
        }
        return ganador;

    }


    /**
     * Recorro las filas, columnas y diagonales del tablero pero antes veo el carácter que tengo que buscar
     *
     * @param jugador
     * @return
     */
    private boolean recorre(boolean jugador, String filaColumnaDiagonal) {
        boolean encontradoTres = false;

        if (filaColumnaDiagonal == fila) {// recorre la fila
            for (int i = 0; i < this.tablero.length && !encontradoTres; i++) {
                if (this.tablero[i][0] != nulo &&
                        this.tablero[i][0] == this.tablero[i][1] &&
                        this.tablero[i][1] == this.tablero[i][2]) {
                    encontradoTres = true;
                }
            }
        } else if (filaColumnaDiagonal == columna) {// recorre la columna
            for (int i = 0; i < this.tablero[0].length && !encontradoTres; i++) {
                if (this.tablero[0][i] != nulo &&
                        this.tablero[0][i] == this.tablero[1][i] &&
                        this.tablero[1][i] == this.tablero[2][i]) {
                    encontradoTres = true;
                }
            }
        }
        if (filaColumnaDiagonal == diagonal) {// recorre la columna
            if (this.tablero[1][1] != nulo &&
                    this.tablero[0][0] == this.tablero[1][1] &&
                    this.tablero[1][1] == this.tablero[2][2]) {
                encontradoTres = true;
            } else {
                if (this.tablero[2][0] != nulo &&
                        this.tablero[2][0] == this.tablero[1][1] &&
                        this.tablero[1][1] == this.tablero[0][2]) {
                    encontradoTres = true;
                }
            }
        }
        return encontradoTres;
    }

    public boolean comprobarSiLleno() {
        boolean lleno = true;
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero[0].length; j++) {
                if (this.tablero[i][j] == nulo){
                    lleno = false;}
            }
        }
        return lleno;
    }

    public boolean compruebaEstadoJuego(boolean jugador) {
        boolean hayGanador = false, tableroLleno = false, finPartida = false;

        hayGanador = comprobarSiGanador(jugador);//se comprueba si con la última jugada hay algún ganador
        if (hayGanador) {
            ganador(jugador);//sacamos un mensaje de ganador
            finPartida = true;
        } else {             //si no hay ganador se comprueba si el tablero está lleno
            tableroLleno = comprobarSiLleno();//si no hay ganador se comprueba si el tablero ya está lleno
            if (tableroLleno) {
                finPartida = true;
                lleno();
            }else {//si no está lleno y no hay ganador, cambiamos al otro jugador
                this.jugador = !jugador;
                System.out.println("el tablero no está lleno, se puede seguir jugando");
                mostrar();
            }
        }
        return finPartida;
    }

    public void ganador(boolean jugador) {
        StringBuilder stringBuilder = new StringBuilder();
        mostrar();
        stringBuilder.append("------Ya hay tres en raya------ \n");
        stringBuilder.append("---------EL GANADOR ES---------     \n");
        if (jugador) {
            stringBuilder.append("¡¡"+ jugador1 + "!!\n");
        } else {
            stringBuilder.append("¡¡"+ jugador2 + "!!\n");
        }
        System.out.println(stringBuilder);
    }
    public void lleno() {
        StringBuilder stringBuilder = new StringBuilder();
        mostrar();
        stringBuilder.append("¡El tablero está lleno!\n");
        System.out.println(stringBuilder);
    }
}