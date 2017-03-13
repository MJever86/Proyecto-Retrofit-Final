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
import es.vcarmen.retrofit.RestPeliculas;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Pelicula;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoTodasPeliculas extends Fragment{
    Retrofit retrofit;
    RestPeliculas service;
    ListView listviewPeliculas;
    ArrayList<Pelicula> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_todas_peliculas, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        listviewPeliculas= (ListView) view.findViewById(R.id.listViewPeliculas);
        datos= new ArrayList<Pelicula>();
        obtenerPeliculas();
        return view;
    }

    //metodo que obtiene las peliculas
    public void obtenerPeliculas() {
        Call<Pelicula[]> call = service.getPeliculas();
        call.enqueue(new Callback<Pelicula[]>() {
            @Override
            public void onResponse(Call<Pelicula[]> call, Response<Pelicula[]> response) {
                Pelicula[] peliculas = response.body();
                for (Pelicula pelicula : peliculas){
                    datos.add(pelicula);
                }
                listviewPeliculas.setAdapter(new Adaptador(getContext(),R.layout.entrada,datos) {
                    @Override
                    public void onEntrada(Object entrada, View view) {
                        if (entrada != null) {
                            TextView texto_superior_entrada = (TextView)
                                    view.findViewById(R.id.texto_titulo);
                            if (texto_superior_entrada != null)
                                texto_superior_entrada.setText(((Pelicula) entrada).getTitulo());
                            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                            if (texto_inferior_entrada != null)
                                texto_inferior_entrada.setText(((Pelicula)
                                        entrada).getAño()+"");
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call<Pelicula[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
