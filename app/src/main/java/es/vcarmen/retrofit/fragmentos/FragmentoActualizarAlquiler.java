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



public class FragmentoActualizarAlquiler extends Fragment{
    EditText dniBuscar, dni, titulo, fechaAlquiler, fechaEntrega;
    Button buscar, actualizar;
    Retrofit retrofit;
    RestAlquiler service;
    String tituloInicio, fechaAlquilerInicio, fechaEntregaInicio;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_actualizar_alquiler,container, false);
        dniBuscar = (EditText) view.findViewById(R.id.actualizar_alquiler_buscar_dni);
        dni = (EditText) view.findViewById(R.id.actualizar_alquiler_modificar_dni);
        titulo = (EditText) view.findViewById(R.id.actualizar_alquiler_modificar_titulo);
        fechaAlquiler = (EditText) view.findViewById(R.id.actualizar_alquiler_modificar_fechaAlquiler);
        fechaEntrega = (EditText) view.findViewById(R.id.actualizar_alquiler_modificar_fechaEntrega);

        buscar = (Button) view.findViewById(R.id.actualizar_alquiler_boton_buscar);
        actualizar = (Button) view.findViewById(R.id.actualizar_alquiler_boton_actualizar);
        //obtenemos la conexion
        retrofit = Conexion.getRetrofit();
        service = retrofit.create(RestAlquiler.class);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerAlquilerDni(dniBuscar.getText().toString());
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarAlquiler(new Alquiler(fechaAlquiler.getText().toString(),fechaEntrega.getText().toString(),dni.getText().toString(),titulo.getText().toString())
                        ,dniBuscar.getText().toString(),tituloInicio,fechaAlquilerInicio,fechaEntregaInicio);
            }
        });
        return view;
    }
    //metodo para obtener el alquiler
    private void obtenerAlquilerDni(String dniBusqueda) {
        Call<Alquiler> call = service.getAlquilerDni(dniBusqueda);
        call.enqueue(new Callback<Alquiler>() {
            @Override
            public void onResponse(Call<Alquiler> call, Response<Alquiler> response) {
                Alquiler alquiler = response.body();
                dni.setText(alquiler.getDni_cliente());
                titulo.setText(alquiler.getTitulo_pelicula());
                fechaAlquiler.setText(alquiler.getFecha_alquiler());
                fechaEntrega.setText(alquiler.getFecha_entrega());
                // Log.v("HelloWorld", alquiler.toString());
                tituloInicio=alquiler.getTitulo_pelicula();
                fechaAlquilerInicio=alquiler.getFecha_alquiler();
                fechaEntregaInicio=alquiler.getFecha_entrega();
            }

            @Override
            public void onFailure(Call<Alquiler> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
    //metodo para actualizar el alquiler
    private void actualizarAlquiler(Alquiler alquiler, String dni, String titulo, String fechaAlquiler, String fechaEntrega) {
        Call<Respuesta> call = service.putAlquilerDni(alquiler,dni,titulo,fechaAlquiler,fechaEntrega);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //si no existe la base de datos nos devuelve null
                if(respuesta!=null)
                    Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"se ha producido un error al actualizar el alquiler",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
