package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.ContextJsonObjectResponseHandler;
import com.unisbadri.easyhttp.handlers.JsonObjectResponseHandler;
import com.unisbadri.easyhttp.interfaces.JsonObjectResponseCallback;

public class JsonObjectResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, JsonObjectResponseCallback callback) {
        sendGETRequest(uri, new JsonObjectResponseHandler(context, callback, actionCode));
    }

    public static void transportGET(Context context, String uri, int actionCode, JsonObjectResponseCallback callback) {
        sendGETRequest(uri, new ContextJsonObjectResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, JsonObjectResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new JsonObjectResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, JsonObjectResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new ContextJsonObjectResponseHandler(context, callback, actionCode));
    }
}
