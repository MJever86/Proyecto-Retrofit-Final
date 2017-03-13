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
import es.vcarmen.retrofit.RestClientes;
import es.vcarmen.retrofit.model.Cliente;
import es.vcarmen.retrofit.model.Conexion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class FragmentoTodosClientes extends Fragment {
    Retrofit retrofit;
    RestClientes service;
    ListView listviewClientes;
    ArrayList<Cliente> datos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragmento_todos_clientes, container, false);
        this.retrofit = Conexion.getRetrofit();
        this.service = retrofit.create(RestClientes.class);
        listviewClientes= (ListView) view.findViewById(R.id.listViewClientes);
        datos= new ArrayList<Cliente>();
        obtenerClientes();
        return view;
    }

    //fragmento que obtiene los clientes
    public void obtenerClientes() {

        Call<Cliente[]> call = service.getClientes();
        call.enqueue(new Callback<Cliente[]>() {
            @Override
            public void onResponse(Call<Cliente[]> call, Response<Cliente[]> response) {
                Cliente[] clientes = response.body();
                for (Cliente cliente : clientes){
                    datos.add(cliente);
                }
                listviewClientes.setAdapter(new Adaptador(getContext(),R.layout.entrada,datos) {
                    @Override
                    public void onEntrada(Object entrada, View view) {
                        if (entrada != null) {
                            TextView texto_superior_entrada = (TextView)
                                    view.findViewById(R.id.texto_titulo);
                            if (texto_superior_entrada != null)
                                texto_superior_entrada.setText(((Cliente) entrada).getNombre());
                            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_datos);
                            if (texto_inferior_entrada != null)
                                texto_inferior_entrada.setText(((Cliente)
                                        entrada).getApellidos());
                        }

                        }
                });
            }

            @Override
            public void onFailure(Call<Cliente[]> call, Throwable t) {
                Log.e("HelloWorld", t.getMessage());
            }
        });
    }
}
