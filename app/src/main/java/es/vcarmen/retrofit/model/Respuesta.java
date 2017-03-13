package es.vcarmen.retrofit.model;


public class Respuesta {
    private String messagge;

    public Respuesta(String respuesta) {
        this.messagge = respuesta;
    }

    public String getRespuesta() {
        return messagge;
    }

    public void setRespuesta(String respuesta) {
        this.messagge = respuesta;
    }

    @Override
    public String toString() {
        return "Respuesta{" +
                "messagge='" + messagge + '\'' +
                '}';
    }
}
