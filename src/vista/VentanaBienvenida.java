package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaBienvenida extends JFrame {
    public VentanaBienvenida() {
        setTitle("Bienvenida");
        setBounds(100, 150, 502, 407);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        configurarInterfaz();
    }

    private void configurarInterfaz() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().add(Box.createVerticalStrut(15));
        JPanel panelCentro = crearPanelCentro();
        JPanel panelBotones = crearPanelBotones();
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.add(panelCentro);
        panelPrincipal.add(panelBotones);
        add(panelPrincipal);
    }

    private JPanel crearPanelCentro() {
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.add(Box.createVerticalStrut(20));
        JLabel lblBienvenida = new JLabel("Bienvenido a Histeria", JLabel.CENTER);
        lblBienvenida.setFont(new Font("Times New Roman", Font.ITALIC, 40));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(lblBienvenida);
        panelCentro.add(Box.createVerticalStrut(10));
        JLabel lblDescripcion = new JLabel(crearDescripcionHtml());
        lblDescripcion.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        lblDescripcion.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblDescripcion.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCentro.add(lblDescripcion);
        panelCentro.add(Box.createVerticalStrut(20));
        return panelCentro;
    }

    private String crearDescripcionHtml() {
        String texto = "El objetivo del juego es colorear completamente una grilla, pero evitando que dos celdas vecinas tengan el mismo color. " +
                "Inicialmente, todas las celdas están sin colorear. En cada turno, el jugador hace click sobre una celda, " +
                "y este click tiene el efecto de cambiar el color de esa casilla a uno aleatorio de entre 6 posibles. " +
                "La dificultad consiste en que si este nuevo color coincide con el de alguna celda vecina, la celda cambiada y todas sus vecinas se apagarán!";
        return "<html><div style='text-align: justify;'>" + texto + "</div></html>";
    }

    private JPanel crearPanelBotones() {
        JButton btnIntentar = new JButton("¡Quiero intentarlo!");
        btnIntentar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIntentar.addActionListener(e -> {
            VentanaNombreJugador ventanaNombre = new VentanaNombreJugador();
            ventanaNombre.setVisible(true);
            dispose();
        });
        JButton btnSalir = new JButton("Salir");
        btnSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSalir.addActionListener(e -> System.exit(0));
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panelBotones.add(btnIntentar);
        panelBotones.add(btnSalir);
        return panelBotones;
    }
}
