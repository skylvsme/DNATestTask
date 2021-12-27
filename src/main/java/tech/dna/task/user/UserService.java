package tech.dna.task.user;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import tech.dna.task.common.util.PasswordEncoder;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public User createUser(UserDTO userDTO) {
        val user = new User();
        user.setLogin(userDTO.getLogin());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setCreationDate(LocalDate.now());

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with specified id not found"));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, Map<String, Object> userProperties) {
        // I think it's kinda incorrect to use public method of Spring component in itself
        // so if we add some new functionality (like permission checks with annotations)
        // this method will not work correct cause of Spring proxy specific usage
        val user = getUserById(userId);

        // Encrypt password
        if (userProperties.containsKey("password"))
            userProperties.put(
                    "password",
                    passwordEncoder.encode(userProperties.get("password").toString())
            );

        // I'm using model mapper to pass non-null values to my entity
        // But I'm aware of any unexpected value
        // e.g. if I have a User with join columns (like in offer)
        // it wouldn't be good to allow the target user to modify join-entity such as Category
        // by using inner fields in JSON: { "name": "John Doe", "category": { "name": "Non-IT" } }
        modelMapper.map(userProperties, user);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

}
