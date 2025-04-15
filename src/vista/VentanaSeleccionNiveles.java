package vista;

import javax.swing.*;

import controlador.ControladorHisteria;
import modelo.ModeloHisteria;

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
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel panelSaludo = new JPanel();
        JLabel lblSaludo = new JLabel("¡Bienvenido, " + nombreJugador + "!");
        lblSaludo.setFont(new Font("Arial", Font.BOLD, 18));
        panelSaludo.add(lblSaludo);
        JPanel panelDimension = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDimension.setBorder(BorderFactory.createTitledBorder("Dimensiones del tablero (n×n)"));
        spinnerDimension = new JSpinner(new SpinnerNumberModel(5, 3, 15, 1));
        panelDimension.add(new JLabel("Tamaño: "));
        panelDimension.add(spinnerDimension);
        JPanel panelModo = new JPanel();
        panelModo.setLayout(new BoxLayout(panelModo, BoxLayout.Y_AXIS));
        panelModo.setBorder(BorderFactory.createTitledBorder("Modo de juego"));
        JPanel panelFacil = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbFacil = new JRadioButton("Fácil");
        JLabel lblFacilDesc = new JLabel(" - Evita repetir colores en vecinos si es posible");
        panelFacil.add(rbFacil);
        panelFacil.add(lblFacilDesc);
        JPanel panelMedio = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMedio = new JRadioButton("Medio");
        JLabel lblMedioDesc = new JLabel(" - Selección de color completamente aleatoria");
        panelMedio.add(rbMedio);
        panelMedio.add(lblMedioDesc);
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
        JPanel panelDiagonales = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelDiagonales.setBorder(BorderFactory.createTitledBorder("Opciones de Vecindad"));
        chckbxDiagonales = new JCheckBox("Incluir diagonales");
        chckbxDiagonales.setSelected(true);
        panelDiagonales.add(chckbxDiagonales);
        JButton btnIniciar = new JButton("Iniciar Juego");
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciar.addActionListener(e -> onIniciarJuego());
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
        dimension = (Integer) spinnerDimension.getValue();
        if (rbFacil.isSelected()) {
            modoSeleccionado = "facil";
        } else if (rbMedio.isSelected()) {
            modoSeleccionado = "medio";
        } else {
            modoSeleccionado = "dificil";
        }
        boolean incluirDiagonales = chckbxDiagonales.isSelected();
        ModeloHisteria modelo = new ModeloHisteria(dimension, dimension, true, incluirDiagonales, modoSeleccionado);
        VistaHisteria vista = new VistaHisteria(dimension, dimension, nombreJugador);
        new ControladorHisteria(modelo, vista);
        vista.setVisible(true);
        dispose();
    }
}
