package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

@SuppressWarnings("UnstableApiUsage")
public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";

    private UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user,  String password) throws ValidationException {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }
        if(userRepository.findByEmail(user.getEmail())!=null){
            throw new ValidationException("Email is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
    }

    public void register(User user, String password) {
        String passwordSha = getPasswordSha(password);
        byte[] array = new byte[7]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        userRepository.save(user, passwordSha);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void validateEnter(String loginOrEmail, String password) throws ValidationException {
        if (loginOrEmail == null || loginOrEmail.isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (loginOrEmail.contains("@") && loginOrEmail.chars().filter(num -> num == '@').count() != 1) {
            throw new ValidationException("Invalid email or login");
        }


        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }

        if (userRepository.findByEmailorLoginAndPasswordSha(loginOrEmail, getPasswordSha(password)) == null) {
                throw new ValidationException("Incorrect email or login");
            }

        }



    private String getPasswordSha(String password) {
        return Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
    }

    public User authorize(String loginOrEmail, String password) {
       /* if(loginOrEmail.contains("@")){
            return userRepository.findByEmailAndPasswordSha(loginOrEmail, getPasswordSha(password));
        }*/
       return userRepository.findByEmailorLoginAndPasswordSha(loginOrEmail,getPasswordSha(password));
        //return userRepository.findByLoginAndPasswordSha(loginOrEmail, getPasswordSha(password));
    }

    public User find(long userId) {
        return userRepository.find(userId);
    }
    public void confirmUpdate(long userId){
         userRepository.confirmUpdate(userId);
    }
}
