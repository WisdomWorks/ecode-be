package com.example.codeE.repository;

import com.example.codeE.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT new com.example.codeE.model.user.User(u.userId, u.username, u.name, u.email, u.role, u.createdDate, u.updatedDate)" +
            " FROM user u WHERE (?1 IS NULL OR u.role = ?1) " +
            "AND (?2 IS NULL OR u.username LIKE %?2%) " +
            "OR (?2 IS NULL OR u.name LIKE %?2%)")
    List<User> findUsersByRoleAndSearchKeyword(String role, String searchKeyword, Pageable pageable);
}
