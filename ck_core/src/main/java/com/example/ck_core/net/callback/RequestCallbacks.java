package com.example.ck_core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestCallbacks implements Callback<String> {

    private final IRequest I_REQUEST;
    private final ISuccess I_SUCCESS;
    private final IError I_ERROR;
    private final IFailure I_FAILURE;


    public RequestCallbacks(IRequest i_request, ISuccess i_success, IError i_error, IFailure i_failure) {
        I_REQUEST = i_request;
        I_SUCCESS = i_success;
        I_ERROR = i_error;
        I_FAILURE = i_failure;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
            if(call.isExecuted()){
                if(I_SUCCESS!=null){
                    I_SUCCESS.onSuccess(response.body());
                }
            }
        }else {
            if(I_ERROR!=null){
                I_ERROR.onError(response.code(),response.message());
            }
        }

        if(I_REQUEST!=null){
            I_REQUEST.onRequestEnd();
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if(I_FAILURE!=null){
            I_FAILURE.onFailure();
        }

        if(I_REQUEST!=null){
            I_REQUEST.onRequestEnd();
        }
    }
}
