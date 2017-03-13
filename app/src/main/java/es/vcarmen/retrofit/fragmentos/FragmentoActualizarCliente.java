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


public class FragmentoActualizarCliente extends Fragment{
    private EditText dni_buscar, dni, nombre, apellidos;
    private Button botonBuscar, botonActualizar;
    Retrofit retrofit;
    RestClientes service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_actualizar_cliente, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestClientes.class);
        dni_buscar= (EditText) view.findViewById(R.id.actualizar_buscar_dni);
        dni= (EditText) view.findViewById(R.id.actualizar_modificar_dni);
        nombre= (EditText) view.findViewById(R.id.actualizar_modificar_nombre);
        apellidos= (EditText) view.findViewById(R.id.actualizar_modificar_apellidos);
        botonBuscar= (Button) view.findViewById(R.id.actualizar_boton_buscar);
        botonActualizar= (Button) view.findViewById(R.id.actualizar_boton_actualizar);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerClienteDni(dni_buscar.getText().toString());
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarCliente(new Cliente(dni.getText().toString(),nombre.getText().toString(),apellidos.getText().toString()),dni_buscar.getText().toString());
            }
        });
        return view;
    }
    //metodo para obtener la lista de clientes
    private void obtenerClienteDni(String dniBusqueda) {
        Call<Cliente> call = service.getClientesDni(dniBusqueda);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cliente = response.body();
                dni.setText(cliente.getDni());
                nombre.setText(cliente.getNombre());
                apellidos.setText(cliente.getApellidos());
                Log.v("HelloWorld", cliente.toString());
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
    //metodo para actualizar un cliente
    private void actualizarCliente(Cliente cliente, String dni) {
        Call<Respuesta> call = service.putClientesDni(cliente, dni);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //si no existe la base de datos nos devuelve null
                if(respuesta!=null)
                Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"se ha producido un error al actualizar el cliente",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
