package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.User;

import java.util.List;

public interface UserRepository {
    User findByEmail(String email);
   // User findByEmailAndPasswordSha(String email, String passwordSha);
    User find(long userId);
    User findByLogin(String login);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    User findByEmailorLoginAndPasswordSha(String loginOrEmail, String passwordSha);

    List<User> findAll();
    void save(User user, String passwordSha);
    void confirmUpdate(long userId);
}
