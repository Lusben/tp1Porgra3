package practica;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ModeloHisteria representa la lógica del juego.
 * Gestiona el tablero, asigna colores aleatorios a las celdas y valida colisiones.
 */
public class ModeloHisteria {
    private int filas;
    private int columnas;
    private Color[][] tablero;
    public static final Color COLOR_APAGADO = Color.DARK_GRAY;
    private Color[] coloresDisponibles = { Color.RED, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.WHITE };
    private Random random;

    // Opciones para contar vecinos
    private boolean contarAdyacentes;
    private boolean contarDiagonales;

    // Modo de juego: "facil", "medio" o "dificil"
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

    /**
     * Inicializa el tablero poniendo todas las celdas en estado "apagado".
     */
    private void inicializarTablero() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = COLOR_APAGADO;
            }
        }
    }

    /**
     * Retorna el color actual de la celda en la posición especificada.
     */
    public Color getColorCelda(int fila, int columna) {
        return tablero[fila][columna];
    }

    /**
     * Procesa el clic en una celda:
     * - Asigna un nuevo color aleatorio.
     * - Verifica si hay colisión con los vecinos.
     * - En caso de colisión, reinicia (apaga) la celda y solo los vecinos permitidos.
     *
     * @return true si ocurrió una colisión; false en caso contrario.
     */
    public boolean celdaPulsada(int fila, int columna) {
        Color nuevoColor = asignarColorAleatorio(fila, columna);
        tablero[fila][columna] = nuevoColor;
        if (revisarColisiones(fila, columna, nuevoColor)) {
            reiniciarCeldas(fila, columna);
            return true;
        }
        return false;
    }

    /**
     * Asigna un color aleatorio según el modo de juego y colores en las celdas vecinas.
     */
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

    /**
     * Ayuda a asignar un color en modo "facil": se evita elegir colores presentes en vecinos.
     */
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

    /**
     * Obtiene los colores de las celdas vecinas permitidas según la configuración.
     */
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

    /**
     * Verifica si se debe contar el vecino en la posición relativa (dx, dy).
     * Retorna true si cumple la condición de adyacencia o diagonal según la configuración.
     */
    private boolean debeContarVecino(int dx, int dy) {
        // Vecino adyacente: suma de las distancias absolutas igual a 1.
        if (Math.abs(dx) + Math.abs(dy) == 1) {
            return contarAdyacentes;
        }
        // Vecino diagonal: cada uno debe ser 1.
        if (Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            return contarDiagonales;
        }
        return false;
    }

    /**
     * Revisa si existe alguna colisión entre la celda recién coloreada y sus vecinos permitidos.
     */
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

    /**
     * Reinicia (apaga) la celda central y solo aquellos vecinos que cumplan la condición.
     */
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

    /**
     * Verifica que la celda (fila, columna) esté dentro de los límites del tablero.
     */
    private boolean esCeldaValida(int fila, int columna) {
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }

    /**
     * Retorna true si todas las celdas del tablero no están en estado "apagado".
     */
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

    // Getters para las dimensiones del tablero.
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
