package com.oep.pruebaoep1.networking.clients;


import com.oep.pruebaoep1.models.Producto;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

/**
 * Created by Grover on 5/12/16.
 */

public interface Api {



    @GET("/productos/")
    Call<Producto> getProducto();


}
