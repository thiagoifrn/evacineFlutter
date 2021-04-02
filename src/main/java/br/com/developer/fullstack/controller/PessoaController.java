package br.com.developer.fullstack.controller;

import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.developer.fullstack.domain.Pessoa;
import br.com.developer.fullstack.domain.service.CadastroPessoaService;
import br.com.developer.fullstack.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	private CadastroPessoaService cadastroPessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	@GetMapping
	public List<Pessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity <Pessoa> buscar(@PathVariable Long pessoaId) {
		Optional <Pessoa> pessoa = pessoaRepository.findById(pessoaId);
		//tratando status do médoto
		if (pessoa.isPresent()) {			
			return ResponseEntity.ok(pessoa.get());
		}
		
		return ResponseEntity.notFound().build();		
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa) {
		return  cadastroPessoaService.salvar(pessoa);
	}
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar(@Valid @PathVariable Long pessoaId,
			@RequestBody Pessoa pessoa) {
		
		if(!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}
		pessoa.setId(pessoaId);
		pessoa =  cadastroPessoaService.salvar(pessoa);
		return ResponseEntity.ok(pessoa);
		
	}
	@DeleteMapping("/{pessoaId}")
	//não retorna um corpo por isso Void
	public ResponseEntity<Void> remover(@PathVariable Long pessoaId){
		if(!pessoaRepository.existsById(pessoaId)) {
			ResponseEntity.notFound().build();
		}
		cadastroPessoaService.remover(pessoaId);
		
		return ResponseEntity.noContent().build();
		//Deu sucesso, mas não retorna corpo
	}
}
