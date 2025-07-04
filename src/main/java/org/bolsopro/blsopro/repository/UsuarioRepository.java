package org.bolsopro.blsopro.repository;

import org.bolsopro.blsopro.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByEmailAndSenha(String email, String senha);
}