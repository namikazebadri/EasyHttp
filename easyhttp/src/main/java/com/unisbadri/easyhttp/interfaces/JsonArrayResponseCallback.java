package com.unisbadri.easyhttp.interfaces;

import org.json.JSONArray;

public interface JsonArrayResponseCallback
{
    void onSuccess(int actionCode, JSONArray response);
    void onFailure(int actionCode, String responseCode, String responseDescription, Throwable throwable);
}
