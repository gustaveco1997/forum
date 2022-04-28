package br.com.luminaapps.forum.config.validacao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroFormularioDto {
	private String field;
	private String message;

}
