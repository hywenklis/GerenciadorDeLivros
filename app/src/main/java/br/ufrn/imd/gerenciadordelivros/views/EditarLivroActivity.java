package br.ufrn.imd.gerenciadordelivros.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import br.ufrn.imd.gerenciadordelivros.R;
import br.ufrn.imd.gerenciadordelivros.data.LivroDAO;
import br.ufrn.imd.gerenciadordelivros.dominio.Livro;

public class EditarLivroActivity extends AppCompatActivity {

    private EditText edi_titulo;
    private EditText edi_autor;
    private EditText edi_editora;
    private CheckBox chk_emprestado;
    private LivroDAO livroDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);

        edi_titulo = findViewById(R.id.edt_titulo);
        edi_autor = findViewById(R.id.edt_autor);
        edi_editora = findViewById(R.id.edt_editora);
        chk_emprestado = findViewById(R.id.check_emprestado);

        livroDAO = LivroDAO.getInstance(this);
    }

    public void cancelar(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }

    public void processar(View view) {

        String titulo = edi_titulo.getText().toString();
        String autor = edi_autor.getText().toString();
        String editora = edi_editora.getText().toString();
        int emprestado = (chk_emprestado.isChecked()) ? 1 : 0;

        Livro livro = new Livro(titulo, autor, editora, emprestado);

        livroDAO.save(livro);

        String msg = "Livro adicionado com sucesso! ID="+livro.getId();
        setResult(RESULT_OK);
        finish();

    }
}