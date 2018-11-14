package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Event;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.EventRepository;


import javax.sql.DataSource;
import java.sql.*;

public class EventRepositoryImpl implements EventRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

@Override
    public  void makeEvent(User user, Event event) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Event (userId, type, creationTime) VALUES (?, ?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, Long.toString(user.getId()));
                statement.setString(2, event.getType());
               statement.executeUpdate();


            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Event.", e);
        }
    }
}


