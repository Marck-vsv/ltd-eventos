package com.ltd.eventos.usecases.exceptions;

public class LocalNaoExiste extends RuntimeException {
  public LocalNaoExiste(String message) {
    super(message);
  }
}
