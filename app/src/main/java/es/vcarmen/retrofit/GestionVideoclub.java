package es.vcarmen.retrofit;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import es.vcarmen.retrofit.fragmentos.FragmentoActualizarAlquiler;
import es.vcarmen.retrofit.fragmentos.FragmentoActualizarCliente;
import es.vcarmen.retrofit.fragmentos.FragmentoActualizarPelicula;
import es.vcarmen.retrofit.fragmentos.FragmentoAlquiler;
import es.vcarmen.retrofit.fragmentos.FragmentoBorrarAlquiler;
import es.vcarmen.retrofit.fragmentos.FragmentoBorrarCliente;
import es.vcarmen.retrofit.fragmentos.FragmentoBorrarPelicula;
import es.vcarmen.retrofit.fragmentos.FragmentoCliente;
import es.vcarmen.retrofit.fragmentos.FragmentoCrearAlquiler;
import es.vcarmen.retrofit.fragmentos.FragmentoCrearCliente;
import es.vcarmen.retrofit.fragmentos.FragmentoCrearPelicula;
import es.vcarmen.retrofit.fragmentos.FragmentoListadoNumeroAlquiler;
import es.vcarmen.retrofit.fragmentos.FragmentoPelicula;
import es.vcarmen.retrofit.fragmentos.FragmentoPrincipal;
import es.vcarmen.retrofit.fragmentos.FragmentoTodasPeliculas;
import es.vcarmen.retrofit.fragmentos.FragmentoTodosAlquileres;
import es.vcarmen.retrofit.fragmentos.FragmentoTodosClientes;
import es.vcarmen.retrofit.model.Conexion;
import retrofit2.Retrofit;

public class GestionVideoclub extends AppCompatActivity {
    //ventana de inicio o principal
    private TextView texto;
    RestClientes service;
    Retrofit retrofit;
    NavigationView navigation;
    FragmentManager fm;
    FragmentTransaction ft;
    FragmentoPrincipal fragmentoPrincipal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        retrofit = Conexion.getRetrofit();
        service = retrofit.create(RestClientes.class);
        navigation = (NavigationView) findViewById(R.id.navview);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        fragmentoPrincipal = new FragmentoPrincipal();
        ft.add(R.id.activity_retrofit, fragmentoPrincipal);
        ft.commit();

        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragmento=new Fragment();
                //creamos un fragmento en funcion del item que pulsemos
                switch (item.getItemId()){
                    case R.id.cliente_1:
                        fragmento=new FragmentoTodosClientes();
                        break;
                    case R.id.cliente_2:
                        fragmento=new FragmentoCliente();
                        break;
                    case R.id.cliente_3:
                        fragmento=new FragmentoCrearCliente();
                        break;
                    case R.id.cliente_4:
                        fragmento=new FragmentoActualizarCliente();
                        break;
                    case R.id.cliente_5:
                        fragmento=new FragmentoBorrarCliente();
                        break;
                    case R.id.pelicula_1:
                        fragmento=new FragmentoTodasPeliculas();
                        break;
                    case R.id.pelicula_2:
                        fragmento=new FragmentoPelicula();
                        break;
                    case R.id.pelicula_3:
                        fragmento=new FragmentoCrearPelicula();
                        break;
                    case R.id.pelicula_4:
                        fragmento=new FragmentoActualizarPelicula();
                        break;
                    case R.id.pelicula_5:
                        fragmento=new FragmentoBorrarPelicula();
                        break;
                    case R.id.alquiler_1:
                        fragmento=new FragmentoTodosAlquileres();
                        break;
                    case R.id.alquiler_2:
                        fragmento=new FragmentoAlquiler();
                        break;
                    case R.id.alquiler_3:
                        fragmento= new FragmentoCrearAlquiler();
                        break;
                    case R.id.alquiler_4:
                        fragmento = new FragmentoActualizarAlquiler();
                        break;
                    case R.id.alquiler_5:
                        fragmento=new FragmentoBorrarAlquiler();
                        break;
                    case R.id.numero_alquiler_1:
                        fragmento=new FragmentoListadoNumeroAlquiler();
                        break;

                }
                //cambiamos de fragmento
                fm.beginTransaction()
                        .replace(R.id.activity_retrofit, fragmento)
                        .commit();
                return true;
            }
        });
    }
}
