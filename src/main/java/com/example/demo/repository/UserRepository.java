package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TblUser;



@Repository
public interface UserRepository extends JpaRepository<TblUser, Integer> {
	
	@Query("SELECT u FROM TblUser u WHERE u.userName = :username")
    public TblUser getUserByUsername(@Param("username") String username);
}
