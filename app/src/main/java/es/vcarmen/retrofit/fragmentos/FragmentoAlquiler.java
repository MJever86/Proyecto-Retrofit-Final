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
import es.vcarmen.retrofit.RestAlquiler;
import es.vcarmen.retrofit.model.Alquiler;
import es.vcarmen.retrofit.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by david on 9/02/17.
 */

public class FragmentoAlquiler extends Fragment{
    private TextView dni_cabecera, dni, nombre_cabecera, nombre, fecha_alquilerCabecera, fecha_alquiler;
    private Button boton;
    private EditText editext;
    Retrofit retrofit;
    RestAlquiler service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_alquiler, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestAlquiler.class);
        dni_cabecera= (TextView) view.findViewById(R.id.dni_Alquilercabecera);
        dni= (TextView) view.findViewById(R.id.dni);
        nombre_cabecera= (TextView) view.findViewById(R.id.nombre_Alquilercabecera);
        nombre= (TextView) view.findViewById(R.id.nombreAlquiler);
        fecha_alquilerCabecera= (TextView) view.findViewById(R.id.fecha_alquilerCabecera);
        fecha_alquiler= (TextView) view.findViewById(R.id.fecha_alquiler);
        boton= (Button) view.findViewById(R.id.botonBuscarAlquilerDni);
        editext= (EditText) view.findViewById(R.id.editext_getAlquiler);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerAlquilerDni(editext.getText().toString());
            }
        });
        return view;
    }
    //metodo para obtener el alquiler por dni del cliente
    private void obtenerAlquilerDni(String dniBusqueda) {
        Call<Alquiler> call = service.getAlquilerDni(dniBusqueda);
        call.enqueue(new Callback<Alquiler>() {
            @Override
            public void onResponse(Call<Alquiler> call, Response<Alquiler> response) {
               Alquiler alquiler = response.body();
                //rellenamos los editext con los datos que tenemos en la base de datos
                dni_cabecera.setText("DNI");
               dni.setText(alquiler.getDni_cliente());
                nombre_cabecera.setText("TITULO PELICULA");
               nombre.setText(alquiler.getTitulo_pelicula());
                fecha_alquilerCabecera.setText("FECHA ALQUILER");
               fecha_alquiler.setText(alquiler.getFecha_alquiler());
               // Log.v("HelloWorld", alquiler.toString());
            }

            @Override
            public void onFailure(Call<Alquiler> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
