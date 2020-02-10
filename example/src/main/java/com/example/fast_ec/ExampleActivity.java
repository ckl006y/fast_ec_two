package com.example.fast_ec;


import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ck_core.activities.ProxyActivity;
import com.example.ck_core.app.ConfigType;
import com.example.ck_core.app.Configurator;
import com.example.ck_core.app.Latte;
import com.example.ck_core.delegates.LatteDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExampleActivity extends ProxyActivity {

    @Override
    public LatteDelegate setRootDeleagate() {
        return new ExampleDelegate();
    }
}
