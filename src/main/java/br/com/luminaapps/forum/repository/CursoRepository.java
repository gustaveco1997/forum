package br.com.luminaapps.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.luminaapps.forum.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
	Curso findByNome(String nome);

	Curso findByCategoria(String categoria);
}
