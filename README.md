# Spring Boot Security CRUD Application

## 📌 Описание

Это полнофункциональное CRUD-приложение на Spring Boot с авторизацией и аутентификацией, реализованной через Spring Security. Приложение позволяет управлять пользователями и их ролями (ROLE_USER / ROLE_ADMIN), выполнять операции добавления, редактирования, удаления, и переключения активности пользователя (enabled) — **без перезагрузки страницы**, используя **Fetch API** и **Bootstrap 5** для адаптивного интерфейса.

---

## 🔧 Технологии

- Java 11+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- Thymeleaf
- MySQL
- Bootstrap 5
- Fetch API (JS)
- Maven

---

## 🔐 Роли

| Роль        | Доступ                              |
|-------------|-------------------------------------|
| `ROLE_ADMIN`| `/admin`, `/user`, REST API         |
| `ROLE_USER` | `/user` только к личной странице    |

---

## ✅ Функциональность

- 🔑 Аутентификация с разделением по ролям
- 🔐 Авторизация: доступ к страницам по ролям
- 👤 Просмотр/создание/редактирование/удаление пользователей
- 👥 Назначение ролей пользователям (многие-ко-многим)
- 📂 Указание департамента (IT / HR / Sales)
- ✅ Включение/отключение пользователя (enabled toggle)
- 🔁 Полностью динамичный интерфейс (без перезагрузок)
- 🔓 Logout с любой страницы

---

## 📦 Структура

├── controller/
│ ├── UserController.java # Страницы /admin и /user
│ └── UserRestController.java # REST API (CRUD)
│
├── service/
│ ├── UserService.java
│ ├── UserServiceImpl.java
│ ├── RoleService.java
│ └── RoleServiceImpl.java
│
├── dao/
│ ├── UserDAO.java
│ ├── UserDAOImpl.java
│ └── RoleRepository.java
│
├── entity/
│ ├── User.java # implements UserDetails
│ └── Role.java # implements GrantedAuthority
│
├── security/
│ ├── WebSecurityConfig.java
│ └── SuccessUserHandler.java
│
├── templates/
│ ├── login.html
│ ├── user.html
│ └── admin.html


---

## 🗂 Пример данных для БД

```sql
-- Роли
INSERT INTO roles (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER');

-- Пользователи
INSERT INTO users (id, department, enabled, name, password, salary, surname, username)
VALUES (1, 'IT', true, 'Admin', '{noop}admin', 100000, 'Adminov', 'admin');

-- Связь user-role
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (1, 2);
