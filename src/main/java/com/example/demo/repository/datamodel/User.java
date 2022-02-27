package com.example.demo.repository.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private String name;
    private String email;
    private int age;
	
	@Override
	public String toString() {
		return "User [aid=" + id + ", aname=" + name + ", mail" + email + "]";
	}
     
  
}

//}
