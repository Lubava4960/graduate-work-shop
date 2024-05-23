package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;

@AllArgsConstructor
@Service
public class UserServiceImpl {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    public User getUser(Authentication authentication) {
        User user = userMapper.userToUserDto(
                userRepository.findByUsername(authentication.getName()).orElseThrow()
        );
        user.setImage("/users/" + user.getId() + "/image");
        return user;
    }

    public void updateUser(UpdateUser updateUser, Authentication authentication) {
        ru.skypro.homework.entity.User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(user.getPhone());
        userRepository.save(user);
    }

    public byte[] updateAvatar(MultipartFile multipartFile, Authentication authentication) throws IOException {
        ru.skypro.homework.entity.User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
        user.setImage(multipartFile.getBytes());
        userRepository.save(user);
        return user.getImage();
    }

    public byte[] getAvatar(int id) {
        ru.skypro.homework.entity.User user = userRepository.findById(id).orElseThrow();
        return user.getImage();
    }

   public void changePassword(NewPassword newPassword,Authentication authentication){
       ru.skypro.homework.entity.User user = userRepository.findByUsername(authentication.getName()).orElseThrow();
       if(passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())){
           user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
       }
   }
}
