package com.example.apps_agenda.ui.activities;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apps_agenda.R;
import com.example.apps_agenda.dao.PersonagemDAO;
import com.example.apps_agenda.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity
{
    //Títulos gravados em uma variável estática para fácil uso ao invés de usar strings Hard Coded
    public static final String LISTA_TITLE = "Lista de Personagens";
    public static final String CHAVE_PERSONAGEM = "personagem";

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

    //Remoção de Item
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //Pega a posição do personagem na lista
        Personagem personagemClicado = adapter.getItem(menuInfo.position);
        //Remove ele da lista
        adapter.remove(personagemClicado);
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

    private void EditarFormulario(Personagem personagemClicado)
    {
        //Pega a informação da Activity do formulário
        Intent formulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        formulario.putExtra(CHAVE_PERSONAGEM, personagemClicado);
        //Vai para o formulário
        startActivity(formulario);
    }

}
