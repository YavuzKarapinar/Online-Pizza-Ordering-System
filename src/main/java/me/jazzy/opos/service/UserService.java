package me.jazzy.opos.service;

import lombok.AllArgsConstructor;
import me.jazzy.opos.model.User;
import me.jazzy.opos.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

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

    public void saveUser(User user) {

        boolean isEmailAlreadyTaken = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(isEmailAlreadyTaken)
            throw new UsernameNotFoundException("Email already taken by another user");

        userRepository.save(user);
    }
}