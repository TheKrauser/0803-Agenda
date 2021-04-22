package com.example.apps_agenda.dao;

import com.example.apps_agenda.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO
{
    private final static List<Personagem> personagens = new ArrayList<>();
    //ID para definir a posição do Personagem na Lista
    private static int countID = 1;

    //Salva o personagem na lista
    public void Salvar(Personagem personagemSalvo)
    {
        //Atribui o ID atual
        personagemSalvo.setSaveID(countID);
        //Salva
        personagens.add(personagemSalvo);
        //Incrementa 1 no ID para o próximo que for salvo
        countID ++;
    }

    //Editar o personagem selecionado
    public void Editar(Personagem personagem)
    {
        //Atribui o personagem certo a editar
        Personagem personagemClicado = PegaID(personagem);

        //Se não for null
        if (personagemClicado != null)
        {
            //Se não for "NULL" pega a posição do personagem na lista
            int posicaoPersonagem = personagens.indexOf(personagemClicado);
            //Realiza a edição
            personagens.set(posicaoPersonagem, personagem);
        }
    }

    //Pega o personagem certo baseado no ID
    private Personagem PegaID(Personagem personagem)
    {
        for (Personagem p : personagens)
        {
            if (p.getSaveID() == personagem.getSaveID());
            {
                //Se for o ID certo, retorne o personagem referente
                return p;
            }
        }
        //Retorno "NULL" caso não haja um personagem com o ID
        return null;
    }

    //Retorna a lista dos personagens
    public List<Personagem> GetInfo()
    {
        return new ArrayList<>(personagens);
    }

    public void Remover(Personagem personagem)
    {
        Personagem personagemDeletado = PegaID(personagem);

        if (personagemDeletado != null)
        {
            personagens.remove(personagemDeletado);
        }
    }
}
