package com.unisbadri.easyhttp.transporters;

import android.content.Context;
import android.util.Log;

import com.unisbadri.easyhttp.R;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpRequestUtils
{
    private static final OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    protected static String baseUrl;

    //private static OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(IntegerUtils.getFromResource(R.integer.muHttpConnectTimeout), TimeUnit.SECONDS).writeTimeout(IntegerUtils.getFromResource(R.integer.muHttpWriteTimeout), TimeUnit.SECONDS).readTimeout(IntegerUtils.getFromResource(R.integer.muHttpReadTimeout), TimeUnit.SECONDS).build();
    //private static MediaType mediaType = MediaType.parse(StringUtils.getFromResource(R.string.muHttpMediaType));
    //private static String baseUrl = StringUtils.getFromResource(R.string.muHttpUrl);

    static void sendGETRequest(String uri, Callback callback) {
        Request.Builder request = buildRequest(uri);

        client.newCall(request.build()).enqueue(callback);
    }

    static void sendPOSTRequest(String uri, String requestString, Callback callback) {
        RequestBody body = RequestBody.create(JSON, requestString);

        Request.Builder request = buildRequest(uri).post(body);

        //Log.d("EasyHttp", context.getResources().getString(R.string.muHttpDebugBody, requestString));

        client.newCall(request.build()).enqueue(callback);
    }

    private static Request.Builder buildRequest(String uri) {
        //Log.d("EasyHttp", context.getResources().getString(R.string.muHttpDebugUrl, baseUrl + uri));

        return new Request.Builder()
            //.header(context.getResources().getString(R.string.muHttpAcceptEncodingKey), context.getResources().getString(R.string.muHttpAcceptEncodingValue))
            //.header(context.getResources().getString(R.string.muHttpContentTypeKey), context.getResources().getString(R.string.muHttpContentTypeValue))
            //.header(context.getResources().getString(R.string.muHttpAuthorizationKey), context.getResources().getString(R.string.muHttpAuthorizationValue, PreferenceStore.getString(BData.BEARER_TOKEN, StringUtils.getEmptyString())))
            .url(baseUrl + uri);
    }
}