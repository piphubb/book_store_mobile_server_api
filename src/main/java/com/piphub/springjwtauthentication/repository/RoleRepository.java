package com.piphub.springjwtauthentication.repository;

import com.piphub.springjwtauthentication.models.Role;
import com.piphub.springjwtauthentication.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
