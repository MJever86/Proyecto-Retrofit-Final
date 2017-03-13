package es.vcarmen.retrofit;

import es.vcarmen.retrofit.model.Alquiler;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestAlquiler {
    //obtener un alquiler
    @GET("alquiler/{dni}")
    Call<Alquiler> getAlquilerDni(@Path("dni") String dni);

    //obtenemos alquileres
    @GET("alquiler")
    Call<Alquiler[]> getAlquileres();

    //creamos un alquiler
    @POST("/alquiler")
    Call<Respuesta> postAlquiler(@Body Alquiler alquiler);

    //actualizamos el alquiler
    @PUT("alquiler/{dni}/{tituloPelicula}/{fechaAlquiler}/{fechaEntrega}")
    Call<Respuesta> putAlquilerDni(@Body Alquiler alquiler, @Path("dni") String dni,@Path("tituloPelicula") String tituloPelicula,@Path("fechaAlquiler") String fechaAlquiler,@Path("fechaEntrega") String fechaEntrega);

    //borramos un alquiler
    @DELETE("alquiler/{tituloPelicula}/{dni}/{fechaAlquiler}/{fechaEntrega}")
    Call<Respuesta> deleteAlquilerDni(@Path("tituloPelicula") String tituloPelicula, @Path("dni") String dni,@Path("fechaAlquiler") String fechaAlquiler,@Path("fechaEntrega") String fechaEntrega);
}
