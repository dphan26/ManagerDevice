package com.example.demo.form;

import java.util.List;

import javax.validation.Valid;

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
public class ListBookingForm {
	@Valid
	private List<BookingForm> lstBooking;

}
