package com.ltd.eventos.usecases.exceptions;

public class EventoNaoExiste extends RuntimeException {
  public EventoNaoExiste(String message) {
    super(message);
  }
}
