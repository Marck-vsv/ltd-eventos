package com.ltd.eventos.usecases.exceptions;

public class UsuarioNaoExiste extends RuntimeException {
    public UsuarioNaoExiste(String mensagem) {
        super(mensagem);
    }
}
