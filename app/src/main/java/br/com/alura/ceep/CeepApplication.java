package br.com.alura.ceep;

import android.app.Application;

import br.com.alura.ceep.helper.HelperDb;

public class CeepApplication extends Application {

    HelperDb db;

    @Override
    public void onCreate() {
        super.onCreate();

        db = new HelperDb(this);
    }
}