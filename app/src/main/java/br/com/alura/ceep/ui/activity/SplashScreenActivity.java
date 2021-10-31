package br.com.alura.ceep.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.ceep.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final String SHARED_PREFS = "shared_prefs";
    private static final String LOGIN_KEY = "login_prefs";
    private SharedPreferences preferences;
    private final Handler waitHandler = new Handler();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        checkSePrimeiroLogin();
    }

    private void checkSePrimeiroLogin() {
        if (primeiroLogin()) {
            aguardaDoisSegundos();
            salvaNoSharedPrefs();
        } else {
            aguardaMeioSegundo();
        }
    }

    private boolean primeiroLogin() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        return preferences.getBoolean(LOGIN_KEY, true);
    }

    private void aguardaDoisSegundos() {
        waitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    navegaParaListaDeNotas();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 2000);
    }

    private void salvaNoSharedPrefs() {
        preferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LOGIN_KEY, false);
        editor.apply();
    }

    private void aguardaMeioSegundo() {
        waitHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    navegaParaListaDeNotas();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 500);
    }

    private void navegaParaListaDeNotas() {
        Intent intent = new Intent(this, ListaNotasActivity.class);
        startActivity(intent);
        finish();
    }
}