package modelo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ModeloHisteria {
    private int filas;
    private int columnas;
    private Color[][] tablero;
    public static final Color COLOR_APAGADO = Color.DARK_GRAY;
    private Color[] coloresDisponibles = { Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.WHITE };
    private Random random;
    private boolean contarAdyacentes;
    private boolean contarDiagonales;
    private String modo;

    public ModeloHisteria(int filas, int columnas, boolean contarAdyacentes, boolean contarDiagonales, String modo) {
        this.filas = filas;
        this.columnas = columnas;
        this.contarAdyacentes = contarAdyacentes;
        this.contarDiagonales = contarDiagonales;
        this.modo = modo;
        tablero = new Color[filas][columnas];
        random = new Random();
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = COLOR_APAGADO;
            }
        }
    }

    public Color getColorCelda(int fila, int columna) {
        return tablero[fila][columna];
    }

    public boolean celdaPulsada(int fila, int columna) {
        Color nuevoColor = asignarColorAleatorio(fila, columna);
        tablero[fila][columna] = nuevoColor;
        if (revisarColisiones(fila, columna, nuevoColor)) {
            reiniciarCeldas(fila, columna);
            return true;
        }
        return false;
    }

    private Color asignarColorAleatorio(int fila, int columna) {
        List<Color> coloresVecinos = obtenerColoresVecinos(fila, columna);
        switch (modo) {
            case "facil":
                return asignarColorFacil(coloresVecinos);
            case "medio":
                return coloresDisponibles[random.nextInt(coloresDisponibles.length)];
            case "dificil":
                if (!coloresVecinos.isEmpty() && random.nextDouble() < 0.7) {
                    return coloresVecinos.get(random.nextInt(coloresVecinos.size()));
                }
                return coloresDisponibles[random.nextInt(coloresDisponibles.length)];
            default:
                return coloresDisponibles[random.nextInt(coloresDisponibles.length)];
        }
    }

    private Color asignarColorFacil(List<Color> coloresVecinos) {
        List<Color> opciones = new ArrayList<>();
        for (Color c : coloresDisponibles) {
            if (!coloresVecinos.contains(c)) {
                opciones.add(c);
            }
        }
        if (!opciones.isEmpty()) {
            return opciones.get(random.nextInt(opciones.size()));
        }
        return coloresDisponibles[random.nextInt(coloresDisponibles.length)];
    }

    private List<Color> obtenerColoresVecinos(int fila, int columna) {
        List<Color> lista = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue;
                int nuevaFila = fila + dx;
                int nuevaColumna = columna + dy;
                if (esCeldaValida(nuevaFila, nuevaColumna) && debeContarVecino(dx, dy)) {
                    Color c = tablero[nuevaFila][nuevaColumna];
                    if (!c.equals(COLOR_APAGADO)) {
                        lista.add(c);
                    }
                }
            }
        }
        return lista;
    }

    private boolean debeContarVecino(int dx, int dy) {
        if (Math.abs(dx) + Math.abs(dy) == 1) {
            return contarAdyacentes;
        }
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            return contarDiagonales;
        }
        return false;
    }

    private boolean revisarColisiones(int fila, int columna, Color color) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue;
                int nfila = fila + dx;
                int ncol = columna + dy;
                if (esCeldaValida(nfila, ncol) && debeContarVecino(dx, dy)) {
                    if (tablero[nfila][ncol].equals(color)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void reiniciarCeldas(int fila, int columna) {
        tablero[fila][columna] = COLOR_APAGADO;
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0)
                    continue;
                int nuevaFila = fila + dx;
                int nuevaColumna = columna + dy;
                if (esCeldaValida(nuevaFila, nuevaColumna) && debeContarVecino(dx, dy)) {
                    tablero[nuevaFila][nuevaColumna] = COLOR_APAGADO;
                }
            }
        }
    }

    private boolean esCeldaValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    public boolean isBoardComplete() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].equals(COLOR_APAGADO)) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
