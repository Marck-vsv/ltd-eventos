package com.ltd.eventos.usecases.interactor.user;

import com.ltd.eventos.domain.entities.user.UserBusinessRules;
import com.ltd.eventos.infrastructure.db.entities.UserDomain;
import com.ltd.eventos.infrastructure.db.repository.UserRepository;
import com.ltd.eventos.usecases.DTO.UserDTO.ResponseUserDTO;
import com.ltd.eventos.usecases.DTO.UserDTO.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class UserUseCases implements IUserUseCases {
    private final UserRepository userRepository;

    @Autowired
    public UserUseCases(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDomain CreateUser(UserBusinessRules user) {
        UserDomain userDomain = new UserDomain(user);
        userDomain.setCreated_at(LocalDateTime.now());
        userRepository.save(userDomain);
        return userDomain;
    }

    @Override
    public String DeleteUser(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Usuario com o ID: " + id + " não encontrado.");
        }
        return id;
    }

    @Override
    public UserDomain UpdateUser(UpdateUserDTO user) {
        UserDomain userDomain = userRepository.findByUsername(user.username());

        if (userDomain.getUsername() != null) {
            userDomain.setUsername(user.usernameNovo());
        }
        if (userDomain.getSenha() != null) {
            userDomain.setSenha(user.senha());
        }

        userDomain.setUpdated_at(LocalDateTime.now());
        userRepository.save(userDomain);
        return userDomain;
    }

    @Override
    public ResponseUserDTO FindByMatricula(String matricula) {
        UserDomain user = userRepository.findByMatricula(matricula);
        return new ResponseUserDTO(user.getUsername(),user.getMatricula(),user.getUser_type(),user.getCreated_at(),user.getUpdated_at(),user.getEvento_evento_id());
    }

    @Override
    public List<ResponseUserDTO> FindAll() {
        return userRepository.findAll();
    }
}
