package ru.itmo.webmail.model.repository.impl;

import ru.itmo.webmail.model.database.DatabaseUtils;
import ru.itmo.webmail.model.domain.Talk;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.RepositoryException;
import ru.itmo.webmail.model.repository.TalkRepository;
import ru.itmo.webmail.model.service.UserService;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalkRepositoryImpl implements TalkRepository {
    private static final DataSource DATA_SOURCE = DatabaseUtils.getDataSource();
    private UserService userService=new UserService();


    @Override
    public List<Talk> findAll(long userId) {
        List<Talk> talks = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM Talk WHERE sourceUserId=? ORDER by id")) {
                statement.setLong(1,userId);
                statement.executeUpdate();
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Talk talk =toTalk(statement.getMetaData(),resultSet);
                        //talk.setTarget(userId);
                        talks.add(talk);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find all users.", e);
        }
        return talks;


    }
    private Talk toTalk(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        Talk talk = new Talk();

        for (int i = 1; i <= metaData.getColumnCount(); i++) {

            String columnName = metaData.getColumnName(i);
            if("id".equalsIgnoreCase(columnName)){
                talk.setSourceId(resultSet.getLong(i));
            }
            else if  ("sourceUserId".equalsIgnoreCase(columnName)) {
                talk.setSourceId(resultSet.getLong(i));
            } else if ("targetUserId".equalsIgnoreCase(columnName)) {
                talk.setTargetId(resultSet.getLong(i));
                talk.setTarget(userService.find(talk.getTargetId()).getLogin());
            } else if ("text".equalsIgnoreCase(columnName)) {
                talk.setText(resultSet.getString(i));
            }else if ("creationTime".equalsIgnoreCase(columnName)) {
                talk.setCreationTime(resultSet.getTimestamp(i));
            }

            else {
                throw new RepositoryException("Unexpected column 'User." + columnName + "'.");
            }
        }

        return talk;
    }
    @Override
    public void createMessage(long sourceUserId,long targetUserId,String message) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Talk (sourceUserId, targetUserId, text, creationTime) VALUES (?, ?,?, NOW())",
                    Statement.RETURN_GENERATED_KEYS)) {
                statement.setLong(1, sourceUserId);
                statement.setLong(2, targetUserId);
                statement.setString(3, message);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't save Event.", e);
        }
    }
}
