package com.example.demo.model_form;

import lombok.Data;


@Data
public class AddingAssetForm {
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

