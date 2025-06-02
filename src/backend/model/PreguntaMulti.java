package backend.model;

public class PreguntaMulti extends Pregunta {
    private String[] opciones;
    private String respuestaCorrecta;

    public PreguntaMulti(String texto, String nivel, int tiempo, String[] opciones, String respuestaCorrecta) {
        super(texto, nivel, tiempo, "multi");
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean esCorrecta(String respuesta) {
        return respuestaCorrecta.equalsIgnoreCase(respuesta);
    }

    @Override
    public String[] getRespuestas() {
        return opciones;
    }

    @Override
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }
}
