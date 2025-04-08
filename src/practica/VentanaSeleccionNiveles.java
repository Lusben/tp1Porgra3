package practica;

import javax.swing.*;
import java.awt.*;

public class VentanaSeleccionNiveles extends JFrame {
    private int dimension;
    private String modoSeleccionado;
    private String nombreJugador;

    private JSpinner spinnerDimension;
    private JRadioButton rbFacil, rbMedio, rbDificil;
    private JCheckBox chckbxDiagonales;

    public VentanaSeleccionNiveles(String nombreJugador) {
        this.nombreJugador = nombreJugador;
        setTitle("Seleccionar Nivel");
        setSize(400, 400); // Se aumenta el tamaño para acomodar la nueva opción
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        // Panel superior para saludar al jugador
        JPanel panelSaludo = new JPanel();
        JLabel lblSaludo = new JLabel("¡Bienvenido, " + nombreJugador + "!");
        lblSaludo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSaludo.add(lblSaludo);

        // Panel para seleccionar la dimensión del tablero
        JPanel panelDimension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDimension.setBorder(BorderFactory.createTitledBorder("Dimensiones del tablero (n×n)"));
        spinnerDimension = new JSpinner(new SpinnerNumberModel(5, 3, 15, 1));
        panelDimension.add(new JLabel("Tamaño: "));
        panelDimension.add(spinnerDimension);

        // Panel para elegir el modo de juego con descripciones breves
        JPanel panelModo = new JPanel();
        panelModo.setLayout(new BoxLayout(panelModo, BoxLayout.Y_AXIS));
        panelModo.setBorder(BorderFactory.createTitledBorder("Modo de juego"));

        // Panel para la opción "Fácil"
        JPanel panelFacil = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbFacil = new JRadioButton("Fácil");
        JLabel lblFacilDesc = new JLabel(" - Evita repetir colores en vecinos si es posible");
        panelFacil.add(rbFacil);
        panelFacil.add(lblFacilDesc);

        // Panel para la opción "Medio"
        JPanel panelMedio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMedio = new JRadioButton("Medio");
        JLabel lblMedioDesc = new JLabel(" - Selección de color completamente aleatoria");
        panelMedio.add(rbMedio);
        panelMedio.add(lblMedioDesc);

        // Panel para la opción "Difícil"
        JPanel panelDificil = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbDificil = new JRadioButton("Difícil");
        JLabel lblDificilDesc = new JLabel(" - Alta probabilidad de colisión (70% con color de vecinos)");
        panelDificil.add(rbDificil);
        panelDificil.add(lblDificilDesc);

        ButtonGroup grupoModo = new ButtonGroup();
        grupoModo.add(rbFacil);
        grupoModo.add(rbMedio);
        grupoModo.add(rbDificil);
        rbFacil.setSelected(true);

        panelModo.add(panelFacil);
        panelModo.add(panelMedio);
        panelModo.add(panelDificil);

        // Panel para elegir si se consideran los vecinos diagonales
        JPanel panelDiagonales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDiagonales.setBorder(BorderFactory.createTitledBorder("Opciones de Vecindad"));
        chckbxDiagonales = new JCheckBox("Incluir diagonales");
        chckbxDiagonales.setSelected(true); // Valor predeterminado
        panelDiagonales.add(chckbxDiagonales);

        // Botón para iniciar el juego
        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(e -> onIniciarJuego());

        // Organización general de los paneles en el frame
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelSaludo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelDimension);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelModo);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(panelDiagonales);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(btnIniciar);

        add(panelPrincipal);
    }

    private void onIniciarJuego() {
        // Se obtiene la dimensión seleccionada del tablero
        dimension = (Integer) spinnerDimension.getValue();

        // Se asigna el modo según la opción marcada
        if (rbFacil.isSelected()) {
            modoSeleccionado = "facil";
        } else if (rbMedio.isSelected()) {
            modoSeleccionado = "medio";
        } else {
            modoSeleccionado = "dificil";
        }

        // Se obtiene la opción para considerar diagonales
        boolean incluirDiagonales = chckbxDiagonales.isSelected();

        // Se crea el modelo utilizando la opción de diagonales seleccionada
        ModeloHisteria modelo = new ModeloHisteria(dimension, dimension, true, incluirDiagonales, modoSeleccionado);
        VistaHisteria vista = new VistaHisteria(dimension, dimension);
        new ControladorHisteria(modelo, vista);
        vista.setVisible(true);
        dispose();
    }
}

