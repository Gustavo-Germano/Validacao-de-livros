package br.com.gustavogermano.model;

import br.com.gustavogermano.exception.ErroLivroException;
import br.com.gustavogermano.util.GerarISBN;

public class Livro {

    static int proximoId = 1;

    private int id;
    private String titulo;
    private String autor;
    private int ano;
    private String isbn;

    public Livro() {
        this.id = proximoId++;
        this.isbn = GerarISBN.gerar();
    }

    public Livro(String titulo, String autor, int ano, String isbn) {
        this.id = proximoId++;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.isbn = GerarISBN.gerar();
    }

    public int getId() {
        return id;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    //Valida os campos obrigatórios do livro antes de adicioná-lo à lista
    public void validar() throws ErroLivroException {
        if (titulo == null || titulo.trim().isEmpty()) {
            proximoId--;
            throw new ErroLivroException("Por favor, coloque o título.");
        }
        if (autor == null || autor.trim().isEmpty()) {
            proximoId--;
            throw new ErroLivroException("Por favor, coloque o nome do autor.");
        }
        if (ano < 1500 || ano > 2025) {
            proximoId--;
            throw new ErroLivroException("Ano não válido.");
        }
    }
}