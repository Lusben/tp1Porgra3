package practica;

import javax.swing.*;
import java.awt.*;

/**
 * ConfiguracionJuego muestra una ventana de diálogo que permite al usuario
 * seleccionar la dimensión del tablero y el modo de juego.
 */
public class ConfiguracionJuego extends JDialog {
    private int dimension;
    private String modoSeleccionado;
    private boolean confirmado = false;

    private JSpinner spinnerDimension;
    private JRadioButton rbFacil, rbMedio, rbDificil;

    public ConfiguracionJuego(Frame parent) {
        super(parent, "Configuración del Juego", true);
        initComponentes();
        pack();
        setLocationRelativeTo(parent);
    }

    /**
     * Inicializa todos los componentes de la ventana y los organiza en el layout.
     */
    private void initComponentes() {
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(crearPanelBienvenida());
        panelPrincipal.add(crearPanelDimension());
        panelPrincipal.add(crearPanelModo());
        panelPrincipal.add(crearPanelBotonAceptar());
        getContentPane().add(panelPrincipal);
    }

    /**
     * Crea el panel que muestra el mensaje de bienvenida.
     */
    private JPanel crearPanelBienvenida() {
        String texto = "El objetivo del juego es colorear completamente una grilla, pero evitando que dos celdas vecinas tengan el mismo color. "
                + "Inicialmente, todas las celdas están sin colorear. En cada turno, el jugador hace click sobre una celda, "
                + "y este click tiene el efecto de cambiar el color de esa casilla a uno aleatorio de entre 6 posibles. "
                + "La dificultad consiste en que si este nuevo color coincide con el de alguna casilla vecina, la casilla "
                + "cambiada y todas sus vecinas se apagarán!";
        JTextArea taBienvenida = new JTextArea(texto);
        taBienvenida.setWrapStyleWord(true);
        taBienvenida.setLineWrap(true);
        taBienvenida.setEditable(false);
        taBienvenida.setFocusable(false);
        taBienvenida.setOpaque(false);
        taBienvenida.setMargin(new Insets(10, 10, 10, 10));

        JPanel panelBienvenida = new JPanel(new BorderLayout());
        panelBienvenida.add(taBienvenida, BorderLayout.CENTER);
        return panelBienvenida;
    }

    /**
     * Crea el panel para seleccionar la dimensión del tablero.
     */
    private JPanel crearPanelDimension() {
        JPanel panelDimension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDimension.setBorder(BorderFactory.createTitledBorder("Dimensiones del tablero (n×n)"));
        spinnerDimension = new JSpinner(new SpinnerNumberModel(5, 3, 10, 1));
        panelDimension.add(new JLabel("Tamaño: "));
        panelDimension.add(spinnerDimension);
        return panelDimension;
    }

    /**
     * Crea el panel para seleccionar el modo de juego.
     */
    private JPanel crearPanelModo() {
        JPanel panelModo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelModo.setBorder(BorderFactory.createTitledBorder("Modo de juego"));
        rbFacil = new JRadioButton("Fácil");
        rbMedio = new JRadioButton("Medio");
        rbDificil = new JRadioButton("Difícil");
        ButtonGroup grupoModo = new ButtonGroup();
        grupoModo.add(rbFacil);
        grupoModo.add(rbMedio);
        grupoModo.add(rbDificil);
        rbFacil.setSelected(true); // Por defecto, modo Fácil.
        panelModo.add(rbFacil);
        panelModo.add(rbMedio);
        panelModo.add(rbDificil);
        return panelModo;
    }

    /**
     * Crea el panel que contiene el botón para confirmar y aceptar la configuración.
     */
    private JPanel crearPanelBotonAceptar() {
        JPanel panelBoton = new JPanel();
        JButton btnAceptar = new JButton("Iniciar Juego");
        btnAceptar.addActionListener(e -> onAceptar());
        panelBoton.add(btnAceptar);
        return panelBoton;
    }

    /**
     * Procesa la acción del botón de aceptar.
     */
    private void onAceptar() {
        dimension = (Integer) spinnerDimension.getValue();
        if (rbFacil.isSelected()) {
            modoSeleccionado = "facil";
        } else if (rbMedio.isSelected()) {
            modoSeleccionado = "medio";
        } else {
            modoSeleccionado = "dificil";
        }
        confirmado = true;
        dispose();
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public int getDimension() {
        return dimension;
    }

    public String getModoSeleccionado() {
        return modoSeleccionado;
    }
}
