package com.unisbadri.easyhttp.handlers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.unisbadri.easyhttp.constants.ResponseCode;
import com.unisbadri.easyhttp.interfaces.JsonArrayResponseCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ContextJsonArrayResponseHandler implements Callback
{
    private Context context;
    private JsonArrayResponseCallback callback;
    private int actionCode;

    public ContextJsonArrayResponseHandler(Context context, JsonArrayResponseCallback callback, int actionCode)
    {
        this.context = context;
        this.callback = callback;
        this.actionCode = actionCode;
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull final IOException e)
    {
        executeFailure(ResponseCode.NETWORK_ERROR, e.getMessage(), e);
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response)
    {
        if(context != null) {
            try {
                ResponseBody responseString = response.body();

                if(response.isSuccessful() && responseString != null) {
                    JSONObject responseJson = new JSONObject(responseString.string());

                    Log.d("EasyHttp", responseJson.toString());

                    if(responseJson.getString("rc").equals("00")) {
                        executeSuccess(responseJson.getJSONArray("data"));
                    } else {
                        executeFailure(responseJson.getString("rc"), responseJson.getString("rd"), null);
                    }
                } else {
                    executeFailure(ResponseCode.RESPONSE_UNSUCCESSFUL, response.message(), null);
                }
            } catch (Exception e) {
                executeFailure(ResponseCode.RESPONSE_PARSE_ERROR, e.getMessage(), e);
            }
        }
    }

    private void executeFailure(final String rc, final String rd, final Throwable e)
    {
        if(context != null) {
            new Runnable() {
                @Override
                public void run() {
                    callback.onFailure(actionCode, rc, rd, e);
                }
            }.run();
        }
    }

    private void executeSuccess(final JSONArray response)
    {
        if(context != null) {
            new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(actionCode, response);
                }
            }.run();
        }
    }
}