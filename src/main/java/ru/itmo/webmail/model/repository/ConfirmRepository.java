package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.User;

public interface ConfirmRepository {

    void makeSecret(User user,String secret );
    long find(String secret);
}
