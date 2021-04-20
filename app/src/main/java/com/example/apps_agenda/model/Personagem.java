package com.example.apps_agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable
{
    private String saveNome;
    private String saveAltura;
    private String saveNascimento;
    private int saveID = 0;

    public Personagem(String saveNome, String saveAltura, String saveNascimento)
    {
        this.saveNome = saveNome;
        this.saveAltura = saveAltura;
        this.saveNascimento = saveNascimento;
    }

    public Personagem()
    {
        //Construtor vazio para não dar erro ao deixar os campos vazios
    }

    public String getSaveNome()
    {
        return saveNome;
    }

    public void setSaveNome(String saveNome)
    {
        this.saveNome = saveNome;
    }

    public String getSaveAltura()
    {
        return saveAltura;
    }

    public void setSaveAltura(String saveAltura)
    {
        this.saveAltura = saveAltura;
    }

    public String getSaveNascimento()
    {
        return saveNascimento;
    }

    public void setSaveNascimento(String saveNascimento)
    {
        this.saveNascimento = saveNascimento;
    }

    @NonNull
    @Override
    public String toString()
    {
        return saveNome;
    }

    public void setSaveID(int saveID)
    {
        this.saveID = saveID;
    }

    public int getSaveID()
    {
        return saveID;
    }

    public boolean ValidaID()
    {
        return saveID > 0;
    }
}
