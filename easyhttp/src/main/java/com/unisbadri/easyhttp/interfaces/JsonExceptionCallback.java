package com.unisbadri.easyhttp.interfaces;

public interface JsonExceptionCallback
{
    void handle(Exception e, int actionCode);
}