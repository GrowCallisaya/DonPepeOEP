package com.oep.pruebaoep1.ui.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.oep.pruebaoep1.R;
import com.oep.pruebaoep1.models.Producto;

import java.util.ArrayList;

/**
 * Created by growcallisaya on 4/4/17.
 */

public class ProductosAdapter extends ArrayAdapter<Producto> {

    public ProductosAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public ProductosAdapter(Context context, int resource, ArrayList<Producto> items) {
        super(context, resource, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vista= convertView;

        if (vista ==null){
            LayoutInflater vi= LayoutInflater.from(getContext());
            vista = vi.inflate(R.layout.item_lista,null);
        }

        Producto p = getItem(position);

        //Referenciamos a UI
        final ImageView imgProd = (ImageView) vista.findViewById(R.id.iv_imagen_prod);
        TextView tvNombre = (TextView) vista.findViewById(R.id.tv_nombre_prod);
        TextView tvPrecio = (TextView) vista.findViewById(R.id.tv_precio_prod);

        //Seteamos Valores de acuerdo a la Posicion
        tvNombre.setText(p.getNombre());
        tvPrecio.setText(p.getPrecio()+" Bs");
        Glide
                .with(getContext())
//                .load(Uri.parse("file:///android_asset/"+p.getImagen()))
                .load(p.getImagen())
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.icono)
                .into(new BitmapImageViewTarget(imgProd){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(getContext().getResources(),resource);
                        drawable.setCircular(true);
                        imgProd.setImageDrawable(drawable);
                    }
                });


        return vista;
    }
}
