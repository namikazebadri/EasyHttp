package com.unisbadri.easyhttp.handlers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.unisbadri.easyhttp.constants.ResponseCode;
import com.unisbadri.easyhttp.handlers.exceptions.DataFieldNullException;
import com.unisbadri.easyhttp.interfaces.JsonExceptionCallback;
import com.unisbadri.easyhttp.interfaces.TextResponseCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ContextTextResponseHandler implements Callback
{
    private Context context;
    private TextResponseCallback callback;
    private int actionCode;

    public ContextTextResponseHandler(Context context, TextResponseCallback callback, int actionCode)
    {
        this.context = context;
        this.callback = callback;
        this.actionCode = actionCode;
    }

    @Override
    public void onFailure(@NonNull Call call, @NonNull IOException e)
    {
        executeFailure(ResponseCode.NETWORK_ERROR, e.getMessage(), e);
    }

    @Override
    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException
    {
        if(context != null) {
            try {
                ResponseBody responseString = response.body();

                if(response.isSuccessful() && responseString != null) {
                    JSONObject responseJson = new JSONObject(responseString.string());

                    Log.d("EasyHttp", responseJson.toString());

                    if(responseJson.getString("rc").equals("00")) {
                        executeSuccess(responseJson);
                    } else {
                        executeFailure(responseJson.getString("rc"), responseJson.getString("rd"), null);
                    }
                } else {
                    executeFailure(ResponseCode.RESPONSE_UNSUCCESSFUL, response.message(), null);
                }
            } catch (final JSONException e) {
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

    private void executeSuccess(final JSONObject response)
    {
        new Runnable() {
            @Override
            public void run() {
                try {
                    if(!response.isNull("data")) {
                        if(response.get("data") instanceof JSONObject) {
                            callback.onSuccess(actionCode, response.getJSONObject("data").toString());
                        } else {
                            callback.onSuccess(actionCode, response.getJSONArray("data").toString());
                        }
                    } else {
                        throw new DataFieldNullException();
                    }
                } catch (final Exception e) {
                    if(callback instanceof JsonExceptionCallback) {
                        ((JsonExceptionCallback) callback).handle(e, actionCode);
                    } else {
                        callback.onFailure(actionCode, ResponseCode.RESPONSE_PARSE_ERROR, e.getMessage(), e);
                    }
                }
            }
        }.run();
    }
}