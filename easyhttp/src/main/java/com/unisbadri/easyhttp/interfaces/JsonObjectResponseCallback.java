package com.unisbadri.easyhttp.interfaces;

import org.json.JSONObject;

public interface JsonObjectResponseCallback
{
    void onSuccess(int actionCode, JSONObject response) throws Exception;
    void onFailure(int actionCode, String responseCode, String responseDescription, Throwable throwable);
}
