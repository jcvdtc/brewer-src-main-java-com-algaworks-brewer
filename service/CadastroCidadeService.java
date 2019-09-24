package com.algaworks.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.Cidades;
import com.algaworks.brewer.service.exception.CidadeJaCadastradaException;
import com.algaworks.brewer.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroCidadeService {
	
	@Autowired
	Cidades cidades;
	
	@Transactional
	public void salvar(Cidade cidade) {
		Optional<Cidade> cidadeExistente = cidades.findByNomeAndEstado(cidade.getNome(), cidade.getEstado());
		
		if (cidadeExistente.isPresent() && !cidadeExistente.get().equals(cidade)) {
			throw new CidadeJaCadastradaException("Cidade já cadastrada");
		}
		cidades.save(cidade);	
	}
	
	@Transactional
	public void excluir(Long codigo) {
		try {
			cidades.deleteById(codigo);
			cidades.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir cidade.");
		}
	}
}
