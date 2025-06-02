package frontend;

import backend.controller.TestController;
import backend.model.Pregunta;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;

public class TestView extends JFrame {
    private TestController controlador;

    private JLabel lblTexto;
    private JPanel panelRespuestas;
    private JButton btnAnterior;
    private JButton btnSiguiente;

    private ButtonGroup grupoRespuestas;

    public TestView(TestController controlador) {
        this.controlador = controlador;

        setTitle("Prueba en progreso!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        lblTexto = new JLabel("", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTexto.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(lblTexto, BorderLayout.NORTH);

        panelRespuestas = new JPanel();
        panelRespuestas.setLayout(new BoxLayout(panelRespuestas, BoxLayout.Y_AXIS));
        panelRespuestas.setBackground(Color.WHITE);
        panelRespuestas.setBorder(BorderFactory.createEmptyBorder(10, 60, 10, 60));
        add(panelRespuestas, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnAnterior = crearBoton("Volver atras");
        btnSiguiente = crearBoton("Avanzar");

        btnAnterior.addActionListener(e -> {
            guardarRespuesta();
            controlador.retroceder();
            cargarPregunta();
        });

        btnSiguiente.addActionListener(e -> {
            guardarRespuesta();
            if (controlador.esUltimo()){
                mostrarResultados();
            } else {
                controlador.avanzar();
                cargarPregunta();
            }
        });

        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        add(panelBotones, BorderLayout.SOUTH);

        cargarPregunta();
    }

    private void cargarPregunta() {
        Pregunta pregunta = controlador.getPreguntaActual();
        lblTexto.setText("<html><body style='width: 500px'>" + pregunta.getTexto() + "</body></html>");
        panelRespuestas.removeAll();

        grupoRespuestas = new ButtonGroup();
        String[] respuestas = pregunta.getRespuestas();

        String respuestaAnt = controlador.respuestaActual(controlador.getIndiceActual());

        for (String respuesta : respuestas) {
            JRadioButton radio = new JRadioButton(respuesta);
            grupoRespuestas.add(radio);
            panelRespuestas.add(radio);

            if (respuestaAnt != null && respuestaAnt.equalsIgnoreCase(respuesta)) {
                radio.setSelected(true);
            }
        }

        btnAnterior.setEnabled(!controlador.esPrimero());
        btnSiguiente.setText(controlador.esUltimo() ? "Entregar prueba" : "Avanzar");

        revalidate();
        repaint();
    }

    private void guardarRespuesta() {
        String seleccionada = null;
        Enumeration<AbstractButton> botones = grupoRespuestas.getElements();
        while (botones.hasMoreElements()) {
            AbstractButton b = botones.nextElement();
            if (b.isSelected()) {
                seleccionada = b.getText();
                break;
            }
        }

        if (seleccionada != null) {
            controlador.guardarRespuesta(seleccionada);
        }
    }

    private void mostrarResultados() {
        new ResumeView(controlador).setVisible(true);
        dispose();
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setBackground(new Color(33, 150, 243));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        return boton;
    }
}
