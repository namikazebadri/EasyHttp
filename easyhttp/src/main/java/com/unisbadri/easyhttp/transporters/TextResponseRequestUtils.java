package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.ContextTextResponseHandler;
import com.unisbadri.easyhttp.handlers.TextResponseHandler;
import com.unisbadri.easyhttp.interfaces.TextResponseCallback;

public class TextResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, TextResponseCallback callback) {
        sendGETRequest(uri, new TextResponseHandler(context, callback, actionCode));
    }

    public static void transportGET(Context context, String uri, int actionCode, TextResponseCallback callback) {
        sendGETRequest(uri, new ContextTextResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new TextResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new ContextTextResponseHandler(context, callback, actionCode));
    }
}
