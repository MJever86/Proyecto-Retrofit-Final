package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.vcarmen.retrofit.Adaptador;
import es.vcarmen.retrofit.R;
import es.vcarmen.retrofit.RestAlquiler;
import es.vcarmen.retrofit.model.Alquiler;
import es.vcarmen.retrofit.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoTodosAlquileres extends Fragment{
    private Retrofit retrofit;
    RestAlquiler service;
    ListView listaAlquileres;
    ArrayList<Alquiler> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragmento_todos_alquileres,container,false);
        retrofit= Conexion.getRetrofit();
        service= retrofit.create(RestAlquiler.class);
        listaAlquileres= (ListView) view.findViewById(R.id.listViewAlquileres);
        datos= new ArrayList<Alquiler>();
        obtenerAlquileres();
        return view;
    }

    //metodo que obtiene los alquileres
    public void obtenerAlquileres() {

        Call<Alquiler[]> call = service.getAlquileres();
        call.enqueue(new Callback<Alquiler[]>() {
            @Override
            public void onResponse(Call<Alquiler[]> call, Response<Alquiler[]> response) {
                Alquiler[] alquileres = response.body();
                for (Alquiler alquiler : alquileres){
                    datos.add(alquiler);
                }

                listaAlquileres.setAdapter(new Adaptador(getContext(),R.layout.entrada,datos) {
                    @Override
                    public void onEntrada(Object entrada, View view) {
                        if (entrada != null) {
                            TextView texto_superior_entrada = (TextView)
                                    view.findViewById(R.id.texto_titulo);
                            if (texto_superior_entrada != null)
                                texto_superior_entrada.setText(((Alquiler) entrada).getTitulo_pelicula());
                            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                            if (texto_inferior_entrada != null)
                                texto_inferior_entrada.setText(((Alquiler)
                                        entrada).getDni_cliente());
                        }

                    }
                });


            }

            @Override
            public void onFailure(Call<Alquiler[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }

        });


    }
}
