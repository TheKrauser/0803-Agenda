package com.example.apps_agenda.ui.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apps_agenda.R;
import com.example.apps_agenda.dao.PersonagemDAO;
import com.example.apps_agenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListaPersonagemActivity extends AppCompatActivity
{
    //Títulos gravados em uma variável estática para fácil uso ao invés de usar strings Hard Coded
    public static final String LISTA_TITLE = "Lista de Personagens";

    //Variável do personagem DAO e adapter
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagem);
        setTitle(LISTA_TITLE);

        OnClickAdd();
        ConfiguraLista();
    }


    @Override
    protected void onResume()
    {
        super.onResume();
        //Limpa a lista ao dar resume
        adapter.clear();
        //Retorna todos os valores do Array de Personagens
        adapter.addAll(dao.GetInfo());
    }

    private void Remover(Personagem personagem)
    {
        //Remove o personagem
        dao.Remover(personagem);
        adapter.remove(personagem);
    }

    //Remoção de Item
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
    }

    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        int itemID = item.getItemId();
        if (itemID == R.id.activity_lista_personagem_menu_remover)
        {
            //Cria o menu de Remover Personagem
            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Deseja Remover")
                    //Opção Sim
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            //Pega o personagem que foi clicado
                            Personagem personagemClicado = adapter.getItem(menuInfo.position);
                            //Remove
                            Remover(personagemClicado);
                        }
                    })
                    //Opção Não
                    .setNegativeButton("Não", null)
                    //Mostra uma mensagem sem nada
                    .show();
        }

        return super.onContextItemSelected(item);
    }

    private void OnClickAdd()
    {
        //Encontra o botão de adicionar
        FloatingActionButton botaoAdd = findViewById(R.id.fab_add);
        botaoAdd.setOnClickListener(new View.OnClickListener()
        {
            //Abre Formulário
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
    }

    private void ConfiguraLista()
    {
        //Encontra a main list de personagens
        ListView listaDePersonagens = findViewById(R.id.main_list_personagens);
        //Atribui a lista
        ListaDePersonagens(listaDePersonagens);
        //Configura para editar o personagem clicado
        ConfiguraItem(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    private void ListaDePersonagens(ListView listaDePersonagens)
    {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }

    private void ConfiguraItem(ListView listaDePersonagens)
    {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                //Atribui o personagem na posição
                Personagem personagemClicado = (Personagem) adapterView.getItemAtPosition(position);
                //Edita o personagem atribuido
                EditarFormulario(personagemClicado);
            }
        });
    }

    private void EditarFormulario(Personagem personagem)
    {
        //Pega a informação da Activity do formulário
        Intent formulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        formulario.putExtra(ConstantesActivities.CHAVE_PERSONAGEM, personagem);
        //Vai para o formulário
        startActivity(formulario);
    }

}
