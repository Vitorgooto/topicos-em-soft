package com.tabalho.primeiro.models;

import jakarta.persistence.*;

@Entity
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String titulo;
    private String autor;
    
    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIVEL;

    public BookModel() {}

    public BookModel(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }
    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}

