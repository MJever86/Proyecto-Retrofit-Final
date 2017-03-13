package es.vcarmen.retrofit.fragmentos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import es.vcarmen.retrofit.R;


public class FragmentoNumeroAlquiler extends Fragment{

    TextView texto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_numero_alquiler,container, false);
        texto= (TextView) view.findViewById(R.id.textoNumeroAlquiler);
        //rellenamos un text view con los datos que obtenemos del bundle
        texto.setText(getArguments().getString("pelicula"));

        return view;
    }
}
