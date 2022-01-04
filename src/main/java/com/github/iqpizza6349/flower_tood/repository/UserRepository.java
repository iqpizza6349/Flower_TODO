package com.github.iqpizza6349.flower_tood.repository;

import com.github.iqpizza6349.flower_tood.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);   // 이메일 통해 회원 조회
}
