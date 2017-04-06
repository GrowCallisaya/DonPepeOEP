package com.oep.pruebaoep1.networking.callbacks;

/**
 * Created by growcallisaya on 6/4/17.
 */

public class OEPCallbacks {

    public static interface productCallBack {
        public void onPerformSuccess(String result, int statuCode);
        public void onPerformFailed(String error, int statusCode);
    }
}
