package br.com.luminaapps.forum.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.luminaapps.forum.controller.dto.UsuarioDto;
import br.com.luminaapps.forum.model.Usuario;
import br.com.luminaapps.forum.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> detalheUsuario(@PathVariable("id") Long id) {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent())
			return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new UsuarioDto(usuario.get()));

	}

}
