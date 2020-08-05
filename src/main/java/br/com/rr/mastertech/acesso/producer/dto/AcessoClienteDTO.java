package br.com.rr.mastertech.acesso.producer.dto;

import java.time.LocalDateTime;

public class AcessoClienteDTO {

    private Integer idPorta;
    private Integer idCliente;
    private Boolean temAcesso;
    private LocalDateTime horario;

    public Integer getIdPorta() {
        return idPorta;
    }

    public void setIdPorta(Integer idPorta) {
        this.idPorta = idPorta;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Boolean getTemAcesso() {
        return temAcesso;
    }

    public void setTemAcesso(Boolean temAcesso) {
        this.temAcesso = temAcesso;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }
}
