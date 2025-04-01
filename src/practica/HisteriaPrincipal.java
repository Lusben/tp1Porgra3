package practica;

import javax.swing.SwingUtilities;

public class HisteriaPrincipal {
    public static void main(String[] args) {
        // Se invoca en el hilo de eventos para asegurar la correcta actualización de la GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                int filas = 8;      
                int columnas = 8;   
                ModeloHisteria modelo = new ModeloHisteria(filas, columnas);
                VistaHisteria vista = new VistaHisteria(filas, columnas);
                new ControladorHisteria(modelo, vista);
                vista.setVisible(true);
            }
        });
    }
}
