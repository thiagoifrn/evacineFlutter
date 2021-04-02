package br.com.developer.fullstack.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.developer.fullstack.domain.GruposPrioridades;
import br.com.developer.fullstack.domain.exception.NegocioException;
import br.com.developer.fullstack.repository.GruposPrioridadesRepository;

@Service
public class CadastroGruposPrioridadesService {
	
	@Autowired
	private GruposPrioridadesRepository gruposPrioridadesRepository;
	
	public GruposPrioridades salvar(GruposPrioridades gruposPrioridades) {
		GruposPrioridades gruposPrioridadesExistente = gruposPrioridadesRepository.findByNome(gruposPrioridades.getNome());
		
		if(gruposPrioridadesExistente != null && !gruposPrioridadesExistente.equals(gruposPrioridades)) {
			throw new NegocioException("Esta prioridade Já foi cadastrada");
		}		
		return gruposPrioridadesRepository.save(gruposPrioridades);
	}
	
	public void excluir(Long grupoPrioridadeId) {
		
		gruposPrioridadesRepository.deleteById(grupoPrioridadeId);
	}
	
}
