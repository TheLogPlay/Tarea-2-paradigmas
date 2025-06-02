package backend.controller;

import backend.model.*;

import java.util.*;

public class TestController {

    private List<Pregunta> preguntas;
    private Map<Integer, String> respuestasUser;
    private int indiceActual;

    public TestController(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
        this.respuestasUser = new HashMap<>();
        this.indiceActual = 0;
    }

    public Pregunta getPreguntaActual() {
        return preguntas.get(indiceActual);
    }

    public int getIndiceActual() {
        return indiceActual;
    }

    public int getTotalPreguntas() {
        return preguntas.size();
    }

    public void avanzar() {
        if ( indiceActual < preguntas.size() - 1) {
            indiceActual++;
        }
    }

    public void retroceder() {
        if (indiceActual > 0) {
            indiceActual--;
        }
    }

    public boolean esPrimero() {
        return indiceActual == 0;
    }

    public boolean esUltimo() {
        return indiceActual == preguntas.size() - 1;
    }

    public void guardarRespuesta(String respuesta) {
        respuestasUser.put(indiceActual, respuesta);
    }

    public String respuestaActual(int indice) {
        return respuestasUser.getOrDefault(indice, null);
    }

    public Map<Integer, Boolean> revisarRespuesta() {
        Map<Integer, Boolean> resultados = new HashMap<>();
        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String respuesta = respuestasUser.get(i);
            if (respuesta != null) {
                resultados.put(i, pregunta.esCorrecta(respuesta));
            }else {
                resultados.put(i, false);
            }
        }
        return resultados;
    }

    public int getTiempoTotal() {
        return preguntas.stream().mapToInt(Pregunta::getTiempo).sum();
    }

    public Map<String, Integer> cantPorTipo() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Pregunta pregunta : preguntas) {
            conteo.put(pregunta.getTipo(), conteo.getOrDefault(pregunta.getTipo(), 0) + 1);
        }
        return conteo;
    }

    public Map<String, Integer> cantPorNivel() {
        Map<String, Integer> conteo = new HashMap<>();
        for (Pregunta pregunta : preguntas) {
            String nivel = pregunta.getNivel();
            conteo.put(nivel, conteo.getOrDefault(nivel, 0) + 1);
        }
        return conteo;
    }

    public Map<String, Double> porcentajePorTipo() {
        Map<String, Integer> correctas = new HashMap<>();
        Map<String, Integer> totales = new HashMap<>();

        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String tipo = pregunta.getTipo();
            boolean correcta = pregunta.esCorrecta(respuestasUser.get(i));

            totales.put(tipo, totales.getOrDefault(tipo, 0) + 1);
            if (correcta) correctas.put(tipo, correctas.getOrDefault(tipo, 0) + 1);
        }

        Map<String, Double> porcentajes = new HashMap<>();
        for (String tipo : totales.keySet()) {
            int total = totales.get(tipo);
            int correct = correctas.getOrDefault(tipo, 0);
            porcentajes.put(tipo, total == 0 ? 0.0 : (correct * 100.0 / total));
        }

        return porcentajes;
    }

    public Map<String, Double> porcentajePorNivel() {
        Map<String, Integer> correctas = new HashMap<>();
        Map<String, Integer> totales = new HashMap<>();

        for (int i = 0; i < preguntas.size(); i++) {
            Pregunta pregunta = preguntas.get(i);
            String nivel = pregunta.getNivel();
            boolean correcta = pregunta.esCorrecta(respuestasUser.get(i));

            totales.put(nivel, totales.getOrDefault(nivel, 0) + 1);
            if (correcta) correctas.put(nivel, correctas.getOrDefault(nivel, 0) + 1);
        }

        Map<String, Double> porcentajes = new HashMap<>();
        for (String nivel : totales.keySet()) {
            int total = totales.get(nivel);
            int correct = correctas.getOrDefault(nivel, 0);
            porcentajes.put(nivel, total == 0 ? 0.0 : (correct * 100.0 / total));
        }

        return porcentajes;
    }
}

















