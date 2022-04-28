package br.com.luminaapps.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.luminaapps.forum.model.Resposta;
import lombok.Getter;

@Getter
public class RespostaDto {
	private Long id;
	private String mensagem;
	private String nomeAutor;
	private LocalDateTime data;

	public RespostaDto(Resposta resposta) {
		this.id = resposta.getId();
		this.mensagem = resposta.getMensagem();
		this.nomeAutor = resposta.getAutor().getNome();
		this.data = resposta.getDataCriacao();
	}

}
