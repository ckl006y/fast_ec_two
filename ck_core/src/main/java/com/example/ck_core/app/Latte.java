package com.example.ck_core.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * 测试同步
 */
public final class Latte {
    public static Configurator init(Context context) {

        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    private static WeakHashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getConFigs();
    }
}
