package es.vcarmen.retrofit.model;


public class Alquiler {
    String fecha_alquiler;
    String fecha_entrega;
    String dni_cliente;
    String titulo_pelicula;

    public Alquiler(String fechaalquiler, String fechaentrega, String dnicliente, String titulopelicula) {
        fecha_alquiler = fechaalquiler;
        fecha_entrega = fechaentrega;
        dni_cliente = dnicliente;
        titulo_pelicula = titulopelicula;
    }

    public String getFecha_alquiler() {
        return fecha_alquiler;
    }

    public void setFecha_alquiler(String fecha_alquiler) {
        this.fecha_alquiler = fecha_alquiler;
    }

    public String getFecha_entrega() {
        return fecha_entrega;
    }

    public void setFecha_entrega(String fecha_entrega) {
        this.fecha_entrega = fecha_entrega;
    }

    public String getDni_cliente() {
        return dni_cliente;
    }

    public void setDni_cliente(String dni_cliente) {
        this.dni_cliente = dni_cliente;
    }

    public String getTitulo_pelicula() {
        return titulo_pelicula;
    }

    public void setTitulo_pelicula(String titulo_pelicula) {
        this.titulo_pelicula = titulo_pelicula;
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "fecha_alquiler='" + fecha_alquiler + '\'' +
                ", fecha_entrega='" + fecha_entrega + '\'' +
                ", dni_cliente='" + dni_cliente + '\'' +
                ", titulo_pelicula='" + titulo_pelicula + '\'' +
                '}';
    }
}
