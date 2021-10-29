package br.com.alura.ceep.ui.activity;

import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.CHAVE_POSICAO;
import static br.com.alura.ceep.ui.activity.NotaActivityConstantes.POSICAO_INVALIDA;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.ceep.R;
import br.com.alura.ceep.model.Nota;
import br.com.alura.ceep.ui.recyclerview.formularionota.Cores;
import br.com.alura.ceep.ui.recyclerview.formularionota.adapter.FormularioNotaAdapter;

public class FormularioNotaActivity extends AppCompatActivity implements FormularioNotaAdapter.OnColorClickListener {

    public static final String TITULO_APPBAR_INSERE = "Insere nota";
    public static final String TITULO_APPBAR_ALTERA = "Altera nota";
    private int posicaoRecibida = POSICAO_INVALIDA;
    private TextView titulo;
    private TextView descricao;
    private RecyclerView rvCores;
    private List<Integer> cores;
    private ConstraintLayout rootLayout;
    private Nota nota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);

        setTitle(TITULO_APPBAR_INSERE);
        inicializaCampos();
        configuraRecyclerView();
        recuperaIntent();
    }

    private void inicializaCampos() {
        rootLayout = findViewById(R.id.formulario_nota_root);
        rvCores = findViewById(R.id.formulario_rv_cores);
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    private void configuraRecyclerView() {
        FormularioNotaAdapter adapter = configuraAdapter();
        rvCores.setAdapter(adapter);
    }

    private void recuperaIntent() {
        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_NOTA)) {
            setTitle(TITULO_APPBAR_ALTERA);
            nota = (Nota) dadosRecebidos.getSerializableExtra(CHAVE_NOTA);
            posicaoRecibida = dadosRecebidos.getIntExtra(CHAVE_POSICAO, POSICAO_INVALIDA);
            preencheCampos();
        } else {
            nota = new Nota("", "", -1);
        }
    }

    private FormularioNotaAdapter configuraAdapter() {
        cores = new ArrayList<>();
        for (Cores cor : Cores.values()) {
            cores.add(Color.parseColor(cor.corString));
        }
        return new FormularioNotaAdapter(this, cores, this::onColorClick);
    }

    private void preencheCampos() {
        rootLayout.setBackgroundColor(nota.getCor());
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota_salva, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (ehMenuSalvaNota(item)) {
            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota notaCriada) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, notaCriada);
        resultadoInsercao.putExtra(CHAVE_POSICAO, posicaoRecibida);
        setResult(Activity.RESULT_OK, resultadoInsercao);
    }

    @NonNull
    private Nota criaNota() {
        return new Nota(
                titulo.getText().toString(),
                descricao.getText().toString(),
                nota.getCor()
        );
    }

    private boolean ehMenuSalvaNota(MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_nota_ic_salva;
    }

    /**
     * Quando uma cor é clicada, este método é chamado
     *
     * @param posicao da cor no adapter
     */
    @Override
    public void onColorClick(int posicao) {
        rootLayout.setBackgroundColor(cores.get(posicao));
        nota.setCor(cores.get(posicao));
    }
}
