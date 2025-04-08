package practica;

import javax.swing.SwingUtilities;

/**
 * Punto de entrada de la aplicación. Inicia la ventana de bienvenida.
 */
public class HisteriaApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaBienvenida bienvenida = new VentanaBienvenida();
            bienvenida.setVisible(true);
        });
    }
}
