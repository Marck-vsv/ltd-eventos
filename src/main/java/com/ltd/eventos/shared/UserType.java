package com.ltd.eventos.shared;

public enum UserType { coordenador(0), professor(1), aluno(2);

    final int valor;

    UserType(int i) {
        this.valor = i;
    }
}
