package frontend;

import backend.controller.TestController;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ResumeView extends JFrame {

    private TestController controlador;

    public ResumeView(TestController controlador){
        this.controlador = controlador;

        setTitle("Resumen de los resultados");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Resumen de la prueba", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JTextArea areaResumen = new JTextArea();
        areaResumen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        areaResumen.setEditable(false);
        areaResumen.setBackground(new Color(245, 245, 245));
        areaResumen.setMargin(new Insets(15, 15, 15, 15));

        StringBuilder resumen = new StringBuilder();

        resumen.append("Porcentaje de respuestas correctas por tipo:\n");
        Map<String, Double> porTipo = controlador.porcentajePorTipo();
        for (String tipo : porTipo.keySet()) {
            resumen.append(" - ").append(tipo).append(": ").append(String.format("%.2f", porTipo.get(tipo))).append("%\n");
        }

        resumen.append("\n Porcentaje de respuestas correctas por nivel en la Taxonomia de Bloom:\n");
        Map<String, Double> porNivel = controlador.porcentajePorNivel();
        for (String nivel : porTipo.keySet()) {
            resumen.append(" - ").append(nivel).append(": ").append(String.format("%.2f", porTipo.get(nivel))).append("%\n");
        }

        areaResumen.setText(resumen.toString());

        JScrollPane scrollPane = new JScrollPane(areaResumen);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnRevisar = crearBoton("Revisar respuestas");
        btnRevisar.addActionListener(e -> {
            new ReviewView(controlador).setVisible(true);
            dispose();
        });

        add(btnRevisar, BorderLayout.SOUTH);
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
