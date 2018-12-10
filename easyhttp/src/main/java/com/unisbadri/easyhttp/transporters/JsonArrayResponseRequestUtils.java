package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.ContextJsonArrayResponseHandler;
import com.unisbadri.easyhttp.handlers.JsonArrayResponseHandler;
import com.unisbadri.easyhttp.interfaces.JsonArrayResponseCallback;

import okhttp3.OkHttpClient;

public class JsonArrayResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, JsonArrayResponseCallback callback) {
        sendGETRequest(uri, new JsonArrayResponseHandler(context, callback, actionCode));
    }

    public static void transportGET(Context context, String uri, int actionCode, JsonArrayResponseCallback callback) {
        sendGETRequest(uri, new ContextJsonArrayResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(AppCompatActivity context, String uri, String request, int actionCode, JsonArrayResponseCallback callback) {
        sendPOSTRequest(uri, request, new JsonArrayResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(Context context, String uri, String request, int actionCode, JsonArrayResponseCallback callback) {
        sendPOSTRequest(uri, request, new ContextJsonArrayResponseHandler(context, callback, actionCode));
    }
}