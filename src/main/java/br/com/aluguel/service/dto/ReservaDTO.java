package br.com.aluguel.service.dto;

import java.time.LocalDate;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Reserva entity.
 */
public class ReservaDTO implements Serializable {

    private Long id;

    @NotNull
    private LocalDate data_inicial;

    private LocalDate data_final;

    private Long veiculo_idId;

    private String veiculo_idVeiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_inicial() {
        return data_inicial;
    }

    public void setData_inicial(LocalDate data_inicial) {
        this.data_inicial = data_inicial;
    }

    public LocalDate getData_final() {
        return data_final;
    }

    public void setData_final(LocalDate data_final) {
        this.data_final = data_final;
    }

    public Long getVeiculo_idId() {
        return veiculo_idId;
    }

    public void setVeiculo_idId(Long veiculoId) {
        this.veiculo_idId = veiculoId;
    }

    public String getVeiculo_idVeiculo() {
        return veiculo_idVeiculo;
    }

    public void setVeiculo_idVeiculo(String veiculoVeiculo) {
        this.veiculo_idVeiculo = veiculoVeiculo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReservaDTO reservaDTO = (ReservaDTO) o;
        if (reservaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reservaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
            "id=" + getId() +
            ", data_inicial='" + getData_inicial() + "'" +
            ", data_final='" + getData_final() + "'" +
            ", veiculo_id=" + getVeiculo_idId() +
            ", veiculo_id='" + getVeiculo_idVeiculo() + "'" +
            "}";
    }
}
