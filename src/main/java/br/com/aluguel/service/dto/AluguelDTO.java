package br.com.aluguel.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Aluguel entity.
 */
public class AluguelDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate data_retirada;

    @NotNull
    private LocalDate data_prev_devolucao;

    @NotNull
    private LocalDate data_real_devolucao;

    private BigDecimal valor_locacao;

    private Long cliente_idId;

    private String cliente_idCliente_id;

    private Long veiculo_idId;

    private String veiculo_idVeiculo_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_retirada() {
        return data_retirada;
    }

    public void setData_retirada(LocalDate data_retirada) {
        this.data_retirada = data_retirada;
    }

    public LocalDate getData_prev_devolucao() {
        return data_prev_devolucao;
    }

    public void setData_prev_devolucao(LocalDate data_prev_devolucao) {
        this.data_prev_devolucao = data_prev_devolucao;
    }

    public LocalDate getData_real_devolucao() {
        return data_real_devolucao;
    }

    public void setData_real_devolucao(LocalDate data_real_devolucao) {
        this.data_real_devolucao = data_real_devolucao;
    }

    public BigDecimal getValor_locacao() {
        return valor_locacao;
    }

    public void setValor_locacao(BigDecimal valor_locacao) {
        this.valor_locacao = valor_locacao;
    }

    public Long getCliente_idId() {
        return cliente_idId;
    }

    public void setCliente_idId(Long clienteId) {
        this.cliente_idId = clienteId;
    }

    public String getCliente_idCliente_id() {
        return cliente_idCliente_id;
    }

    public void setCliente_idCliente_id(String clienteCliente_id) {
        this.cliente_idCliente_id = clienteCliente_id;
    }

    public Long getVeiculo_idId() {
        return veiculo_idId;
    }

    public void setVeiculo_idId(Long veiculoId) {
        this.veiculo_idId = veiculoId;
    }

    public String getVeiculo_idVeiculo_id() {
        return veiculo_idVeiculo_id;
    }

    public void setVeiculo_idVeiculo_id(String veiculoVeiculo_id) {
        this.veiculo_idVeiculo_id = veiculoVeiculo_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AluguelDTO aluguelDTO = (AluguelDTO) o;
        if (aluguelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluguelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AluguelDTO{" +
            "id=" + getId() +
            ", data_retirada='" + getData_retirada() + "'" +
            ", data_prev_devolucao='" + getData_prev_devolucao() + "'" +
            ", data_real_devolucao='" + getData_real_devolucao() + "'" +
            ", valor_locacao=" + getValor_locacao() +
            ", cliente_id=" + getCliente_idId() +
            ", cliente_id='" + getCliente_idCliente_id() + "'" +
            ", veiculo_id=" + getVeiculo_idId() +
            ", veiculo_id='" + getVeiculo_idVeiculo_id() + "'" +
            "}";
    }
}
