package com.example.codeE.repository;

import com.example.codeE.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    String getUsersByRoleAndSearchKeywordSql = "SELECT new com.example.codeE.model.user.User(u.userId, u.name, u.email, u.username, u.role, u.createdDate, u.updatedDate)" +
            " FROM user u WHERE (?1 IS NULL OR u.role = ?1) " +
            "AND (?2 IS NULL OR u.username LIKE %?2%) " +
            "OR (?2 IS NULL OR u.name LIKE %?2%)";

    String getUserByRoleAndUserNameSql = "SELECT new com.example.codeE.model.user.User(u.userId, u.name, u.email, u.username, u.role, u.createdDate, u.updatedDate)" +
            " FROM user u WHERE (?1 IS NULL OR u.role = ?1) " +
            "AND (?2 IS NULL OR u.username = ?2)";

    String getUserByUserName = "SELECT u FROM User u WHERE u.username = :username";
    @Query(value = getUsersByRoleAndSearchKeywordSql)
    List<User> findUsersByRoleAndSearchKeywordWithPagination(String role, String searchKeyword, Pageable pageable);

    @Query(value = getUsersByRoleAndSearchKeywordSql)
    List<User> findUsersByRoleAndSearchKeyword(String role, String searchKeyword);

    @Query(getUserByRoleAndUserNameSql)
    User findUserByRoleAndUserName(String role, String userName);

    @Query(value = getUserByUserName)
    public User findByUserName(@Param("username") String username);
}
