package com.unisbadri.easyhttp.transporters;

import android.support.v7.app.AppCompatActivity;

import com.unisbadri.easyhttp.handlers.TextResponseHandler;
import com.unisbadri.easyhttp.handlers.TypedResponseHandler;
import com.unisbadri.easyhttp.interfaces.TextResponseCallback;
import com.unisbadri.easyhttp.interfaces.TypedResponseCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class FIleUploadUtils
{
    private static final OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).readTimeout(60, TimeUnit.SECONDS).build();
    //private static final String url = StringUtils.getFromResource(R.string.muHttpUrl);

    public static void uploadFilesWithTextResponse(AppCompatActivity context, String uri, int actionCode, HashMap<String, File> uploadItems, TextResponseCallback callback) {
        String[] names = new String[uploadItems.size()];
        File[] files = new File[uploadItems.size()];

        int i = 0;
        for (Map.Entry<String, File> item : uploadItems.entrySet()) {
            names[i] = item.getKey();
            files[i] = item.getValue();

            i++;
        }

        Request request = buildUploadRequest(uri, names, files);

        client.newCall(request).enqueue(new TextResponseHandler(context, callback, actionCode));
    }

    public static void uploadFileWithTextResponse(AppCompatActivity context, String uri, int actionCode, String name, File file, TextResponseCallback callback) {
        Request request = buildUploadRequest(uri, new String[]{name}, new File[]{file});

        client.newCall(request).enqueue(new TextResponseHandler(context, callback, actionCode));
    }

    public static void uploadFileWithTypedResponse(AppCompatActivity context, String uri, int actionCode, String name, File file, Class responseClass, TypedResponseCallback callback) {
        Request request = buildUploadRequest(uri, new String[]{name}, new File[]{file});

        client.newCall(request).enqueue(new TypedResponseHandler(responseClass, context, callback, actionCode));
    }

    private static RequestBody buildFilesUploadRequestBody(String[] name, File[] files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        builder.setType(MultipartBody.FORM);

        int i = 0;
        for (File file : files) {
            builder.addFormDataPart(name[i], file.getName(), RequestBody.create(MediaType.parse("*/*"), file));
            i++;
        }

        return builder.build();
    }

    private static Request buildUploadRequest(String uri, String[] names, File[] files) {
        return new Request.Builder()
            //.url(url + uri)
            .post(buildFilesUploadRequestBody(names, files))
            .build();
    }
}
