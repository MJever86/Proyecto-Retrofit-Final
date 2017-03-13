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
import es.vcarmen.retrofit.RestClientes;
import es.vcarmen.retrofit.model.Cliente;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class FragmentoBorrarCliente extends Fragment{
    private EditText dni;
    private Button boton;
    Retrofit retrofit;
    RestClientes service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_borrar_cliente, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestClientes.class);
        dni= (EditText) view.findViewById(R.id.borrar_buscar_dni);

        boton= (Button) view.findViewById(R.id.borrar_boton_buscar);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarClienteDni(dni.getText().toString());
            }
        });
        return view;
    }

    //metodo para borrar un cliente
    private void borrarClienteDni(String dni) {
        Call<Respuesta> call = service.deleteClientesDni(dni);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //nos muestra con un mensaje que el cliente se ha borrado
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
