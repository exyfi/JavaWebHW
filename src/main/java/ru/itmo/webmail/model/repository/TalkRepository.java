package ru.itmo.webmail.model.repository;

import ru.itmo.webmail.model.domain.Talk;

import java.util.List;

public interface TalkRepository {
    List<Talk> findAll(long userId);
    void createMessage(long sourceUserId, long targetUserId, String message);
}

