package com.example.demo.form;

import java.util.List;

import com.example.demo.model.BookingModel;

import lombok.Data;

/**
 * 
 * Object transfer data between Form and Controller Validation from Form here
 * 
 * @author DoPT
 *
 *
 */

@Data
public class GroupBookingForm {

	private List<BookingForm> bkDevices;



}
