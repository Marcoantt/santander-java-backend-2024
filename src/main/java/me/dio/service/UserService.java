package me.dio.service;

import me.dio.domain.model.User;

public interface UserService {

    //Get methods
    Iterable<User> getAllUsers();

    User getUserById(Long id);

    //Post methods
    User saveUser(User user);

    //Put methods
    User updateUser(Long id, User newUser);

    //Delete methods
    User deleteUser(Long id);
}
