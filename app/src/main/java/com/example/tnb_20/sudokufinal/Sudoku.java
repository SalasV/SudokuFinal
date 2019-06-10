package com.example.tnb_20.sudokufinal;

import java.util.ArrayList;
import java.util.List;

public class Sudoku {
    public List<Integer> tablero;
    static List<Integer> casillasCuadrante = new ArrayList();

    public List inicializar() {
        tablero= new ArrayList();

        //Creamos un tablero sudoku vacio
        for (int i = 0; i < 81; i++) {
            tablero.add(0);
        }

        return tablero;
    }

    public List getTablero() {
        return tablero;
    }


    public void rellenarCuadrante(int numCuadrante) {
        //Rellenamos cada cuadrante 3x3
        casillasCuadrante.clear();
        seleccionarCaso(numCuadrante);

        //En cada cuadrante se rellenara inicialmente con 3 numeros
        for (int i = 0; i < 6; i++) {
            int posicion;
            int numero;
            do {
                boolean posicionOcupada = true;
                //Elegimos un numero al azar para seleccionar la posicion
                posicion = (int) (Math.random() * 9);

                //Comprobamos que la posicion no esta ocupada
                while (posicionOcupada) {
                    //Si la posicion esta ocupada generamos otra
                    if (tablero.get(casillasCuadrante.get(posicion) - 1) != 0) {
                        posicion = (int) (Math.random() * 9);
                    } else {
                        posicionOcupada = false;
                    }
                }

                //Generamos el numero que vamos a colocar y comprobamos que no se repite en el cuadrante, fila y columna
                numero = numeroAColocar();

                //En caso de estar repetido volvemos a generar un numero y una posicion
            } while (comprobarColumna(casillasCuadrante.get(posicion) - 1, numero) == false
                    || comprobarFila(casillasCuadrante.get(posicion) - 1, numero) == false);

            tablero.set(casillasCuadrante.get(posicion) - 1, numero);
        }

    }

    private boolean comprobarFila(int posicion, int numero) {
        int x = 0, y = 0;

        //Comprobamos a que fila pertenece la posicion
        if (posicion > -1 && posicion < 9) {
            x = 0;
            y = 9;
        } else if (posicion > 8 && posicion < 18) {
            x = 9;
            y = 18;
        } else if (posicion > 17 && posicion < 27) {
            x = 18;
            y = 27;
        } else if (posicion >= 26 && posicion < 36) {
            x = 27;
            y = 36;
        } else if (posicion >= 35 && posicion < 45) {
            x = 36;
            y = 45;
        } else if (posicion >= 44 && posicion < 54) {
            x = 45;
            y = 54;
        } else if (posicion >= 53 && posicion < 63) {
            x = 54;
            y = 63;
        } else if (posicion >= 62 && posicion < 72) {
            x = 63;
            y = 72;
        } else if (posicion >= 71 && posicion < 81) {
            x = 72;
            y = 81;
        }

        //Comprobamos que el numero no este en esa fila
        for (int i = x; i < y; i++) {
            if (tablero.get(i) == numero && posicion != i) {
                return false;
            }
        }

        return true;
    }

    private boolean comprobarColumna(int posicion, int numero) {
        int x = 0, y = 0;
        int posicionOriginal = posicion;

        //Comprobamos a que columna pertenece la posicion
        while (posicion != 0 && posicion != 1 && posicion != 2 && posicion != 3 && posicion != 4 && posicion != 5
                && posicion != 6 && posicion != 7 && posicion != 8) {
            posicion = posicion - 9;
        }

        if (posicion == 0) {
            x = 0;
            y = 72;
        } else if (posicion == 1) {
            x = 1;
            y = 73;
        } else if (posicion == 2) {
            x = 2;
            y = 74;
        } else if (posicion == 3) {
            x = 3;
            y = 75;
        } else if (posicion == 4) {
            x = 4;
            y = 76;
        } else if (posicion == 5) {
            x = 5;
            y = 77;
        } else if (posicion == 6) {
            x = 6;
            y = 78;
        } else if (posicion == 7) {
            x = 7;
            y = 79;
        } else if (posicion == 8) {
            x = 8;
            y = 80;
        }

        //Comprobamos que el numero no este en esa columna
        for (int i = x; i < y + 1; i += 9) {
            if (tablero.get(i) == numero && posicionOriginal != i) {
                return false;
            }
        }

        return true;
    }

    private int numeroAColocar() {
        boolean mismoNumero = false;

        int numero;

        //Generamos un numero aleatorio del 1 al 9 y comprobamos que no este en el mismo cuadrante
        do {
            mismoNumero = false;
            numero = (int) (Math.random() * 9);
            for (int i = 0; i < casillasCuadrante.size(); i++) {
                if (tablero.get(casillasCuadrante.get(i) - 1) == numero + 1) {
                    mismoNumero = true;
                }
            }
        } while (mismoNumero == true);

        return numero + 1;
    }

    private void seleccionarCaso(int numCuadrante) {
        //Seleccionamos las casillas del cuadrante que vamos a rellenar
        switch (numCuadrante) {
            case 1:
                casillasCuadrante.add(1);
                casillasCuadrante.add(2);
                casillasCuadrante.add(3);

                casillasCuadrante.add(10);
                casillasCuadrante.add(11);
                casillasCuadrante.add(12);

                casillasCuadrante.add(19);
                casillasCuadrante.add(20);
                casillasCuadrante.add(21);
                break;
            case 2:
                casillasCuadrante.add(4);
                casillasCuadrante.add(5);
                casillasCuadrante.add(6);

                casillasCuadrante.add(13);
                casillasCuadrante.add(14);
                casillasCuadrante.add(15);

                casillasCuadrante.add(22);
                casillasCuadrante.add(23);
                casillasCuadrante.add(24);
                break;
            case 3:
                casillasCuadrante.add(7);
                casillasCuadrante.add(8);
                casillasCuadrante.add(9);

                casillasCuadrante.add(16);
                casillasCuadrante.add(17);
                casillasCuadrante.add(18);

                casillasCuadrante.add(25);
                casillasCuadrante.add(26);
                casillasCuadrante.add(27);

                break;
            case 4:
                casillasCuadrante.add(28);
                casillasCuadrante.add(29);
                casillasCuadrante.add(30);

                casillasCuadrante.add(37);
                casillasCuadrante.add(38);
                casillasCuadrante.add(39);

                casillasCuadrante.add(46);
                casillasCuadrante.add(47);
                casillasCuadrante.add(48);

                break;
            case 5:
                casillasCuadrante.add(31);
                casillasCuadrante.add(32);
                casillasCuadrante.add(33);

                casillasCuadrante.add(40);
                casillasCuadrante.add(41);
                casillasCuadrante.add(42);

                casillasCuadrante.add(49);
                casillasCuadrante.add(50);
                casillasCuadrante.add(51);

                break;
            case 6:
                casillasCuadrante.add(34);
                casillasCuadrante.add(35);
                casillasCuadrante.add(36);

                casillasCuadrante.add(43);
                casillasCuadrante.add(44);
                casillasCuadrante.add(45);

                casillasCuadrante.add(52);
                casillasCuadrante.add(53);
                casillasCuadrante.add(54);

                break;
            case 7:
                casillasCuadrante.add(55);
                casillasCuadrante.add(56);
                casillasCuadrante.add(57);

                casillasCuadrante.add(64);
                casillasCuadrante.add(65);
                casillasCuadrante.add(66);

                casillasCuadrante.add(73);
                casillasCuadrante.add(74);
                casillasCuadrante.add(75);
                break;
            case 8:
                casillasCuadrante.add(58);
                casillasCuadrante.add(59);
                casillasCuadrante.add(60);

                casillasCuadrante.add(67);
                casillasCuadrante.add(68);
                casillasCuadrante.add(69);

                casillasCuadrante.add(76);
                casillasCuadrante.add(77);
                casillasCuadrante.add(78);
                break;
            case 9:
                casillasCuadrante.add(61);
                casillasCuadrante.add(62);
                casillasCuadrante.add(63);

                casillasCuadrante.add(70);
                casillasCuadrante.add(71);
                casillasCuadrante.add(72);

                casillasCuadrante.add(79);
                casillasCuadrante.add(80);
                casillasCuadrante.add(81);
                break;
        }
    }

    public void mostrarTablero() {
        int cont = 0, cont2 = 0;

        //Mostramos el tablero rellenado
        for (int i = 0; i < tablero.size(); i++) {
            System.out.print(tablero.get(i) + " ");
            cont++;
            if (cont == 3 || cont == 6) {
                System.out.print(" ");
            }
            if (cont == 9) {
                System.out.println();
                cont = 0;
                cont2++;
            }
            if (cont2 == 3) {
                System.out.println();
                cont2 = 0;
            }
        }
    }

    public boolean comprobarTablero(int posicion, int numero) {
        boolean win = true;

        //Comprobamos que el tablero esta rellenado y que no hay numeros repetidos
        if (comprobarColumna(posicion,numero) == false || comprobarFila(posicion, numero) == false
                || comprobarCuadrante(numero, posicion) == false || numero == 0) {
            win = false;
        }

        return win;
    }

    private boolean comprobarCuadrante(int numero, int posicion) {
        int cuadranteAComprobar=0;

        //Seleccionamos el cuadrante al que pertenece la posicion
        for (int i = 0; i < 9; i++) {
            casillasCuadrante.clear();
            seleccionarCaso(i);
            for (int j = 0; j < casillasCuadrante.size(); j++) {
                if (posicion==casillasCuadrante.get(j)-1) {
                    cuadranteAComprobar=i;
                }
            }
        }

        casillasCuadrante.clear();
        seleccionarCaso(cuadranteAComprobar);
        //Comprobamos que el numero no esta en el cuadrante
        for (int j = 0; j < casillasCuadrante.size(); j++) {
            if (tablero.get(casillasCuadrante.get(j)-1) == numero && casillasCuadrante.get(j)-1 != posicion) {
                return false;
            }
        }

        return true;
    }


}
