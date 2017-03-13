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


public class FragmentoCrearCliente extends Fragment {
    private EditText dni, nombre, apellidos;
    private Button boton;
    Retrofit retrofit;
    RestClientes service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_crear_cliente, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestClientes.class);
        dni= (EditText) view.findViewById(R.id.editext_crearClienteDni);
        nombre= (EditText) view.findViewById(R.id.editext_crearClienteNombre);
        apellidos= (EditText) view.findViewById(R.id.editext_crearClienteApellidos);
        boton= (Button) view.findViewById(R.id.botonCrearCliente);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCliente(new Cliente(dni.getText().toString(),nombre.getText().toString(),apellidos.getText().toString()));
            }
        });
        return view;
    }

    //metodo para crear un cliente
    private void crearCliente(Cliente cliente) {
        Call<Respuesta> call = service.postClientes(cliente);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if(respuesta!=null) {
                    Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                }else {
                    Toast.makeText(getContext(),"se ha producido un error al insertar el cliente en la base de datos",Toast.LENGTH_SHORT).show();
                    Log.v("HelloWorld", "se ha producido un error al insertar el cliente en la base de datos");
                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

}
