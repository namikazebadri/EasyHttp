package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.ContextTypedResponseHandler;
import com.unisbadri.easyhttp.handlers.TypedResponseHandler;
import com.unisbadri.easyhttp.interfaces.TypedResponseCallback;

public class TypedResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, Class responseClassType, TypedResponseCallback callback) {
        sendGETRequest(uri, new TypedResponseHandler(responseClassType, context, callback, actionCode));
    }

    public static void transportGET(Context context, String uri, int actionCode, Class responseClassType, TypedResponseCallback callback) {
        sendGETRequest(uri, new ContextTypedResponseHandler(responseClassType, context, callback, actionCode));
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, Class responseClass, TypedResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new TypedResponseHandler(responseClass, context, callback, actionCode));
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, Class responseClass, TypedResponseCallback callback) {
        sendPOSTRequest(uri, requestString, new ContextTypedResponseHandler(responseClass, context, callback, actionCode));
    }
}
