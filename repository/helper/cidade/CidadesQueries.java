package com.algaworks.brewer.repository.helper.cidade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Cidade;

public interface CidadesQueries {
	
	public Page<Cidade> filtrar(Cidade filtro, Pageable pageable);

}
