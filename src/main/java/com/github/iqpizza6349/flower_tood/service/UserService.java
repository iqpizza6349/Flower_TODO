package com.github.iqpizza6349.flower_tood.service;

import com.github.iqpizza6349.flower_tood.dto.UserDTO;
import com.github.iqpizza6349.flower_tood.entity.User;
import com.github.iqpizza6349.flower_tood.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserDTO userDTO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));

        return userRepository.save(User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .auth(userDTO.getAuth())
                .build())
                .getAutoID();
    }

}
