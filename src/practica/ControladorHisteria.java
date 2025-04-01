package practica;

//HisteriaController.java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class ControladorHisteria implements ActionListener {
    private ModeloHisteria modelo;
    private VistaHisteria vista;
    
    public ControladorHisteria(ModeloHisteria modelo, VistaHisteria vista) {
        this.modelo = modelo;
        this.vista = vista;
        // Se añaden los listeners a cada celda del tablero
        vista.agregarEscuchaCelda(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton boton = (JButton) e.getSource();
        // Obtener las coordenadas almacenadas en las propiedades del botón
        int fila = (int) boton.getClientProperty("fila");
        int columna = (int) boton.getClientProperty("columna");
        // Invocar la lógica del modelo para gestionar el clic en la celda
        modelo.celdaPulsada(fila, columna);
        // Actualizar la vista para reflejar los cambios
        vista.actualizarTablero(modelo);
    }
}

