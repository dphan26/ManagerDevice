package com.example.demo.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "BOOKERS")
public class Booker {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "BOOKER_NAME", nullable = false)
	private String bookerName;

//	@Column(name = "BOOKER_PHOTO", nullable = true)
//	private String bookerPhoto;

//  MAP
	// Mot catogories co nhieu thiet bi
	@OneToMany(mappedBy = "booker")
	private Set<Device> devices;

}
