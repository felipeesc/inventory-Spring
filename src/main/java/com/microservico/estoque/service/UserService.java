package com.microservico.estoque.service;

import com.microservico.estoque.domain.User;
import com.microservico.estoque.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class UserService implements AbstractService<User> {

    private UserRepository userRepository;

    @Override
    public Optional<User> findByCode(Long code) {
        return this.userRepository.findById(code);
    }

    @Override
    public Collection<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return this.userRepository.save(user);
    }


    @Override
    public void delete(Long code) {
        userRepository.findById(code).ifPresent(usuario ->
                userRepository.delete(usuario)
        );
    }

    public Optional<User> buscarPorLogin(String login) {
        return this.userRepository.findOneByLogin(login);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String login) {
        return userRepository.findOneWithAuthoritiesByLogin(login);
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities(Long codigo) {
        return userRepository.findOneWithAuthoritiesByCode(codigo);
    }
}
