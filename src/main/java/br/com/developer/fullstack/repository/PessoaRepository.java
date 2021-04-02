package br.com.developer.fullstack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.developer.fullstack.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

	List<Pessoa> findByNome(String nome);
	List<Pessoa> findByNomeContaining(String nome);
	Pessoa findByEmail(String email);
}
