package com.example.demo.repository.datamodel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "Assets")
public class AssetUser {
    @Id
    private String id_asset;   
    private String name;
    private String account;
    private String department;
    private String ip;
    private String received_date;
    private String status;
    private String email;
    private String site;
    private String update_stamp;
    private String update_user;
    private String delete_ts;
    private String delete_user;
}

