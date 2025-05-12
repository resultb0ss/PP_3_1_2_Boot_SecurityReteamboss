package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.List;

public interface RoleDAO {

    List<Role> getAllRoles();

    void addNewRole(Role role);

    Role getRoleById(Long id);

}
