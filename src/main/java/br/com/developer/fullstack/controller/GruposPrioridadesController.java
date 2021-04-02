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

import br.com.developer.fullstack.domain.GruposPrioridades;
import br.com.developer.fullstack.domain.service.CadastroGruposPrioridadesService;
import br.com.developer.fullstack.repository.GruposPrioridadesRepository;

@RestController
@RequestMapping("/grupos")
public class GruposPrioridadesController {
	
	@Autowired
	CadastroGruposPrioridadesService gruposService;
	
	@Autowired
	private GruposPrioridadesRepository gruposPrioridadesRepository;
	
	@GetMapping
	public List<GruposPrioridades> listar(){
		return gruposPrioridadesRepository.findAll();
	}
	
	@GetMapping("/{gruposPrioridadesId}")
	public ResponseEntity<GruposPrioridades> buscar(@PathVariable Long gruposPrioridadesId) {
		 
		Optional<GruposPrioridades> gruPrioridades = gruposPrioridadesRepository.findById(gruposPrioridadesId);
		
		if(gruPrioridades.isPresent()) {
			return ResponseEntity.ok(gruPrioridades.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GruposPrioridades adicionar(@Valid 
			@RequestBody GruposPrioridades gruposPrioridades ){
		return gruposService.salvar(gruposPrioridades);
	}
	
	@PutMapping("/{grupoPrioridadeId}")
	public ResponseEntity<GruposPrioridades> atualizar(@Valid @PathVariable Long grupoPrioridadeId,
			@RequestBody GruposPrioridades gruposPrioridades){
		if(!gruposPrioridadesRepository.existsById(grupoPrioridadeId)) {
			ResponseEntity.notFound().build();
		}
		gruposPrioridades.setId(grupoPrioridadeId);
		gruposPrioridades = gruposService.salvar(gruposPrioridades);
		
		return ResponseEntity.ok(gruposPrioridades);
	}
	
	@DeleteMapping("/{grupoPrioridadeId}")
	public ResponseEntity<Void> excluir(@Valid @PathVariable Long grupoPrioridadeId){
		if(!gruposPrioridadesRepository.existsById(grupoPrioridadeId)) {
			return ResponseEntity.notFound().build();
		}
		gruposService.excluir(grupoPrioridadeId);
		return ResponseEntity.noContent().build();
	}
}
