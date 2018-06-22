package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.ContextTextResponseHandler;
import com.unisbadri.easyhttp.handlers.TextResponseHandler;
import com.unisbadri.easyhttp.interfaces.TextResponseCallback;

import okhttp3.OkHttpClient;

public class TextResponseRequestUtils extends HttpRequestUtils
{
    public static void transportGET(AppCompatActivity context, String uri, int actionCode, TextResponseCallback callback) {
        transportGET(context, uri, actionCode, callback, null);
    }

    public static void transportGET(AppCompatActivity context, String uri, int actionCode, TextResponseCallback callback, OkHttpClient httpClient) {
        sendGETRequest(context, uri, new TextResponseHandler(context, callback, actionCode), httpClient);
    }

    public static void transportGET(Context context, String uri, int actionCode, TextResponseCallback callback) {
        transportGET(context, uri, actionCode, callback, null);
    }

    public static void transportGET(Context context, String uri, int actionCode, TextResponseCallback callback, OkHttpClient httpClient) {
        sendGETRequest(context, uri, new ContextTextResponseHandler(context, callback, actionCode), httpClient);
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        transportPOST(context, uri, requestString, actionCode, callback, null);
    }

    public static void transportPOST(AppCompatActivity context, String uri, String requestString, int actionCode, TextResponseCallback callback, OkHttpClient httpClient) {
        sendPOSTRequest(context, uri, requestString, new TextResponseHandler(context, callback, actionCode), httpClient);
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, TextResponseCallback callback) {
        transportPOST(context, uri, requestString, actionCode, callback, null);
    }

    public static void transportPOST(Context context, String uri, String requestString, int actionCode, TextResponseCallback callback, OkHttpClient httpClient) {
        sendPOSTRequest(context, uri, requestString, new ContextTextResponseHandler(context, callback, actionCode), httpClient);
    }
}
