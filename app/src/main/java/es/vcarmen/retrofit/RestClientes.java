package es.vcarmen.retrofit;

import es.vcarmen.retrofit.model.Cliente;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;


public interface RestClientes {
    //obtener un cliente
    @GET("clientes/{dni}")
    Call<Cliente> getClientesDni(@Path("dni") String dni);

    //obtenemos los clientes
    @GET("clientes")
    Call<Cliente[]> getClientes();

    //creamos un cliente
    @POST("/clientes")
    Call<Respuesta> postClientes(@Body Cliente cliente);

    //borramos un cliente
    @DELETE("clientes/{dni}")
    Call<Respuesta> deleteClientesDni(@Path("dni") String dni);

    //actualizamos un cliente
    @PUT("clientes/{dni}")
    Call<Respuesta> putClientesDni(@Body Cliente cliente, @Path("dni") String dni);


}