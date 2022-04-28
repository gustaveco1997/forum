package br.com.luminaapps.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.luminaapps.forum.model.Topico;
import br.com.luminaapps.forum.repository.CursoRepository;
import lombok.Data;

@Data
public class TopicoForm {
	@NotNull @NotEmpty @Length(min =  5 )
	private String titulo;
	
	@NotNull @NotEmpty @Length(min =  10)
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;

	public Topico convert(CursoRepository cursoRepository) {
		return new Topico(titulo, mensagem, cursoRepository.findByNome(nomeCurso));
	}

}
