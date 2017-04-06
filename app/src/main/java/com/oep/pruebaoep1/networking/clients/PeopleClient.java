package com.oep.pruebaoep1.networking.clients;

import android.app.Activity;

import com.oep.pruebaoep1.networking.callbacks.OEPCallbacks;
import com.oep.pruebaoep1.networking.requests.GetRequest;

/**
 * Created by growcallisaya on 6/4/17.
 */

public class PeopleClient {

    private Activity activity;
    private String SERVER_URL="http://192.168.0.102:8000";
    private String PRODUCT_ENDPOINT=SERVER_URL+"/productos/";

    public PeopleClient(Activity activity) {
        this.activity = activity;
    }

    /**
     * --> GET /productos/
     **/
    public void getProducts(){
        GetRequest getRequest = new GetRequest(PRODUCT_ENDPOINT) {
            @Override
            public void onSucess(String result, int statusCode) {
                ((OEPCallbacks.productCallBack) activity).onPerformSuccess(result,statusCode);
            }

            @Override
            public void onFailed(String error, int statusCode) {
                ((OEPCallbacks.productCallBack) activity).onPerformFailed(error,statusCode);
            }
        };
        getRequest.execute();
    }
}
