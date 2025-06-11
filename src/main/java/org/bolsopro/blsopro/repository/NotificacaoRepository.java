package org.bolsopro.blsopro.repository;

import org.bolsopro.blsopro.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {

    List<Notificacao> findByUsuario_Id(Long usuarioId);
}
