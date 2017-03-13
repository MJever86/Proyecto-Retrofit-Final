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


public class FragmentoCrearPelicula extends Fragment{
    private EditText titulo, año, director;
    private Button boton;
    Retrofit retrofit;
    RestPeliculas service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_crear_pelicula, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        titulo= (EditText) view.findViewById(R.id.editext_crearPeliculaTitulo);
        año= (EditText) view.findViewById(R.id.editext_crearPeliculaAño);
        director= (EditText) view.findViewById(R.id.editext_crearPeliculaDirector);
        boton= (Button) view.findViewById(R.id.botonCrearPelicula);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearPelicula(new Pelicula(titulo.getText().toString(),director.getText().toString(),Integer.parseInt(año.getText().toString())));
            }
        });
        return view;
    }

    //metodo para crear una pelicula
    private void crearPelicula(Pelicula pelicula) {
        Call<Respuesta> call = service.postPeliculas(pelicula);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                if(respuesta!=null) {
                    Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                    Log.v("HelloWorld", respuesta.toString());
                }else {
                    Toast.makeText(getContext(),"se ha producido un error al insertar la pelicula en la base de datos",Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
