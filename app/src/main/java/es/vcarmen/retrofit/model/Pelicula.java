package es.vcarmen.retrofit.model;


public class Pelicula {
    private String titulo;
    private String director;
    private int año;

    public Pelicula(String titulo, String director, int año) {
        this.titulo = titulo;
        this.director = director;
        this.año = año;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "titulo='" + titulo + '\'' +
                ", director='" + director + '\'' +
                ", año=" + año +
                '}';
    }
}
