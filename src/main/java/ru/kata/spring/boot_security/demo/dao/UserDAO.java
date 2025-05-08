package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.User;
import java.util.List;

public interface UserDAO {

    List<User> getAllUsers();

    void saveNewUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    User findByUsername(String username);

}
