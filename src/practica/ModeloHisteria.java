package practica;

import java.awt.Color;
import java.util.Random;
//Prueba
public class ModeloHisteria {
	private Color[] coloresDisponibles = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA,
			Color.CYAN };
	private int filas;
	private int columnas;
	private Color[][] tablero;
	private Random aleatorio;
	public int getFilas() {return filas;}
	public int getColumnas() {return columnas;}
	public static final Color COLOR_APAGADO = Color.DARK_GRAY;
	

	public void inicializarTableroApagado(int filas, int columnas) {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				tablero[i][j] = COLOR_APAGADO;
			}
		}
	}

	public ModeloHisteria(int filas, int columnas) {
		this.filas = filas;
		this.columnas = columnas;
		tablero = new Color[filas][columnas];
		aleatorio = new Random();
		inicializarTableroApagado(filas, columnas);
	}

	public Color obtenerColorCelda(int fila, int columna) {
		return tablero[fila][columna];
	}

	public void asignarColorCelda(int fila, int columna, Color color) {
		tablero[fila][columna] = color;
	}

	private void comprobarVecinos(int fila, int columna, Color color) {
		boolean coincidenciaEncontrada = false;
		int[][] direcciones = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int[] d : direcciones) {
			int filaVecina = fila + d[0];
			int columnaVecina = columna + d[1];
			if (esValido(filaVecina, columnaVecina)) {
				Color colorVecino = tablero[filaVecina][columnaVecina];
				if (colorVecino.equals(color)) {
					coincidenciaEncontrada = true;
				}
			}
		}
		if (coincidenciaEncontrada) {
			tablero[fila][columna] = COLOR_APAGADO;
			for (int[] d : direcciones) {
				int filaVecina = fila + d[0];
				int columnaVecina = columna + d[1];
				if (esValido(filaVecina, columnaVecina)) {
					tablero[filaVecina][columnaVecina] = COLOR_APAGADO;
				}
			}
		}
	}

	public void celdaPulsada(int fila, int columna) {
		Color nuevoColor = coloresDisponibles[aleatorio.nextInt(coloresDisponibles.length)];
		
		tablero[fila][columna] = nuevoColor;

		comprobarVecinos(fila, columna, nuevoColor);
	}

	private boolean esValido(int fila, int columna) {
		return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
	}
}
