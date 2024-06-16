package com.SpringSecurity.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class UserDTO {
	private String name;
	private String userName;
	private String email;
	private String password;
	private String address;
	private String phoneNo;
}
