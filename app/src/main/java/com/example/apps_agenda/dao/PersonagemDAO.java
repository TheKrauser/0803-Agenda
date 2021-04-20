package com.example.apps_agenda.dao;

import androidx.collection.ArraySet;

import com.example.apps_agenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO
{
    private final static List<Personagem> personagens = new ArrayList<>();
    private static int countID = 1;

    public void Salvar(Personagem personagemSalvo)
    {
        personagemSalvo.setSaveID(countID);
        personagens.add(personagemSalvo);
        countID++;
    }

    public void Editar(Personagem personagem)
    {
        Personagem personagemClicado = PegaID(personagem);

        if (personagemClicado != null)
        {
            int posicaoPersonagem = personagens.indexOf(personagemClicado);
            personagens.set(posicaoPersonagem, personagem);
        }
    }

    private Personagem PegaID(Personagem personagem)
    {
        for (Personagem p : personagens)
        {
            if (p.getSaveID() == personagem.getSaveID());
            {
                return p;
            }
        }
        return null;
    }

    public List<Personagem> GetInfo()
    {
        return new ArrayList<>(personagens);
    }
}
