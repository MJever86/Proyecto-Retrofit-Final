package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import es.vcarmen.retrofit.Adaptador;
import es.vcarmen.retrofit.R;
import es.vcarmen.retrofit.RestNumeroAlquiler;
import es.vcarmen.retrofit.RestPeliculas;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Pelicula;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoListadoNumeroAlquiler extends Fragment {
    Retrofit retrofit;
    RestPeliculas service;
    RestNumeroAlquiler serviceNumeroAlquiler;
    ListView listviewPeliculas;
    ArrayList<Pelicula> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_listado_numero_alquiler, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        this.serviceNumeroAlquiler=retrofit.create(RestNumeroAlquiler.class);
        listviewPeliculas= (ListView) view.findViewById(R.id.listViewNumeroAlquileres);
        datos= new ArrayList<Pelicula>();
        obtenerPeliculas();
        listviewPeliculas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                obtenerNumeroAlquileres(datos.get(position).getTitulo());
            }
        });
        return view;
    }

    //metodo para obtener el listado de peliculas
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

    //metodo para obtener el numero de alquileres de cada pelicula
    public void obtenerNumeroAlquileres(String titulo){
        Call<Respuesta> call = serviceNumeroAlquiler.getNumeroAlquilerTitulo(titulo);
        //con el callback obtenemos la respuesta
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //creamos un bundle (pasa datos de un fragmento a otro) para pasar la pelicula al fragmento numero de alquiler
                Bundle bundle=new Bundle();
                bundle.putString("pelicula",respuesta.getRespuesta());
                Fragment fragmento=new FragmentoNumeroAlquiler();
                fragmento.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.activity_retrofit,fragmento).commit();
            }
            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }

        });

    }

}
