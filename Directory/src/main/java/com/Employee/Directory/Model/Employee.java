package com.Employee.Directory.Model;


import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	@Size(min = 2, max = 80)
	private String name;
	
	@NotBlank
	@Size(max = 120)
	@Email
	@Column(unique = true)
	private String email;
	
	@NotBlank
	@Size(min = 1,max = 50)
	private String department;
	
	@Size(max = 50)
	private String role;
	
	@NotNull
	@PastOrPresent
	private LocalDate joinedOn;
}
