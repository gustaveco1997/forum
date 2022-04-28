package br.com.luminaapps.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.luminaapps.forum.model.Usuario;
import br.com.luminaapps.forum.repository.UsuarioRepository;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository usuarioRepository;

	public AutenticacaoTokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);
		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);
	}

	private void autenticarCliente(String token) {
		Long usuarioID = tokenService.getIdUsuario(token);
		Usuario usuario = usuarioRepository.findById(usuarioID).get();

		Authentication autentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());
		SecurityContextHolder.getContext().setAuthentication(autentication);
	}

	private String recuperarToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		if (authorization == null || authorization.isEmpty())
			return null;

		authorization = authorization.trim();
		if (!authorization.startsWith("Bearer "))
			return null;

		return authorization.substring(7, authorization.length());
	}

}
