package com.example.frontToBack.repository;

import com.example.frontToBack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepository extends JpaRepository<User,Long> {


}
