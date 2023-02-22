package com.mr.blog.repositories;

import com.mr.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

  @Query(value = "SELECT u " +
          "FROM User u " +
          "WHERE u.name = :userName")
  return findByUserName(@Param("userName") User userName String);
}
