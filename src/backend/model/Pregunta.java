package backend.model;

public abstract class Pregunta {
    protected String texto;
    protected String nivel;
    protected int tiempo;
    protected String tipo;

    public Pregunta(String texto, String nivel, int tiempo, String tipo ){
        this.texto = texto;
        this.nivel = nivel;
        this.tiempo = tiempo;
        this.tipo = tipo;
    }

    public String getTexto(){return texto;}
    public String getNivel(){return nivel;}
    public int getTiempo(){return tiempo;}
    public String getTipo(){return tipo;}

    public abstract boolean esCorrecta(String respuesta);

    public abstract String[] getRespuestas();

    public abstract String getRespuestaCorrecta();

}
