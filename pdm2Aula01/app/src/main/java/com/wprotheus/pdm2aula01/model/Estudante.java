package com.wprotheus.pdm2aula01.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

import lombok.Data;

@Data
public class Estudante implements Serializable {
    private String nome;
    private String disciplina;
    private int nota;

    public Estudante() {
    }

    public Estudante(String nome, String disciplina, int nota) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.nota = nota;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estudantes {\n")
            .append("Nome: ").append(nome).append('\n')
            .append("Disciplina: ").append(disciplina).append('\n')
            .append("Nota: ").append(nota).append(" }");
        return sb.toString();
    }
}