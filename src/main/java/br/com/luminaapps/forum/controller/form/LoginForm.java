package br.com.luminaapps.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Data;

@Data
public class LoginForm {
	@NotNull @NotEmpty
	private String email;
	
	@NotNull @NotEmpty
	private String senha;

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
}
