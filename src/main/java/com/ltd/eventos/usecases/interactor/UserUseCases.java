package com.ltd.eventos.usecases.interactor;

import com.ltd.eventos.domain.entities.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.model.EventoDomain;
import com.ltd.eventos.infrastructure.db.model.UserDomain;
import com.ltd.eventos.infrastructure.db.repository.EventoRepository;
import com.ltd.eventos.infrastructure.db.repository.UserRepository;
import com.ltd.eventos.adapter.dto.userdto.RequestUserDTO;
import com.ltd.eventos.adapter.dto.userdto.ResponseUserDTO;
import com.ltd.eventos.adapter.dto.userdto.UpdateUserDTO;
import com.ltd.eventos.usecases.exceptions.UsuarioNaoExiste;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserUseCases {
  private final UserRepository userRepository;
  private final EventoRepository eventoRepository;

  @Autowired
  public UserUseCases(UserRepository userRepository, EventoRepository eventoRepository) {
    this.userRepository = userRepository;
    this.eventoRepository = eventoRepository;
  }

  @Transactional
  public UserDomain createUser(RequestUserDTO user) throws RuntimeException {
    // Instancia as regras de negócio
    UserBusinessRules userBusinessRules = new UserBusinessRules(user);

    // Cria a entidade UserDomain (sem eventos inicialmente)
    UserDomain userDomain = new UserDomain(userBusinessRules);

    try {
      // Salva o UserDomain primeiro no banco de dados
      userDomain = userRepository.save(userDomain);

      // Verifica se o usuário possui eventos
      if (user.eventos() != null && !user.eventos().isEmpty()) {
        // Busca cada evento pelo ID e associa o UserDomain
        UserDomain finalUserDomain = userDomain;
        List<EventoDomain> eventoDomainList = user.eventos().stream()
            .map(eventoId -> eventoRepository.findById(eventoId)  // Busca o evento por ID
                .orElseThrow(() -> new EntityNotFoundException("Evento com ID " + eventoId + " não encontrado"))) // Lança exceção se não encontrar
            .peek(evento -> evento.setUser_id(finalUserDomain))  // Associa o evento ao UserDomain já salvo
            .collect(Collectors.toList());  // Coleta os eventos modificados

        // Agora, salva os eventos atualizados
        eventoRepository.saveAll(eventoDomainList);

        // Atualiza a entidade UserDomain com a lista de eventos associada
        userDomain.setEvento_evento_id(eventoDomainList);
      }

      // Retorna o UserDomain já salvo
      return userRepository.save(userDomain);

    } catch (EntityNotFoundException e) {
      throw new RuntimeException("Erro ao criar usuário: evento não encontrado", e);
    } catch (Exception e) {
      throw new RuntimeException("Erro ao criar usuário", e);
    }
  }

  @Transactional
  public String deleteUser(String id) throws UsuarioNaoExiste {
    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
    } else {
      throw new UsuarioNaoExiste("Usuario com o ID: " + id + " não encontrado.");
    }
    return id;
  }

  @Transactional
  public UserDomain updateUser(UpdateUserDTO user) throws UsuarioNaoExiste, IllegalArgumentException {
    if (user.username() == null) {
      throw new IllegalArgumentException("Username de usuário não pode estar vazio.");
    }
    Optional<UserDomain> userDomain = Optional.ofNullable(userRepository.findByUsername(user.username()));

    if (userDomain.isEmpty()) {
      throw new UsuarioNaoExiste("Usuário com username: " + user.username() + " não existe no banco de dados.");
    }
    if (user.usernameNovo() != null) {
      userDomain.get().setUsername(user.usernameNovo());
    }
    if (user.senha() != null) {
      userDomain.get().setSenha(user.senha());
    }
    userRepository.save(userDomain.get());
    return userDomain.get();
  }

  public UserDomain findByMatricula(String matricula) throws UsuarioNaoExiste {
    Optional<UserDomain> user = Optional.ofNullable(userRepository.findByMatricula(matricula));
    if (user.isEmpty()) {
      throw new UsuarioNaoExiste("Usuário não existe no banco de dados.");
    }
    return user.get();
  }

  public List<ResponseUserDTO> findAll() {
    return StreamSupport.stream(userRepository.findAll().spliterator(), true).map(ResponseUserDTO::new).collect(Collectors.toList());
  }
}
