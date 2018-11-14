package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.ConfirmRepository;
import ru.itmo.webmail.web.exception.RedirectException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;

public class ConfirmRepositoryImpl implements ConfirmRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();

    @Override
    public void makeSecret(User user,String secret){
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO EmailConfirmation (userId,secret, creationTime) VALUES (?,?,  NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, Long.toString(user.getId()));

                statement.setString(2, secret);
                statement.executeUpdate();


            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save ConfirmRepository.", e);
        }


    }
    @Override
    public long find(String secret) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT userId FROM EmailConfirmation WHERE secret=?")) {
                statement.setString(1, secret);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {

                        return resultSet.getLong(1);
                    }
                }
                throw new RedirectException("/index", "incorrectSecretCode");
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find User by login.", e);
        }
    }



}
