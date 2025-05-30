package org.bolsopro;

import java.time.LocalDateTime;

public class Notificacao {
    private int id;
    private String mensagem;
    private String tipo;
    private LocalDateTime data;
    private int usuarioId;

    public Notificacao(int id, String mensagem, String tipo, LocalDateTime data, int usuarioId) {
        this.id = id;
        this.mensagem = mensagem;
        this.tipo = tipo;
        this.data = data;
        this.usuarioId = usuarioId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String enviar() {
        return "Notificação: " + mensagem + " | Tipo: " + tipo;
    }
}
