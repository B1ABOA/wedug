package com.b1aboa.wedug.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.b1aboa.wedug.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{
	
}
