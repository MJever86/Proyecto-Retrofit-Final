package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import es.vcarmen.retrofit.RestClientes;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoBorrarAlquiler extends Fragment{
    EditText dni, titulo,fechaAlquiler, fechaEntrega;
    Button borrarAlquiler;
    private Retrofit retrofit;
    private RestAlquiler service;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmento_borrar_alquiler,container,false);
        dni= (EditText) view.findViewById(R.id.borrar_alquilerDni);
        titulo= (EditText) view.findViewById(R.id.borrar_alquilerTituloPelicula);
        fechaAlquiler= (EditText) view.findViewById(R.id.borrar_fechaAlquiler);
        fechaEntrega= (EditText) view.findViewById(R.id.borrar_fechaEntrega);
        borrarAlquiler= (Button) view.findViewById(R.id.borrar_boton_alquiler);
        retrofit= Conexion.getRetrofit();
        service = retrofit.create(RestAlquiler.class);
        borrarAlquiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarAlquiler(dni.getText().toString(),titulo.getText().toString(),fechaAlquiler.getText().toString(),fechaEntrega.getText().toString());
            }
        });

        return view;
    }
    //metodo para borrar un alquiler
    private void borrarAlquiler(String dni,String titulo, String fecha_alquiler, String fecha_entrega) {
        Call<Respuesta> call = service.deleteAlquilerDni(titulo,dni,fecha_alquiler,fecha_entrega);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                Log.v("HelloWorld", respuesta.toString());
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
