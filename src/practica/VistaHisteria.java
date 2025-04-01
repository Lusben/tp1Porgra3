package practica;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VistaHisteria extends JFrame {
    private int filas;
    private int columnas;
    private JButton[][] botones;
    
    public VistaHisteria(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        botones = new JButton[filas][columnas];
        inicializarInterfaz();
    }
    
    private void inicializarInterfaz() {
        setTitle("Juego Histeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);
        setLocationRelativeTo(null);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(filas, columnas));
        
        // Crear botones para cada celda del tablero
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                JButton boton = new JButton();
                boton.setBackground(ModeloHisteria.COLOR_APAGADO);
                boton.setFocusPainted(false);
                boton.setBorderPainted(true);
                // Guardar las coordenadas de la celda en las propiedades del botón
                boton.putClientProperty("fila", i);
                boton.putClientProperty("columna", j);
                botones[i][j] = boton;
                panel.add(boton);
            }
        }
        
        add(panel);
    }
    
    // Permite que el controlador asocie un ActionListener a cada botón
    public void agregarEscuchaCelda(ActionListener escucha) {
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                botones[i][j].addActionListener(escucha);
            }
        }
    }
    

    public void actualizarTablero(ModeloHisteria modelo) {
        for (int i = 0; i < filas; i++){
            for (int j = 0; j < columnas; j++){
                botones[i][j].setBackground(modelo.obtenerColorCelda(i, j));
            }
        }
    }
}
