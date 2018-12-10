package com.unisbadri.easyhttp.requests;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseRequest
{
    @SerializedName("accessToken")
    @Expose
    private String accessToken;

    @SerializedName("deviceToken")
    @Expose
    private String deviceToken;

    public BaseRequest()
    {
        //String accessToken = PreferenceStore.getString(Data.ACCESS_TOKEN, null);

        if(accessToken != null)
        {
            setAccessToken(accessToken);
        }
    }

    public String getDeviceToken()
    {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public void setAccessToken(String accessToken)
    {
        this.accessToken = accessToken;
    }
}
