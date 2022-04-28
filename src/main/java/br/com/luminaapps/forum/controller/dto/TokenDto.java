package br.com.luminaapps.forum.controller.dto;

import java.util.Date;

import br.com.luminaapps.forum.config.security.Token;
import lombok.Data;

@Data
public class TokenDto {

	private String token;
	private String type;
	private Date expiration;

	public TokenDto(Token token, String type) {
		this.token = token.getToken();
		this.expiration = token.getExpiration();	
		this.type = type;
	}

}
