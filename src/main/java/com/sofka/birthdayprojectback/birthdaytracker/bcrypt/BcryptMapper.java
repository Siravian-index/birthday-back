package com.sofka.birthdayprojectback.birthdaytracker.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BcryptMapper {

    private final PasswordEncoder encoder;

    public BcryptMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public String encode(String secret) {
        return encoder.encode(secret);
    }

    public Boolean compare(String secret, String hashSecret) {
        return encoder.matches(secret, hashSecret);
    }
}
