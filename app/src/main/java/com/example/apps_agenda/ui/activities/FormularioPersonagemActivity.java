package com.example.apps_agenda.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apps_agenda.R;
import com.example.apps_agenda.dao.PersonagemDAO;
import com.example.apps_agenda.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //Chaves de Título
    public static final String NOVO_PERSONAGEM_TITLE = "Adicionar Personagem";
    public static final String EDITAR_PERSONAGEM_TITLE = "Editar Personagem";
    public static final String CHAVE_PERSONAGEM = "personagem";

    private EditText nome;
    private EditText altura;
    private EditText nascimento;
    private final PersonagemDAO dao = new PersonagemDAO();
    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);

        EncontraCampos();
        OnClickSalvar();
        PegaDados();
    }

    /*@Override
    protected void onResume()
    {
        super.onResume();
    }*/

    private void PegaDados()
    {
        Intent dados = getIntent();

        if (dados.hasExtra(CHAVE_PERSONAGEM))
        {
            setTitle(EDITAR_PERSONAGEM_TITLE);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            PreencheCampos();
        }

        else
        {
            setTitle(NOVO_PERSONAGEM_TITLE);
            personagem = new Personagem();
        }
    }

    private void PreencheCampos() {
        nome.setText(personagem.getSaveNome());
        altura.setText(personagem.getSaveAltura());
        nascimento.setText(personagem.getSaveNascimento());
    }

    private void OnClickSalvar()
    {
        Button botaoSalvar = findViewById(R.id.buttonSalvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FinalizaFormulario();
            }
        });
    }

    private void FinalizaFormulario()
    {
        PreenchePersonagem();
        if (personagem.ValidaID())
        {
            dao.Editar(personagem);
            finish();
        }
        else
        {
            dao.Salvar(personagem);
        }
        finish();
    }

    private void EncontraCampos()
    {
        nome = findViewById(R.id.textNome);
        altura = findViewById(R.id.textAltura);
        nascimento = findViewById(R.id.textNascimento);
    }

    private void PreenchePersonagem()
    {
        String saveNome = nome.getText().toString();
        String saveAltura = altura.getText().toString();
        String saveNascimento = nascimento.getText().toString();

        personagem.setSaveNome(saveNome);
        personagem.setSaveAltura(saveAltura);
        personagem.setSaveNascimento(saveNascimento);;
    }

}