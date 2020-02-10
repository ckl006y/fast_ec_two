package com.example.ck_core.net;

import com.example.ck_core.net.callback.IError;
import com.example.ck_core.net.callback.IFailure;
import com.example.ck_core.net.callback.IRequest;
import com.example.ck_core.net.callback.ISuccess;
import com.example.ck_core.net.callback.RequestCallbacks;

import java.util.Map;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RestClient {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest I_REQUEST;
    private final ISuccess I_SUCCESS;
    private final IError I_ERROR;
    private final IFailure I_FAILURE;
    private final RequestBody BODY;


    public RestClient(String URL,
                      Map<String, Object> params,
                      IRequest i_request,
                      ISuccess i_success,
                      IError i_error,
                      IFailure i_failure,
                      RequestBody body) {
        this.URL = URL;
        PARAMS.putAll(params);
        I_REQUEST = i_request;
        I_SUCCESS = i_success;
        I_ERROR = i_error;
        I_FAILURE = i_failure;
        this.BODY = body;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (I_REQUEST != null) {
            I_REQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL,PARAMS);
                break;
            case POST:
                call = service.post(URL,PARAMS);
                break;
            case POST_RAW:

                break;
            case PUT:
                call = service.put(URL,PARAMS);
                break;
            case PUT_RAW:
                break;
            case DELETE:
                call = service.delete(URL,PARAMS);
                break;
            case UPLOAD:
                break;
            default:
                break;
        }
        if(call!=null){
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback(){
        return new RequestCallbacks(I_REQUEST,I_SUCCESS,I_ERROR,I_FAILURE);
    }

    public final void get(){
        request(HttpMethod.GET);
    }

    public final void post(){
        request(HttpMethod.POST);
    }

    public final void put(){
        request(HttpMethod.PUT);
    }

    public final void delete(){
        request(HttpMethod.DELETE);
    }
}
