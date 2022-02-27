package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.repository.datamodel.User;
import com.example.demo.repository.datamodel.UserB;



public interface UserBRepository extends JpaRepository<UserB, Long>{
//	List<User> findByUserOrderByUser(String user);

}
