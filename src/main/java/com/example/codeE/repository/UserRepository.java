package com.example.codeE.repository;

import com.example.codeE.model.User;
import org.springframework.data.repository.CrudRepository;
public interface UserRepository extends CrudRepository <User, String> {
}
