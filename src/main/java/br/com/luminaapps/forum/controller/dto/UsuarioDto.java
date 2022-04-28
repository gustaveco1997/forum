package br.com.luminaapps.forum.controller.dto;

import br.com.luminaapps.forum.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsuarioDto {
	private Long id;
	private String nome;
	private String email;
	private boolean ativo;

	public UsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
		this.ativo = usuario.isAtivo();
	}

}
