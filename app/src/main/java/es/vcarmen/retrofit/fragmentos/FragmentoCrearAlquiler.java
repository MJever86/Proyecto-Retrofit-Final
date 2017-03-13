package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.vcarmen.retrofit.R;
import es.vcarmen.retrofit.RestAlquiler;
import es.vcarmen.retrofit.model.Alquiler;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoCrearAlquiler extends Fragment{

    EditText dni, titulo, fecha_alquiler, fecha_entrega;
    Button crearAlquiler;
    Retrofit retrofit;
    RestAlquiler service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmento_crear_alquiler,container,false);
        dni= (EditText) view.findViewById(R.id.crear_alquilerDni);
        titulo= (EditText) view.findViewById(R.id.crear_alquilerTituloPelicula);
        fecha_alquiler= (EditText) view.findViewById(R.id.crear_fechaAlquiler);
        fecha_entrega= (EditText) view.findViewById(R.id.crear_fechaEntrega);
        retrofit= Conexion.getRetrofit();
        service = retrofit.create(RestAlquiler.class);
        crearAlquiler= (Button) view.findViewById(R.id.crear_boton_alquiler);
        crearAlquiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearAlquiler(new Alquiler(fecha_alquiler.getText().toString(),fecha_entrega.getText().toString(),dni.getText().toString(),titulo.getText().toString()));
            }
        });


        return view;
    }

    //metodo para crear alquiler
    private void crearAlquiler(Alquiler alquiler) {
        Call<Respuesta> call = service.postAlquiler(alquiler);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //si no existe la base de datos nos devuelve null
                if(respuesta!=null) {
                    Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                //si se produce un error nos muestra el mensaje de error
                }else {
                    Toast.makeText(getContext(),"se ha producido un error al alquilar la pelicula",Toast.LENGTH_SHORT).show();
                   // Log.v("HelloWorld", "se ha producido un error al insertar la pelicula en la base de datos");
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
