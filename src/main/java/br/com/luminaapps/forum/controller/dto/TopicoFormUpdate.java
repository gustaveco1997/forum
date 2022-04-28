package br.com.luminaapps.forum.controller.dto;

import br.com.luminaapps.forum.model.Topico;
import lombok.Data;

@Data
public class TopicoFormUpdate {
	private String titulo;
	private String mensagem;

	public Topico atualizar(Long id, Topico topico) {		
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);
		return topico;
	}
}
