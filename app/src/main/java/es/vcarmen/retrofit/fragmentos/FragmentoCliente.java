package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import es.vcarmen.retrofit.R;
import es.vcarmen.retrofit.RestClientes;
import es.vcarmen.retrofit.model.Cliente;
import es.vcarmen.retrofit.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoCliente extends Fragment {
    private TextView dni_cabecera, dni, nombre_cabecera, nombre, apellidos_cabecera, apellidos;
    private Button boton;
    private EditText editext;
    Retrofit retrofit;
    RestClientes service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_un_cliente, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestClientes.class);
        dni_cabecera= (TextView) view.findViewById(R.id.dni_cabecera);
        dni= (TextView) view.findViewById(R.id.dni);
        nombre_cabecera= (TextView) view.findViewById(R.id.nombre_cabecera);
        nombre= (TextView) view.findViewById(R.id.nombre);
        apellidos_cabecera= (TextView) view.findViewById(R.id.apellidos_cabecera);
        apellidos= (TextView) view.findViewById(R.id.apellidos);
        boton= (Button) view.findViewById(R.id.botonBuscarDni);
        editext= (EditText) view.findViewById(R.id.editext_getCliente);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerClienteDni(editext.getText().toString());
            }
        });
        return view;
    }

    //metodo para obtener un cliente mediante el dni
    private void obtenerClienteDni(String dniBusqueda) {
        Call<Cliente> call = service.getClientesDni(dniBusqueda);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                Cliente cliente = response.body();
                //rellenamos los editext con los datos que tenemos en la base de datos
                dni_cabecera.setText("DNI");
                dni.setText(cliente.getDni());
                nombre_cabecera.setText("NOMBRE");
                nombre.setText(cliente.getNombre());
                apellidos_cabecera.setText("APELLIDO");
                apellidos.setText(cliente.getApellidos());
                Log.v("HelloWorld", cliente.toString());
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
