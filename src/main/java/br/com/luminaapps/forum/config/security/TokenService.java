package br.com.luminaapps.forum.config.security;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.luminaapps.forum.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${forum.jwt.expiration}") // pega valor lá do application.properties
	private Integer expirationMinutes;

	@Value("${forum.jwt.secret}") // pega valor lá do application.properties
	private String secret;

	public Token gerarToken(Authentication auth) {
		Usuario logado = (Usuario) auth.getPrincipal();

		Date hoje = new Date();
		Calendar expiration = Calendar.getInstance();
		expiration.setTimeZone(TimeZone.getTimeZone("UTC"));
		expiration.setTime(hoje);
		expiration.add(Calendar.MINUTE, expirationMinutes);

		String token = Jwts.builder().setIssuer("API Fórum LuminaApps").setSubject(logado.getId().toString())
				.setIssuedAt(new Date()).setExpiration(expiration.getTime()).signWith(SignatureAlgorithm.HS256, secret)
				.compact().toString();

		return new Token(token, expiration.getTime());
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
