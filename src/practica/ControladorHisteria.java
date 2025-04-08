package practica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ControladorHisteria conecta la vista y el modelo.
 * Se encarga de procesar el clic en una celda, actualizar el modelo y refrescar la vista.
 */
public class ControladorHisteria implements ActionListener {
    private ModeloHisteria modelo;
    private VistaHisteria vista;
    private int intentos;

    public ControladorHisteria(ModeloHisteria modelo, VistaHisteria vista) {
        this.modelo = modelo;
        this.vista = vista;
        intentos = 0;
        vista.agregarEscuchaCelda(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        int fila = (int) boton.getClientProperty("fila");
        int columna = (int) boton.getClientProperty("columna");

        // Se procesa el clic sobre la celda para cambiar su color aleatorio y verificar colisiones.
        boolean colision = modelo.celdaPulsada(fila, columna);
        intentos++;
        vista.actualizarIntentos(intentos);
        vista.actualizarTablero(modelo);

        if (modelo.isBoardComplete()) {
            vista.mostrarMensajeVictoria();
            System.exit(0);
        }
    }
}
