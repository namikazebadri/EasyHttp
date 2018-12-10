package com.unisbadri.easyhttp.handlers.exceptions;

public class DataFieldNullException extends Exception
{
    public DataFieldNullException()
    {
        super("Field 'data' is null on the received response.");
    }
}
