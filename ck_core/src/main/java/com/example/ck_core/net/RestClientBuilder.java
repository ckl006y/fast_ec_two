package com.example.ck_core.net;

import com.example.ck_core.net.callback.IError;
import com.example.ck_core.net.callback.IFailure;
import com.example.ck_core.net.callback.IRequest;
import com.example.ck_core.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    private String mUrl;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mIrequest;
    private ISuccess mIsuccess;
    private IError mIerror;
    private IFailure mIfailure;
    private RequestBody mBody;


    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url){
        mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){

        PARAMS.put(key,value);
        return this;
    }


    public final RestClientBuilder raw(String raw){
        mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }


    public final RestClientBuilder success(ISuccess success){
        mIsuccess = success;

        return this;
    }

    public final RestClientBuilder failure (IFailure failure){
        mIfailure = failure;
        return this;
    }

    public final RestClientBuilder error (IError error){
        mIerror = error;
        return this;
    }


    private Map<String,Object>checkMaps(){

        return PARAMS;
    }

    public final RestClientBuilder request(IRequest request){
      mIrequest = request;
        return this;
    }


    public RestClient build(){
        return new RestClient(mUrl,PARAMS,mIrequest,mIsuccess,mIerror,mIfailure,mBody);
    }
}
