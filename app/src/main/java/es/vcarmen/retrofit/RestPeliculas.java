package es.vcarmen.retrofit;

import es.vcarmen.retrofit.model.Cliente;
import es.vcarmen.retrofit.model.Pelicula;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestPeliculas {
    //obtenemos pelicula
    @GET("peliculas/{titulo}")
    Call<Pelicula> getPeliculasTitulo(@Path("titulo") String titulo);

    //obtenemos todas las peliculas
    @GET("peliculas")
    Call<Pelicula[]> getPeliculas();

    //creamos una pelicula
    @POST("/peliculas")
    Call<Respuesta> postPeliculas(@Body Pelicula pelicula);

    //borramos una pelicula
    @DELETE("peliculas/{titulo}")
    Call<Respuesta> deletePeliculasTitulo(@Path("titulo") String titulo);

    //actualizamos la pelicula
    @PUT("peliculas/{titulo}")
    Call<Respuesta> putPeliculaTitulo(@Body Pelicula pelicula, @Path("titulo") String titulo);
}
