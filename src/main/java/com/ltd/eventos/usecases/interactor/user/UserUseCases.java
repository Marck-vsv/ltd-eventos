package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.infrastructure.db.repository.UserRepository;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;
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
public class UserUseCases implements IUserUseCases {
    private final UserRepository userRepository;

    @Autowired
    public UserUseCases(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDomain createUser(UserBusinessRules user) throws RuntimeException {
        UserDomain userDomain = new UserDomain(user);
        userDomain.setCreated_at(LocalDateTime.now());
        try {
            userRepository.save(userDomain);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDomain;
    }

    @Override
    @Transactional
    public String deleteUser(String id) throws UsuarioNaoExiste {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UsuarioNaoExiste("Usuario com o ID: " + id + " não encontrado.");
        }
        return id;
    }

    @Override
    @Transactional
    public Optional<UserDomain> updateUser(UpdateUserDTO user) throws UsuarioNaoExiste, IllegalArgumentException {
        if (user.username()  == null) {
            throw new IllegalArgumentException("Username de usuário não pode estar vazio.");
        }
        Optional<UserDomain> userDomain = Optional.ofNullable(userRepository.findByUsername(user.username()));

        if (userDomain.isEmpty()) {
            throw new UsuarioNaoExiste("Usuário com username: " + user + " não existe no banco de dados.");
        }

        if (user.usernameNovo() != null) {
            userDomain.get().setUsername(user.usernameNovo());
        }

        if (user.senha() != null) {
            userDomain.get().setSenha(user.senha());
        }
        userDomain.get().setUpdated_at(LocalDateTime.now());
        userRepository.save(userDomain.get());
        return userDomain;
    }

    @Override
    public Optional<ResponseUserDTO> findByMatricula(String matricula) throws UsuarioNaoExiste {
        Optional<UserDomain> user = Optional.ofNullable(userRepository.findByMatricula(matricula));
        if (user.isEmpty()) {
            throw new UsuarioNaoExiste("Usuário não existe no banco de dados.");
        }
        return Optional.of(new ResponseUserDTO(
                user.get().getUsername(),
                user.get().getMatricula(),
                user.get().getUser_type(),
                user.get().getCreated_at(),
                user.get().getUpdated_at(),
                user.get().getEvento_evento_id()
        ));
    }

    @Override
    public List<ResponseUserDTO> findAll() {
        Iterable<UserDomain> usersIterable = userRepository.findAll();

        return StreamSupport.stream(usersIterable.spliterator(), true).map(ResponseUserDTO::new).collect(Collectors.toList());
    }
}
