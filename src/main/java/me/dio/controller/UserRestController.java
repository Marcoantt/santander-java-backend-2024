package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.User;
import me.dio.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }




    //Get Methods
    @GetMapping
    @Operation(summary = "Get a list with all users", method = "GET")
    public ResponseEntity<Iterable<User>> getAllUsers() {
        Iterable<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an user by Id", method = "GET")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //Post Methods
    @PostMapping
    @Operation(summary = "Add a new user on repository", method = "POST")
    public ResponseEntity<User> saveUser(@RequestBody User newUser) {
        var userSaved = userService.saveUser(newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userSaved.getId())
                .toUri();
        return ResponseEntity.created(location).body(userSaved);
    }

    //Put Methods
    @PutMapping("/{id}")
    @Operation(summary = "Update an user by Id", method = "PUT")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User newUser) {
        var userUpdated = userService.updateUser(id, newUser);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userUpdated.getId())
                .toUri();
        return ResponseEntity.created(location).body(userUpdated);
    }

    //Delete Methods
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user by Id", method = "DELETE")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        var userDeleted = userService.deleteUser(id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDeleted.getId())
                .toUri();
        return ResponseEntity.created(location).body(userDeleted);
    }
}
