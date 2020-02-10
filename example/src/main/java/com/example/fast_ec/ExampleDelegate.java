package com.example.fast_ec;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.ck_core.app.ConfigType;
import com.example.ck_core.app.Configurator;
import com.example.ck_core.app.Latte;
import com.example.ck_core.delegates.LatteDelegate;
import com.example.ck_core.net.RestClient;
import com.example.ck_core.net.callback.IError;
import com.example.ck_core.net.callback.IFailure;
import com.example.ck_core.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

public class ExampleDelegate extends LatteDelegate {

    @BindView(R.id.btn_connect)
    Button btn_connect;


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
//        checkRestClient();
//        String api = Configurator.getInstance().getConfiguration(ConfigType.API_HOST);
//        if(api!=null){
//            Toast.makeText(getContext(),api,Toast.LENGTH_LONG).show();
//        }
        checkRestClient();

    }

    private void checkRestClient() {
        RestClient.builder()
                .url(Configurator.getInstance().getConfiguration(ConfigType.API_HOST)+"api")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Toast.makeText(getContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int error_code, String reason) {

                    }
                })
                .build()
                .get();
    }

    @OnClick(R.id.btn_connect)
    public void onClick(){
        checkRestClient();
    }
}
