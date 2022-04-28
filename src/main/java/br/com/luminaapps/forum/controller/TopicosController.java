package br.com.luminaapps.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.luminaapps.forum.controller.dto.DetalhesTopicoDto;
import br.com.luminaapps.forum.controller.dto.TopicoDto;
import br.com.luminaapps.forum.controller.dto.TopicoFormUpdate;
import br.com.luminaapps.forum.controller.form.TopicoForm;
import br.com.luminaapps.forum.model.Topico;
import br.com.luminaapps.forum.repository.CursoRepository;
import br.com.luminaapps.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	@Cacheable(value = "listagem_topicos")
	public ResponseEntity<Page<DetalhesTopicoDto>> buscarTodos(@RequestParam(required = false) String nomeCurso,
			@PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 3) Pageable paginacao) {

//		Pageable paginacao =  PageRequest.of(pagina < 1 ? 0 : pagina - 1, quantidade, Direction.ASC, ordenacao);

		Page<Topico> topicos;
		if (nomeCurso != null)
			topicos = topicoRepository.findByCursoNome(nomeCurso, paginacao);
		else
			topicos = topicoRepository.findAll(paginacao);

		return ResponseEntity.ok(DetalhesTopicoDto.convert(topicos));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetalhesTopicoDto> detalhar(@PathVariable("id") Long id) {
		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent())
			return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listagem_topicos", allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		Topico topico = form.convert(cursoRepository);
		topicoRepository.save(topico);

		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listagem_topicos", allEntries = true)
	public ResponseEntity<TopicoDto> update(@PathVariable("id") Long id, @RequestBody @Valid TopicoFormUpdate form,
			UriComponentsBuilder uriBuilder) {

		Optional<Topico> topicoOptional = topicoRepository.findById(id);
		if (!topicoOptional.isPresent())
			return ResponseEntity.notFound().build();

		Topico topico = form.atualizar(id, topicoOptional.get());
		topicoRepository.save(topico);
		return ResponseEntity.ok(new TopicoDto(topico));

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listagem_topicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable("id") Long id) {

		boolean exists = topicoRepository.existsById(id);
		if (exists) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
