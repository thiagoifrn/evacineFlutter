package br.com.developer.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.developer.fullstack.domain.GruposPrioridades;

@Repository
public interface GruposPrioridadesRepository extends JpaRepository<GruposPrioridades, Long> {
	
	GruposPrioridades findByNome(String nome);
}
