package practica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * VistaHisteria se encarga de la interfaz gráfica del juego.
 * Crea la ventana del tablero y un panel superior para el contador.
 */
public class VistaHisteria extends JFrame {
    private int filas;
    private int columnas;
    private JButton[][] botones;
    private JLabel lblIntentos;

    public VistaHisteria(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        botones = new JButton[filas][columnas];
        inicializarInterfaz();
    }

    /**
     * Inicializa la interfaz dividiendo la ventana en panel superior (contador)
     * y panel central (tablero de celdas).
     */
    private void inicializarInterfaz() {
        setTitle("Juego Histeria Unificado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelTablero(), BorderLayout.CENTER);
    }

    /**
     * Crea el panel superior que muestra el contador de intentos.
     */
    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblIntentos = new JLabel("Intentos: 0");
        lblIntentos.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblIntentos);
        return panelSuperior;
    }

    /**
     * Crea el panel central con una cuadrícula de botones que representan las celdas.
     */
    private JPanel crearPanelTablero() {
        JPanel panelTablero = new JPanel(new GridLayout(filas, columnas));
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton boton = crearBotonCelda(i, j);
                botones[i][j] = boton;
                panelTablero.add(boton);
            }
        }
        return panelTablero;
    }

    /**
     * Crea un botón para una celda, configurando su color, propiedades y ubicación.
     */
    private JButton crearBotonCelda(int fila, int columna) {
        JButton boton = new JButton();
        boton.setBackground(ModeloHisteria.COLOR_APAGADO);
        boton.setFocusPainted(false);
        // Se guardan las posiciones para poder identificarlas en el controlador
        boton.putClientProperty("fila", fila);
        boton.putClientProperty("columna", columna);
        return boton;
    }

    /**
     * Agrega el ActionListener a cada botón del tablero.
     */
    public void agregarEscuchaCelda(ActionListener escucha) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].addActionListener(escucha);
            }
        }
    }

    /**
     * Actualiza la vista del tablero asignando a cada botón el color de la celda correspondiente.
     */
    public void actualizarTablero(ModeloHisteria modelo) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].setBackground(modelo.getColorCelda(i, j));
            }
        }
    }

    /**
     * Actualiza la etiqueta del contador de intentos.
     */
    public void actualizarIntentos(int intentos) {
        lblIntentos.setText("Intentos: " + intentos);
    }

    /**
     * Muestra un mensaje de victoria cuando se completa el tablero.
     */
    public void mostrarMensajeVictoria() {
        JOptionPane.showMessageDialog(this, "¡Felicidades, has completado el tablero!",
                "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }
}
