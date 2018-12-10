package com.unisbadri.easyhttp.interfaces;

public interface TypedResponseCallback<T>
{
    void onSuccess(int actionCode, T response) throws Exception;
    void onFailure(int actionCode, String responseCode, String responseDescription, Throwable throwable);
}