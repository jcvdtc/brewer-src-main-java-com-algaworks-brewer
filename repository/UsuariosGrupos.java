package com.algaworks.brewer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.brewer.model.UsuarioGrupo;
import com.algaworks.brewer.model.UsuarioGrupoId;

@Repository
public interface UsuariosGrupos extends JpaRepository<UsuarioGrupo, UsuarioGrupoId> {

}
