package com.example.ck_core.app;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;

public class Configurator {

    private static final HashMap<String,Object> LATTE_CONFIGS = new HashMap<>();

    public Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final HashMap<String,Object> getConFigs(){
        return LATTE_CONFIGS;
    }

    public static class Holder{
        private static final Configurator INSTANCE = new Configurator();

    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public final Configurator withApiHost(String apiHost){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),apiHost);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if(!isReady){
            throw new RuntimeException("未完成初始化!");
        }
    }

    public final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }



}
