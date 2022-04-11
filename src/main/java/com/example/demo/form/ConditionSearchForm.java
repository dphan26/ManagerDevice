package com.example.demo.form;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
public class ConditionSearchForm {

	// Search by category
	private String categoryId;

	// Search by version
	private String version;

	// Search by device Id Or Name
	private String deviceIdOrName;

	// Search by Status
	private String site;

	// Search by Status
	private String status;

	// Search by booker
	private String bookerId;

	// Search by BorrowedTime
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime borrowedTime;

	// Search by Returned Time
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime returnedTime;

}
