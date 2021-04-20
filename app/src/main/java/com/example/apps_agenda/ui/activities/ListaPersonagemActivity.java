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
    public static final String LISTA_TITLE = "Lista de Personagens";
    public static final String CHAVE_PERSONAGEM = "personagem";
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
        adapter.clear();
        adapter.addAll(dao.GetInfo());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Personagem personagemClicado = adapter.getItem(menuInfo.position);
        adapter.remove(personagemClicado);
        return super.onContextItemSelected(item);
    }

    private void OnClickAdd()
    {
        FloatingActionButton botaoAdd = findViewById(R.id.fab_add);
        botaoAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Abre Formulário
                startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
            }
        });
    }

    private void ConfiguraLista()
    {
        ListView listaDePersonagens = findViewById(R.id.main_list_personagens);
        ListaDePersonagens(listaDePersonagens);
        ConfiguraItem(listaDePersonagens);
        registerForContextMenu(listaDePersonagens);
    }

    /*private void ConfiguraLista2(ListView listaDePersonagens, List<Personagem> personagens)
    {
        listaDePersonagens.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Personagem personagemClicado = personagens.get(position);
                Intent formulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                formulario.putExtra(CHAVE_PERSONAGEM, personagemClicado);
                startActivity(formulario);
            }
        });
    }*/

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
                Personagem personagemClicado = (Personagem) adapterView.getItemAtPosition(position);
                EditarFormulario(personagemClicado);
            }
        });
    }

    private void EditarFormulario(Personagem personagemClicado)
    {
        Intent formulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        formulario.putExtra(CHAVE_PERSONAGEM, personagemClicado);
        startActivity(formulario);
    }

}
