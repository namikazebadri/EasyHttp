package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.RawContextTextResponseHandler;
import com.unisbadri.easyhttp.handlers.RawTextResponseHandler;
import com.unisbadri.easyhttp.interfaces.TextResponseCallback;

public class RawTextResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, TextResponseCallback callback) {
        sendGETRequest(uri, new RawTextResponseHandler(context, callback, actionCode));
    }

    public static void transportGET(Context context, String uri, int actionCode, TextResponseCallback callback) {
        sendGETRequest(uri, new RawContextTextResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new RawTextResponseHandler(context, callback, actionCode));
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new RawContextTextResponseHandler(context, callback, actionCode));
    }
}
