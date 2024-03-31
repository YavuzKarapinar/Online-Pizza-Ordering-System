package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.dto.UserDto;
import me.jazzy.opos.exception.badrequest.UserBadRequestException;
import me.jazzy.opos.exception.notfound.UserNotFoundException;
import me.jazzy.opos.model.User;
import me.jazzy.opos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                    .orElseThrow(() ->
                            new UsernameNotFoundException("User with " + email + " not found"));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("There is no such user."));
    }

    public User getUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User with " + email + " not found"));
    }

    public void saveUser(User user) {

        boolean isEmailAlreadyTaken = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(isEmailAlreadyTaken)
            throw new UserBadRequestException("Email already taken by another user");

        userRepository.save(user);
    }

    public User updateUser(UserDto userDto) {

        User user = getUserById(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setContactNumber(userDto.getContactNumber());

        userRepository.save(user);

        return user;
    }
}