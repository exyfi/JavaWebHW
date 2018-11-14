package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.ConfirmRepository;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.impl.ConfirmRepositoryImpl;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;

import java.nio.charset.Charset;
import java.util.Random;

public class ConfirmService {

    private ConfirmRepository confirmRepository = new ConfirmRepositoryImpl();
    public void make(User user) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String secret = buffer.toString();
        confirmRepository.makeSecret(user,secret);

    }
    public long find(String secret ){
        return confirmRepository.find(secret);
    }

}
