package org.bolsopro.blsopro.repository;

import org.bolsopro.blsopro.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}