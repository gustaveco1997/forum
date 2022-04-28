package br.com.luminaapps.forum.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.luminaapps.forum.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
	List<Topico> findByTitulo(String titulo);

	@Query("SELECT t FROM Topico t where t.curso.nome = :curso")
	List<Topico> findByCurso(@Param("curso") String curso);
	
	Page<Topico> findByCursoNome(String curso, Pageable pageable);

}
