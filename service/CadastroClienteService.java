package com.algaworks.brewer.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.Cidades;
import com.algaworks.brewer.repository.Clientes;
import com.algaworks.brewer.service.exception.CpfCnpjClienteJaCadastradoException;
import com.algaworks.brewer.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroClienteService {

	@Autowired
	Clientes clientes;
	
	@Autowired
	Cidades cidades;

	@Transactional
	public void salvar(Cliente cliente) {

		Optional<Cliente> clienteExistente = clientes.findByCpfOuCnpj(cliente.getCpfOuCnpjSemFormatacao());

		if (clienteExistente.isPresent() && !clienteExistente.get().equals(cliente)) {
			throw new CpfCnpjClienteJaCadastradoException("CPF/CNPJ já cadastrado");
		}
		clientes.save(cliente);	
	}

	public void comporDadosEndereco(Cliente cliente) {

		if ((cliente.getEndereco() == null) 
					|| (cliente.getEndereco().getCidade() == null) 
					|| (cliente.getEndereco().getCidade().getCodigo() == null)) {
			return;
		}
		
		Cidade cidade = cidades.findByCodigoFetchingEstado(cliente.getEndereco().getCidade().getCodigo());
		
		cliente.getEndereco().setCidade(cidade);
		cliente.getEndereco().setEstado(cidade.getEstado());
	}

	@Transactional
	public void excluir(Cliente cliente) {
		try {
			clientes.delete(cliente);
			clientes.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível excluir cliente. Provavelmente já está associado a uma venda");
		}

	}
}
