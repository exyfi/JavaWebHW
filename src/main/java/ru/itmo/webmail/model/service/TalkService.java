package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.TalkRepository;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.TalkRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.util.List;

public class TalkService {

    private TalkRepository talkRepository=new TalkRepositoryImpl();
    private UserRepository userRepository = new UserRepositoryImpl();



    public List<Talk> findAll(long userId) {
        return talkRepository.findAll(userId);
    }

    public void validateEnter(long sourceUserId, String loginOrEmail, String password) throws ValidationException {
        if (loginOrEmail == null || loginOrEmail.isEmpty()) {
            throw new ValidationException("Address is required");
        }
        if (loginOrEmail.contains("@") && loginOrEmail.chars().filter(num -> num == '@').count() != 1) {
            throw new ValidationException("Invalid email or login");
        }


        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        User user =userRepository.findByLogin(loginOrEmail);

        if (user==null) {
            throw new ValidationException("Incorrect email or login");
        }
        talkRepository.createMessage(sourceUserId, user.getId(),password);

    }
}
