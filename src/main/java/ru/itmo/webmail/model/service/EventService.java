package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.repository.EventRepository;
import ru.itmo.webmail.model.repository.impl.EventRepositoryImpl;

public class EventService {
    private EventRepository eventRepository = new EventRepositoryImpl();
    public void make(User user, Event event) {
        eventRepository.makeEvent(user,event);

    }
}
