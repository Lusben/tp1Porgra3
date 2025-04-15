package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaNombreJugador extends JFrame {
    private String nombreJugador;

    public VentanaNombreJugador() {
        setTitle("Nombre del Jugador");
        setSize(250, 125);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel lblPregunta = new JLabel("¿Cuál es tu nombre?", JLabel.CENTER);
        lblPregunta.setFont(new Font("Arial", Font.PLAIN, 15));
        JTextField txtNombre = new JTextField();
        JButton btnContinuar = new JButton("Siguiente");
        btnContinuar.addActionListener(e -> onContinuar(txtNombre));
        panel.add(lblPregunta, BorderLayout.NORTH);
        panel.add(txtNombre, BorderLayout.CENTER);
        panel.add(btnContinuar, BorderLayout.SOUTH);
        add(panel);
    }

    private void onContinuar(JTextField txtNombre) {
        nombreJugador = txtNombre.getText().trim();
        if (nombreJugador.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa tu nombre.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        VentanaSeleccionNiveles ventanaSeleccion = new VentanaSeleccionNiveles(nombreJugador);
        ventanaSeleccion.setVisible(true);
        dispose();
    }

    public String getNombreJugador() {
        return nombreJugador;
    }
}
