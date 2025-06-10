package org.bolsopro.blsopro;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensagem;
    private String tipo;
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Notificacao() {
    }

    public Notificacao(String mensagem, String tipo, LocalDateTime data, Usuario usuario) {
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.data = data;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // ✅ Método auxiliar para pegar o ID do usuário associado
    public Long getUsuarioId() {
        return usuario != null ? usuario.getId() : null;
    }

    public String enviar() {
        return "Notificação: " + mensagem + " | Tipo: " + tipo;
    }
}
