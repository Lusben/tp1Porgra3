package vista;

import javax.swing.*;

import modelo.ModeloHisteria;

import java.awt.*;
import java.awt.event.ActionListener;

public class VistaHisteria extends JFrame {
    private int filas;
    private int columnas;
    private JButton[][] botones;
    private JLabel lblIntentos;
    private String nombreJugador;
    private int intentos;

    public VistaHisteria(int filas, int columnas, String nombreJugador) {
        this.filas = filas;
        this.columnas = columnas;
        this.nombreJugador = nombreJugador;
        botones = new JButton[filas][columnas];
        inicializarInterfaz();
    }

    private void inicializarInterfaz() {
        setTitle("Juego Histeria Unificado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelTablero(), BorderLayout.CENTER);
    }

    private JPanel crearPanelSuperior() {
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblIntentos = new JLabel("Intentos: 0");
        lblIntentos.setFont(new Font("Arial", Font.BOLD, 16));
        panelSuperior.add(lblIntentos);
        return panelSuperior;
    }

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

    private JButton crearBotonCelda(int fila, int columna) {
        JButton boton = new JButton();
        boton.setBackground(ModeloHisteria.COLOR_APAGADO);
        boton.setFocusPainted(false);
        boton.putClientProperty("fila", fila);
        boton.putClientProperty("columna", columna);
        return boton;
    }

    public void agregarEscuchaCelda(ActionListener escucha) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].addActionListener(escucha);
            }
        }
    }

    public void actualizarTablero(ModeloHisteria modelo) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j].setBackground(modelo.getColorCelda(i, j));
            }
        }
    }

    public void actualizarIntentos(int intentos) {
        this.intentos = intentos;
        lblIntentos.setText("Intentos: " + intentos);
    }

    public void mostrarMensajeVictoria() {
        JOptionPane.showMessageDialog(this, "¡Felicidades, " + nombreJugador + "! Has completado el tablero en " + intentos + " intentos!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
    }
}
