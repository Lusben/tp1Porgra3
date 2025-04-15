package main;

import javax.swing.SwingUtilities;

import vista.VentanaBienvenida;

public class HisteriaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaBienvenida bienvenida = new VentanaBienvenida();
            bienvenida.setVisible(true);
        });
    }
}
