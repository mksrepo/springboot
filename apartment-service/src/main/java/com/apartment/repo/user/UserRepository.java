package com.apartment.repo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apartment.entity.user.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
