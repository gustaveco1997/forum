package br.com.luminaapps.forum.config.security;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Token {
	private String token;
	private Date expiration;	

}
