package com.piphub.springjwtauthentication.repository;

import com.piphub.springjwtauthentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsernameAndStatus(String username, String status);

    Optional<User> findByPhoneNumberAndStatus(String phone, String status);

    Optional<User> findByPhoneNumberAndStatusAndChangePassword(String phone, String status,String changePassword);

    Boolean existsByUsernameAndStatus(String username, String status);

    Boolean existsByEmailAndStatus(String email, String status);

    Boolean existsByPhoneNumberAndStatus(String phone, String status);


    List<User> findAllByStatus(String status);

    Optional<User> findByUsername(String username);

    User findByIdAndStatusIn(Integer id, List<String> stringList);
}
