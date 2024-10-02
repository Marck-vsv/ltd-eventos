package com.ltd.eventos.usecases.interactor;

import com.ltd.eventos.domain.entities.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.model.UserDomain;
import com.ltd.eventos.infrastructure.db.repository.UserRepository;
import com.ltd.eventos.adapter.DTO.UserDTO.RequestUserDTO;
import com.ltd.eventos.adapter.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.adapter.DTO.UserDTO.UpdateUserDTO;
import com.ltd.eventos.usecases.exceptions.UsuarioNaoExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserUseCases {
  private final UserRepository userRepository;

  @Autowired
  public UserUseCases(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Transactional
  public UserDomain createUser(RequestUserDTO user) throws RuntimeException {
    UserDomain userDomain = new UserDomain(new UserBusinessRules(user));
    try {
      userRepository.save(userDomain);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return userDomain;
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
    userDomain.get().setUpdated_at(LocalDateTime.now());
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
