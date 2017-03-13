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
import es.vcarmen.retrofit.RestPeliculas;
import es.vcarmen.retrofit.model.Conexion;
import es.vcarmen.retrofit.model.Pelicula;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoPelicula extends Fragment{
    private TextView titulo_cabecera, titulo, año_cabecera, año, director_cabecera, director;
    private Button boton;
    private EditText editext;
    Retrofit retrofit;
    RestPeliculas service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_una_pelicula, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        titulo_cabecera= (TextView) view.findViewById(R.id.titulo_cabecera);
        titulo= (TextView) view.findViewById(R.id.titulo);
        año_cabecera= (TextView) view.findViewById(R.id.año_cabecera);
        año= (TextView) view.findViewById(R.id.año);
        director_cabecera= (TextView) view.findViewById(R.id.director_cabecera);
        director= (TextView) view.findViewById(R.id.director);
        boton= (Button) view.findViewById(R.id.botonBuscarTitulo);
        editext= (EditText) view.findViewById(R.id.editext_getPelicula);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obtenerPeliculaTitulo(editext.getText().toString());
            }
        });
        return view;
    }

    //metodo para obtener la pelicula por titulo
    private void obtenerPeliculaTitulo(String tituloBusqueda) {
        Call<Pelicula> call = service.getPeliculasTitulo(tituloBusqueda);
        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                Pelicula pelicula = response.body();
                //rellenamos los editext con los datos que tenemos en la base de datos
                titulo_cabecera.setText("TITULO");
                titulo.setText(pelicula.getTitulo());
                año_cabecera.setText("AÑO");
                año.setText(pelicula.getAño()+"");
                director_cabecera.setText("DIRECTOR");
                director.setText(pelicula.getDirector());
                Log.v("HelloWorld", pelicula.toString());
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
