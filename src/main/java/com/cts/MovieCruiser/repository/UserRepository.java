package com.cts.MovieCruiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.MovieCruiser.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Integer>{



		User findByUsername(String username);

}
