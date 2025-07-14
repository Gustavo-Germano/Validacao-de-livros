package br.com.gustavogermano.exception;

/**
 * Classe que representa exceções específicas do domínio Livro.
 * Lançada quando dados inválidos são detectados durante o cadastro.
 */
public class ErroLivroException extends Exception {

    /**
     * Construtor da exceção personalizada.
     * @param detalhe Mensagem descritiva do erro ocorrido.
     */
    public ErroLivroException(String detalhe) {
        super(detalhe);
    }
}

