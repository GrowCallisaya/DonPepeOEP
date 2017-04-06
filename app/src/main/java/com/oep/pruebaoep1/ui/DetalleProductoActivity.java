package com.oep.pruebaoep1.ui;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.oep.pruebaoep1.R;
import com.oep.pruebaoep1.networking.callbacks.OEPCallbacks;
import com.oep.pruebaoep1.networking.clients.PeopleClient;
import com.oep.pruebaoep1.utils.ConnectionUtils;

import org.w3c.dom.Text;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class DetalleProductoActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout col = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);



        Bundle mochila = getIntent().getExtras();
        Toast.makeText(this, "Elegiste: "+ mochila.getInt("PRECIO_KEY"), Toast.LENGTH_SHORT).show();

        //Referenciamos objetos
        ImageView imgProd = (ImageView) findViewById(R.id.iv_imagen_detalle);
        TextView tvPrecio = (TextView) findViewById(R.id.tv_precio_detalle);


        //Seteamos los valores
        String nombre= mochila.getString("NOMBRE_KEY");
        String imagen= mochila.getString("IMAGEN_KEY");
        int precio= mochila.getInt("PRECIO_KEY");


        col.setTitle(nombre);

        Glide.with(this)
                .load(Uri.parse("file:///android_asset/"+imagen))
                .into(imgProd);
        tvPrecio.setText(precio+" Bs.");




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }





























}
