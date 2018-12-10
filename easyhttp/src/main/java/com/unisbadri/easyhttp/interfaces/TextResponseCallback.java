package com.unisbadri.easyhttp.interfaces;

public interface TextResponseCallback
{
    void onSuccess(int actionCode, String response) throws Exception;
    void onFailure(int actionCode, String responseCode, String responseDescription, Throwable throwable);
}