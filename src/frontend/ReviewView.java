package frontend;

import backend.controller.TestController;
import backend.model.Pregunta;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ReviewView extends JFrame {
    private TestController controlador;
    private JLabel lblTexto;
    private JPanel panelRespuestas;
    private JButton btnAnterior, btnSiguiente, btnVolverR;
    private JLabel lblResultado;

    private ButtonGroup grupoOpciones;
    private Map<Integer, Boolean> resultados;

    public ReviewView(TestController controlador) {
        this.controlador = controlador;
        this.resultados = controlador.revisarRespuesta();

        setTitle("Revision de preguntas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        lblTexto = new JLabel("", SwingConstants.CENTER);
        lblTexto.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTexto.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(lblTexto, BorderLayout.NORTH);

        panelRespuestas = new JPanel();
        panelRespuestas.setLayout(new BoxLayout(panelRespuestas, BoxLayout.Y_AXIS));
        panelRespuestas.setBackground(Color.WHITE);
        panelRespuestas.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(panelRespuestas, BorderLayout.CENTER);

        lblResultado = new JLabel("", SwingConstants.CENTER);
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblResultado.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0));
        add(lblResultado, BorderLayout.EAST);


        btnAnterior = crearBoton("Anterior");
        btnSiguiente = crearBoton("Siguiente");
        btnVolverR = crearBoton("Volver al resumen");

        btnAnterior.addActionListener(e -> {
            controlador.retroceder();
            cargarPregunta();
        });

        btnSiguiente.addActionListener(e -> {
            controlador.avanzar();
            cargarPregunta();
        });

        btnVolverR.addActionListener(e -> {
            new ResumeView(controlador).setVisible(true);
            dispose();
        });

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(Color.WHITE);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));
        panelBotones.add(btnAnterior);
        panelBotones.add(btnSiguiente);
        panelBotones.add(btnVolverR);
        add(panelBotones, BorderLayout.SOUTH);

        cargarPregunta();
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

    private void cargarPregunta() {
        Pregunta pregunta = controlador.getPreguntaActual();
        int indice = controlador.getIndiceActual();

        lblTexto.setText("<html><body style='width: 500px'>" + pregunta.getTexto() + "</body></html>");
        panelRespuestas.removeAll();

        grupoOpciones = new ButtonGroup();
        String[] respuestas = pregunta.getRespuestas();
        String respuestaUser = controlador.respuestaActual(indice);

        for (String respuesta : respuestas) {
            JRadioButton radio = new JRadioButton(respuesta);
            radio.setEnabled(false);
            grupoOpciones.add(radio);
            panelRespuestas.add(radio);

            if (respuesta.equalsIgnoreCase(respuestaUser)) {
                radio.setSelected(true);
            }
        }

        boolean correcta = resultados.get(indice);
        lblResultado.setText(correcta ? "Correcta" : "Incorrecta");

        btnAnterior.setEnabled(!controlador.esPrimero());
        btnSiguiente.setEnabled(!controlador.esUltimo());

        revalidate();
        repaint();
    }
}
