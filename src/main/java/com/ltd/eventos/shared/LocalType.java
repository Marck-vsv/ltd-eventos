package com.ltd.eventos.shared;

public enum LocalType { campus(0), externo(1);
    final int valor;

    LocalType(int i) {
        this.valor = i;
    }
}
