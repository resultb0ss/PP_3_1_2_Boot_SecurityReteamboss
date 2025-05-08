package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleRepository;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entity.Role;
import ru.kata.spring.boot_security.demo.entity.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleRepository roleRepository) {
        this.userDAO = userDAO;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void saveNewUser(User user) {
        user.setEnabled(true);
        Set<Role> resolvedRoles = new HashSet<>();

        for (Role role : user.getRoles()) {
            roleRepository.findById(role.getId()).ifPresent(resolvedRoles::add);
        }

        if (resolvedRoles.isEmpty()) {
            roleRepository.findById(2L).ifPresent(resolvedRoles::add);
        }

        if (user.getDepartment() == null) {
            user.setDepartment("IT");
        }
        user.setRoles(resolvedRoles);

        if (user.getId() == null || user.getId() == 0) {
            user.setEnabled(true);
            userDAO.saveNewUser(user);
            return;
        }

        User existing = userDAO.getUserById(user.getId());
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            user.setPassword(existing.getPassword());
        }
        user.setEnabled(existing.isEnabled());
        userDAO.saveNewUser(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDAO.getUserById(id);
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        userDAO.deleteUserById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
