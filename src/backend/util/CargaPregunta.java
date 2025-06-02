package backend.util;

import backend.model.*;

import java.io.*;
import java.util.*;

public class CargaPregunta {

    public static List<Pregunta> cargarDesdeArchivo(File archivo) throws IOException {
        List<Pregunta> preguntas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(",");

                String tipo = partes[0].trim();
                String nivel = partes[1].trim();
                String texto = partes[2].trim();
                int tiempo = Integer.parseInt(partes[partes.length - 1].trim());

                if (tipo.equals("multiple")) {
                    String[] opciones = Arrays.copyOfRange(partes, 3, 7);
                    String respuesta = partes[7].trim();
                    preguntas.add(new PreguntaMulti(texto, nivel, tiempo, opciones, respuesta));
                } else if (tipo.equals("vf")) {
                    String respuesta = partes[3].trim();
                    preguntas.add(new PreguntaVF(texto, nivel, tiempo, respuesta));
                } else {
                    throw new IllegalArgumentException("Tipo de Pregunta no valido: " + tipo);
                }
            }
        }

        return preguntas;
    }
}
