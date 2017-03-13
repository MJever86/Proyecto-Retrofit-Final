package es.vcarmen.retrofit;

import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;



public interface RestNumeroAlquiler {
    //obtenemos el numero de alquileres
    @GET("numeroAlquileres/{titulo}")
    Call<Respuesta> getNumeroAlquilerTitulo(@Path("titulo") String titulo);
}
