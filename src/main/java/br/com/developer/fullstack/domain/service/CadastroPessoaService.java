package br.com.developer.fullstack.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.developer.fullstack.domain.Pessoa;
import br.com.developer.fullstack.domain.exception.NegocioException;
import br.com.developer.fullstack.repository.PessoaRepository;

@Service
public class CadastroPessoaService {
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		Pessoa pessoaExistente = pessoaRepository.findByEmail(pessoa.getEmail());
		
		if(pessoaExistente != null && !pessoaExistente.equals(pessoa)) {
			throw new NegocioException("Email já está cadastrado");
		}
		
		return pessoaRepository.save(pessoa);
	}
	
	public void remover(Long pessoaId) {
		pessoaRepository.deleteById(pessoaId);
	}

}
