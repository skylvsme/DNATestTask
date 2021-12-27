package tech.dna.task.common.util;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {

    private BCrypt.Hasher hasher = BCrypt.withDefaults();

    private static final int PASSWORD_COST = 12;

    public String encode(String password) {
        return hasher.hashToString(PASSWORD_COST, password.toCharArray());
    }

}
