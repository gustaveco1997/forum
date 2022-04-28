package br.com.luminaapps.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.luminaapps.forum.model.StatusTopico;
import br.com.luminaapps.forum.model.Topico;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetalhesTopicoDto {

	private Long id;
	private String titulo;
	private String mensagem;
	private String nomeCurso;
	private String nomeAutor;
	private StatusTopico status;
	private List<RespostaDto> respostas;
	private LocalDateTime dataCricao;

	public DetalhesTopicoDto(Topico topico) {
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.nomeAutor = topico.getAutor() != null ? topico.getAutor().getNome() : null;
		this.nomeCurso = topico.getCurso() != null ? 	topico.getCurso().getNome() : null;
		this.status = topico.getStatus();
		this.mensagem = topico.getMensagem();
		this.dataCricao = topico.getDataCriacao();

		this.respostas = new ArrayList<>();

		if (topico.getRespostas() != null)
			this.respostas = topico.getRespostas().stream().map(RespostaDto::new).collect(Collectors.toList());
		else
			this.respostas = new ArrayList<>();	
	}

	public static List<DetalhesTopicoDto> convert(List<Topico> topicos) {
		return topicos.stream().map(DetalhesTopicoDto::new).collect(Collectors.toList());
	}
	
	public static Page<DetalhesTopicoDto> convert(Page<Topico> topicos) {
		return topicos.map(DetalhesTopicoDto::new);
	}

}
