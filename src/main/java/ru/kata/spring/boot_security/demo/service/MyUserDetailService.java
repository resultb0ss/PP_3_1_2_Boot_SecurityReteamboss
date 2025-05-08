package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.entity.User;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserDAO userDAO;

    public MyUserDetailService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Попытка логина: " + username);
        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден: " + username);
        }

        System.out.println("Найден пользователь: " + user.getUsername());
        System.out.println("Пароль (в открытом виде): " + user.getPassword());

        return user;
    }
}
