package com.example.codeE.repository;

import com.example.codeE.model.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    String getUsersByRoleAndSearchKeywordSql = "SELECT new com.example.codeE.model.user.User(u.userId, u.name, u.email, u.username, u.role, u.createdDate, u.updatedDate)" +
            " FROM user u WHERE (?1 IS NULL OR u.role = ?1) " +
            "AND (?2 IS NULL OR u.username LIKE %?2%) " +
            "OR (?2 IS NULL OR u.name LIKE %?2%)";

    String getUserByRoleAndUserNameSql = "SELECT new com.example.codeE.model.user.User(u.userId, u.name, u.email, u.username, u.role, u.createdDate, u.updatedDate)" +
            " FROM user u WHERE (?1 IS NULL OR u.role = ?1) " +
            "AND (?2 IS NULL OR u.username = ?2)";

    String getListOfUsersByRoleSql = "SELECT * FROM user WHERE CASE WHEN ?1 IS NULL THEN TRUE ELSE role = ?1 END";
    String getUserByUserName = "Select u.user_id, u.username, u.name, u.email, u.password, u.role, u.created_date, u.updated_date " +
            "FROM user u WHERE u.username = ?1";
    String getUserByUserId = "Select u.user_id, u.username, u.name, u.email, u.password, u.role, u.created_date, u.updated_date " +
            "FROM user u WHERE u.user_id = ?1";
    @Query(value = getUsersByRoleAndSearchKeywordSql)
    List<User> findUsersByRoleAndSearchKeywordWithPagination(String role, String searchKeyword, Pageable pageable);

    @Query(value = getUsersByRoleAndSearchKeywordSql)
    List<User> findUsersByRoleAndSearchKeyword(String role, String searchKeyword);

    @Query(getUserByRoleAndUserNameSql)
    User findUserByRoleAndUserName(String role, String userName);

    @Query(value = getListOfUsersByRoleSql, nativeQuery = true)
    List<User> findUsersByRole(String role);

    String getTeacherInCourse = "SELECT u.user_id, u.username, u.name, u.email, u.password, u.role, u.created_date, u.updated_date " +
            "FROM codee.user u INNER JOIN codee.course_teacher ct ON  u.user_id = ct.teacher_id " +
            "Where ct.course_id = ?1 AND ct.is_main = true";
    @Query(value = getTeacherInCourse, nativeQuery = true)
    User getTeacherInCourse(String courseId);
    @Query(value = "SELECT u.user_id, u.username, u.name, u.email, u.password, u.role, u.created_date, u.updated_date " +
            "FROM user u INNER JOIN course_student cs ON u.user_id = cs.student_id " +
            "WHERE cs.course_id = ?1", nativeQuery = true)
    List<User> getUserInCourse(String courseId);
    @Query(value = getUserByUserName, nativeQuery = true)
    User findUserByUserName(String userName);
    @Query(value = getUserByUserId, nativeQuery = true)
    User findUserByUserId(String userId);
}
