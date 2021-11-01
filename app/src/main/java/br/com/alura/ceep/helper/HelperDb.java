package br.com.alura.ceep.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.ceep.model.NotaEntity;

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

    public List<NotaEntity> buscaTodasNotas() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<NotaEntity> lista = new ArrayList<>();
        String query = "SELECT * FROM " + NOTA_TABLE;
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            NotaEntity nota = new NotaEntity(
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NOTA_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOTA_TITULO)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NOTA_DESCRICAO)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NOTA_COR)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_NOTA_POSICAO))
            );
            lista.add(nota);
        }
        cursor.close();
        return lista;
    }

    public boolean adcionaNota(NotaEntity nota) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NOTA_TITULO, nota.getTitulo());
        cv.put(COLUMN_NOTA_DESCRICAO, nota.getDescricao());
        cv.put(COLUMN_NOTA_COR, nota.getCor());
        cv.put(COLUMN_NOTA_POSICAO, nota.getPosicao());

        long insert = db.insert(NOTA_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}