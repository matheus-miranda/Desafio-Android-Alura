package br.com.alura.ceep.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class HelperDb extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "ceep.db";
    private static final int VERSAO_BANCO = 1;
    private static final String NOTA_TABLE = "nota_table";
    private static final String COLUMN_NOTA_ID = "nota_id";
    private static final String COLUMN_NOTA_TITULO = "nota_titulo";
    private static final String COLUMN_NOTA_DESCRICAO = "nota_descricao";
    private static final String COLUMN_NOTA_COR = "nota_cor";
    private static final String COLUMN_NOTA_POSICAO = "nota_posicao";

    public HelperDb(@Nullable Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable =
                "CREATE TABLE " + NOTA_TABLE + " (" +
                        COLUMN_NOTA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NOTA_TITULO + " TEXT, " +
                        COLUMN_NOTA_DESCRICAO + " TEXT, " +
                        COLUMN_NOTA_COR + " INTEGER, " +
                        COLUMN_NOTA_POSICAO + " INTEGER)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + NOTA_TABLE;

        if (oldVersion != newVersion) {
            db.execSQL(dropTable);
            onCreate(db);
        }
    }
}