package com.assessment.icinbank.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE user_role=:role",nativeQuery = true)
    List<User> findAllByUserRole(@Param("role") String role);

    @Query(value = "SELECT * FROM users WHERE user_id=:id",nativeQuery = true)
    User findByUserId(@Param("id") Long id);
}
