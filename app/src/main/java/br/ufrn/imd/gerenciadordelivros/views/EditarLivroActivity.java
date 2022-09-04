package br.ufrn.imd.gerenciadordelivros.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.ufrn.imd.gerenciadordelivros.R;
import br.ufrn.imd.gerenciadordelivros.data.LivroDAO;
import br.ufrn.imd.gerenciadordelivros.dominio.Livro;

public class EditarLivroActivity extends AppCompatActivity {

    private EditText edi_titulo;
    private EditText edi_autor;
    private EditText edi_editora;
    private CheckBox chk_emprestado;
    private LivroDAO livroDAO;

    private Livro livro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_livro);

        edi_titulo = findViewById(R.id.edt_titulo);
        edi_autor = findViewById(R.id.edt_autor);
        edi_editora = findViewById(R.id.edt_editora);
        chk_emprestado = findViewById(R.id.check_emprestado);

        livroDAO = LivroDAO.getInstance(this);

        livro = (Livro) getIntent().getSerializableExtra("livro");

        if(livro != null){
            edi_titulo.setText(livro.getTitulo());
            edi_autor.setText(livro.getAutor());
            edi_editora.setText(livro.getEditora());
            chk_emprestado.setChecked(livro.getEmprestado() == 1);
        }
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

        String msg;

        if (livro == null) {
            Livro livro = new Livro(titulo, autor, editora, emprestado);
            livroDAO.save(livro);
            msg = "Livro adicionado com sucesso! ID= "+livro.getId();
        } else {
            livro.setTitulo(titulo);
            livro.setAutor(autor);
            livro.setEditora(editora);
            livro.setEmprestado(emprestado);

            livroDAO.update(livro);
            msg = "Livro atualizado com sucesso! ID= "+livro.getId();
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();

    }
}