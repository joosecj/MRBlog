package com.mr.blog.repositories;

import com.mr.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
  User findByEmail(String email);

  @Query("SELECT u FROM User u WHERE u.email =:userEmail")
  User findByUserEmail(@Param("userEmail") String userEmail);
}
