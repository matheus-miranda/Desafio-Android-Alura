package br.com.alura.ceep;

import android.app.Application;

import br.com.alura.ceep.helper.HelperDb;

public class CeepApplication extends Application {

    public HelperDb helperDb;
    public static CeepApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        helperDb = new HelperDb(this);
    }
}