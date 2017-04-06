package com.oep.pruebaoep1.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.oep.pruebaoep1.networking.callbacks.OEPCallbacks;
import com.oep.pruebaoep1.networking.clients.OEPService;
import com.oep.pruebaoep1.networking.clients.PeopleClient;
import com.oep.pruebaoep1.ui.adapters.ProductosAdapter;
import com.oep.pruebaoep1.R;
import com.oep.pruebaoep1.models.Producto;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupermercadoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, OEPCallbacks.productCallBack, Callback<Producto> {
    TextView tvNombre;
    TextView tvCiudad;
    ListView listaProd;
    ProductosAdapter mAdaptador;

    String txt_nombre;
    String txt_ciudad;
    String productos [] = new String[]{"Ron","Ace","Pintura","Escopeta"};
    ArrayList<Producto> productos2 = new ArrayList<>();
    ArrayList<HashMap<String, String>> productList;

    private PeopleClient client;
    OEPService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supermercado);

        //Referenciamos a los objetos
        tvNombre = (TextView) findViewById(R.id.tv_nombre);
        tvCiudad = (TextView) findViewById(R.id.tv_ciudad);
        listaProd = (ListView) findViewById(R.id.lista_productos);

        //Obtenemos datos enviados desde el MainActivity
        Bundle mochila = getIntent().getExtras();
        txt_nombre = mochila.getString("NOMBRE_KEY");
        txt_ciudad = mochila.getString("CIUDAD_KEY");

        //Seteamos los datos en los TextViews
        tvNombre.setText("BIENVENIDO "+txt_nombre);
        tvCiudad.setText(txt_ciudad);

        //TODO Configuramos la Lista mas de Objetos (MANUAL)
//        productos2.add(0,new Producto("botacho.jpg","Ron Abuelo",12));
//        productos2.add(1,new Producto("botacho.jpg","Ace Omo",24));
//        productos2.add(2,new Producto("botacho.jpg","Juegue de Ule",52));
//        productos2.add(3,new Producto("botacho.jpg","Ron Abuelo",12));
//        productos2.add(4,new Producto("botacho.jpg","Ace Omo",24));
//        productos2.add(5,new Producto("botacho.jpg","Juegue de Ule",52));
//        productos2.add(6,new Producto("botacho.jpg","Ron Abuelo",12));
//        productos2.add(7,new Producto("botacho.jpg","Ace Omo",24));
//        productos2.add(8,new Producto("botacho.jpg","Juegue de Ule",52));
//        productos2.add(9,new Producto("botacho.jpg","Ron Abuelo",12));
//        productos2.add(10,new Producto("botacho.jpg","Ace Omo",24));
//        productos2.add(11,new Producto("botacho.jpg","Juegue de Ule",52));
//        mAdaptador = new ProductosAdapter(this,R.layout.item_lista,productos2);
//        listaProd.setAdapter(mAdaptador);
//        listaProd.setOnItemClickListener(this);



        // TODO Cliente Local
        client = new PeopleClient(this);
        client.getProducts();

        // TODO Cliente Retrofit
        //service = new OEPService();
        //Call<Producto> request = service.getApi().getProducto();
        //request.enqueue(this);
    }


    /**
     * METODOS PARA GETREQUEST LOCAL
     **/
    @Override
    public void onPerformSuccess(String result, int statuCode) {
        Log.e("ClienteLocal OK", result);
        productList = new ArrayList<>();

        try {
            JSONObject jsonObj = new JSONObject(result);
            JSONArray productos = jsonObj.getJSONArray("results");
            for (int i = 0 ;i< productos.length();i++){
                JSONObject p = productos.getJSONObject(i);
                Log.e("Producto",p.getString("name"));
                String nombre = p.getString("name");
                String imagen= p.getString("image");
                int precio = p.getInt("price");
                productos2.add(i,new Producto(imagen,nombre,precio));
                mAdaptador = new ProductosAdapter(this,R.layout.item_lista,productos2);
                listaProd.setAdapter(mAdaptador);
                listaProd.setOnItemClickListener(this);
            }
        }catch (Exception ex){
        }

    }

    @Override
    public void onPerformFailed(String error, int statusCode) {
        Log.e("ClienteLocal ERROR", error);
    }

    /**
     * METODOS PARA RETROFIT
     **/
    @Override
    public void onResponse(Call<Producto> call, Response<Producto> response) {
        Log.e("ClienteRetrofit OK", response+"");
    }

    @Override
    public void onFailure(Call<Producto> call, Throwable error) {
        Log.e("ClienteRetrofit OK", error+"");
    }



    /**
     * METODOS CUANDO HAGA CLICK EN ALGUN PRODUCTO
     **/
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Producto p = mAdaptador.getItem(position);
        Intent intent = new Intent(SupermercadoActivity.this, DetalleProductoActivity.class);
        intent.putExtra("NOMBRE_KEY", p.getNombre());
        intent.putExtra("IMAGEN_KEY", p.getImagen());
        intent.putExtra("PRECIO_KEY", p.getPrecio());
        startActivity(intent);
    }

}
