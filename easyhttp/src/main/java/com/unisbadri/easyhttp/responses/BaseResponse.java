package com.unisbadri.easyhttp.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseResponse
{
    @Expose
    @SerializedName("rc")
    private String rc;

    @Expose
    @SerializedName("rd")
    private String rd;

    @Expose
    @SerializedName("accessToken")
    private String accessToken;

    public String getRC()
    {
        return rc;
    }

    public void setRC(String rc)
    {
        this.rc = rc;
    }

    public String getRD()
    {
        return rd;
    }

    public void setRD(String rd)
    {
        this.rd = rd;
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
