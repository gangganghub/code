package com.origin.dao;


import org.springframework.data.jpa.repository.JpaRepository;

import com.origin.entity.User;

public interface UserDao extends JpaRepository<User, Long>{

	User findByUsername(String username);
}
