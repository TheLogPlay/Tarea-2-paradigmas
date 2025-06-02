package frontend;

import backend.controller.TestController;
import backend.model.Pregunta;
import backend.util.CargaPregunta;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class HomeView extends JFrame {
    private JLabel lblCantidadPreguntas;
    private JLabel lblTiempo;
    private JButton btnIniciar;
    private TestController controlador;

    public HomeView() {
        setTitle("Test generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Cargar preguntas de la prueba", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.WHITE);
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        lblCantidadPreguntas = new JLabel("Cantidad de preguntas: - ");
        lblTiempo = new JLabel("Tiempo total: - ");

        for (JLabel label : new JLabel[]{lblCantidadPreguntas, lblTiempo}) {
            label.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelCentro.add(label);
            panelCentro.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton btnCargar = crearBoton("Cargar archivo csv");
        btnCargar.addActionListener(e -> cargarArchivo());
        panelCentro.add(btnCargar);
        btnCargar.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(panelCentro, BorderLayout.CENTER);

        /*
        panelCentro.add(lblCantidadPreguntas);
        panelCentro.add(lblTiempo);
        */

        btnIniciar = crearBoton("Iniciar evaluacion");
        btnIniciar.setEnabled(false);
        btnIniciar.addActionListener(e -> iniciarprueba());
        btnIniciar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel panelSur = new JPanel();
        panelSur.setBackground(Color.WHITE);
        panelSur.add(btnIniciar);

        add(panelSur, BorderLayout.SOUTH);

        /*add(btnIniciar,BorderLayout.SOUTH );*/
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(33, 150, 243));
        boton.setForeground(Color.WHITE);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
    }

    private void cargarArchivo() {
        JFileChooser chooser = new JFileChooser();
        int opcion = chooser.showOpenDialog(this);

        if (opcion == JFileChooser.APPROVE_OPTION) {
            File archivo = chooser.getSelectedFile();
            try {
                List<Pregunta> preguntas = CargaPregunta.cargarDesdeArchivo(archivo);
                controlador = new TestController(preguntas);
                lblCantidadPreguntas.setText("Cantidad de Preguntas: " + preguntas.size());
                lblTiempo.setText("Tiempo total: " + controlador.getTiempoTotal() + " segundos");
                btnIniciar.setEnabled(true);
                JOptionPane.showMessageDialog(this, "Archivo cargado correctamente.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al cargar el archivo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void iniciarprueba() {
        new TestView(controlador).setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeView().setVisible(true));
    }
}
