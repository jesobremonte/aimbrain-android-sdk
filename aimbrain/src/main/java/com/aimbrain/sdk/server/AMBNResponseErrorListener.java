package com.aimbrain.sdk.server;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;


public class AMBNResponseErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        error.printStackTrace();

        String json = null;

        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            switch(response.statusCode){
                default:
                    json = new String(response.data);
                    json = trimMessage(json, "Error");
                    if(json != null) displayMessage(json, response.statusCode);
                    break;
            }
        }
    }

    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }

        return trimmedString;
    }

    public void displayMessage(String errorMessage, int statusCode){
        Log.w("RESPONSE ERROR MESSAGE", "ERROR " + statusCode + ": " +errorMessage);
    }
}
