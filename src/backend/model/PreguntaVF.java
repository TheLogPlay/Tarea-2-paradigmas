package backend.model;

public class PreguntaVF extends Pregunta {
    private String respuestaCorrecta;

    public PreguntaVF(String texto, String nivel, int tiempo, String respuestaCorrecta) {
        super(texto, nivel, tiempo, "VF");
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public boolean esCorrecta(String respuesta) {
        return respuestaCorrecta.equalsIgnoreCase(respuesta);
    }

    @Override
    public String[] getRespuestas() {
        return new String[]{"Verdadero", "Falso"};
    }

    @Override
    public String getRespuestaCorrecta(){
        return respuestaCorrecta;
    }
}
