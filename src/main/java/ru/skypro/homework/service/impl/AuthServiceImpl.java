package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

   private final UserRepository userRepository;
    private final PasswordEncoder encoder;



    @Override
    public boolean login(String userName, String password) {
        if (!userRepository.existsByUsername(userName)) {
            return false;
        }
        ru.skypro.homework.entity.User user = userRepository.findByUsername(userName).orElseThrow(()->new UsernameNotFoundException("Юзер не найден"));
        return encoder.matches(password, user.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.existsByUsername(register.getUsername())) {
            return false;
        }
        userRepository.save(
                User.builder()
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .password(encoder.encode(register.getPassword()))
                        .username(register.getUsername())
                        .role(register.getRole())
                        .phone(register.getPhone())
                        .build());
        return true;
    }

}
