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
import es.vcarmen.retrofit.RestPeliculas;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Pelicula;
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;



public class FragmentoActualizarPelicula extends Fragment{
    private EditText titulo_buscar, titulo, año, director;
    private Button botonBuscar, botonActualizar;
    Retrofit retrofit;
    RestPeliculas service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_actualizar_pelicula, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        titulo_buscar= (EditText) view.findViewById(R.id.actualizar_buscar_Titulo);
        titulo= (EditText) view.findViewById(R.id.actualizar_modificar_titulo);
        año= (EditText) view.findViewById(R.id.actualizar_modificar_año);
        director= (EditText) view.findViewById(R.id.actualizar_modificar_director);
        botonBuscar= (Button) view.findViewById(R.id.actualizar_boton_buscar_pelicula);
        botonActualizar= (Button) view.findViewById(R.id.actualizar_boton_actualizar_pelicula);

        botonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerPeliculaTitulo(titulo_buscar.getText().toString());
            }
        });

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                actualizarPelicula(new Pelicula(titulo.getText().toString(),director.getText().toString(),Integer.parseInt(año.getText().toString())),titulo_buscar.getText().toString());
            }
        });
        return view;
    }
    //metodo para obtener el titulo de la pelicula
    private void obtenerPeliculaTitulo(String tituloBusqueda) {
        Call<Pelicula> call = service.getPeliculasTitulo(tituloBusqueda);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Pelicula pelicula = response.body();
                titulo.setText(pelicula.getTitulo());
                año.setText(pelicula.getAño()+"");
                director.setText(pelicula.getDirector());
                Log.v("HelloWorld", pelicula.toString());
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
    //metodo para actualizar la pelicula
    private void actualizarPelicula(Pelicula pelicula, String titulo) {
        Call<Respuesta> call = service.putPeliculaTitulo(pelicula, titulo);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                //si no existe la base de datos nos devuelve null
                if(respuesta!=null)
                    Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getContext(),"se ha producido un error al actualizar la pelicula",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

}
