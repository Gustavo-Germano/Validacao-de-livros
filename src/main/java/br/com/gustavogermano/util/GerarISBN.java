package br.com.gustavogermano.util;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe utilitária para gerar números ISBN simulados.
 */
public class GerarISBN {

    private static final String PREFIXO_ISBN = "777";

    /**
     * Gera um número ISBN aleatório com prefixo fixo.
     * @return String com 12 dígitos, começando por "123"
     */
    public static String gerar() {
        int numeroAleatorio = ThreadLocalRandom.current().nextInt(1000000000);
        // Formata com zeros à esquerda para garantir 9 dígitos
        String noveDigitos = String.format("%09d", numeroAleatorio);
        return PREFIXO_ISBN + noveDigitos;
    }
}

