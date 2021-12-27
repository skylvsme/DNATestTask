package tech.dna.task.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.dna.task.user.User;
import tech.dna.task.user.UserDTO;
import tech.dna.task.user.UserService;
import tech.dna.task.common.RestResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public RestResponse<User> getUser(@PathVariable("id") Long userId) {
        return RestResponse.ok(userService.getUserById(userId));
    }

    @GetMapping
    public RestResponse<List<User>> getAllUsers() {
        return RestResponse.ok(userService.getAllUsers());
    }

    @PostMapping
    public RestResponse<User> createUser(@RequestBody UserDTO userDTO) {
        return RestResponse.ok(userService.createUser(userDTO));
    }

    @PatchMapping("/{id}")
    public RestResponse<User> updateUser(
            @PathVariable("id") Long userId,
            @RequestBody Map<String, Object> properties
    ) {
        // It will be a good thing to use an at least javax validations,
        // but I think then I will not have done this in 5 hours
        if (properties.containsKey("id"))
            throw new RuntimeException("Id is not allowed to update");
        if (properties.containsKey("creationDate"))
            throw new RuntimeException("Creation date is not allowed to update");

        return RestResponse.ok(userService.updateUser(userId, properties));
    }

    @DeleteMapping("/{id}")
    public RestResponse<?> deleteUser(@PathVariable("id") Long userId) {
        userService.deleteUser(userId);
        return RestResponse.ok();
    }

}
