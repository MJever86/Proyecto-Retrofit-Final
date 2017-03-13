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
import es.vcarmen.retrofit.model.Respuesta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoBorrarPelicula extends Fragment{
    private EditText titulo;
    private Button boton;
    Retrofit retrofit;
    RestPeliculas service;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_borrar_pelicula, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestPeliculas.class);
        titulo= (EditText) view.findViewById(R.id.borrar_buscar_pelicula);

        boton= (Button) view.findViewById(R.id.borrar_boton_buscar_pelicula);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                borrarPeliculaTitulo(titulo.getText().toString());
            }
        });
        return view;
    }

    //metodo para borrar una pelicula por el titulo
    private void borrarPeliculaTitulo(String titulo) {
        Call<Respuesta> call = service.deletePeliculasTitulo(titulo);
        call.enqueue(new Callback<Respuesta>() {
            @Override
            public void onResponse(Call<Respuesta> call, Response<Respuesta> response) {
                Respuesta respuesta = response.body();
                Toast.makeText(getContext(),respuesta.getRespuesta(),Toast.LENGTH_SHORT).show();
                Log.v("HelloWorld", respuesta.toString());
            }

            @Override
            public void onFailure(Call<Respuesta> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }

}
