package com.algaworks.brewer.repository.helper.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.algaworks.brewer.model.Cliente;

public interface ClientesQueries {
	
	public Page<Cliente> filtrar(Cliente filtro, Pageable pageable); 


}
