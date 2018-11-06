package br.com.aluguel.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the Veiculo entity.
 */
public class VeiculoDTO implements Serializable {

    private Long id;

    @NotNull
    private String marca;

    @NotNull
    private String modelo;

    private String ano;

    private String categoria;

    private BigDecimal valor_diaria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getValor_diaria() {
        return valor_diaria;
    }

    public void setValor_diaria(BigDecimal valor_diaria) {
        this.valor_diaria = valor_diaria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VeiculoDTO veiculoDTO = (VeiculoDTO) o;
        if (veiculoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), veiculoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "VeiculoDTO{" +
            "id=" + getId() +
            ", marca='" + getMarca() + "'" +
            ", modelo='" + getModelo() + "'" +
            ", ano='" + getAno() + "'" +
            ", categoria='" + getCategoria() + "'" +
            ", valor_diaria=" + getValor_diaria() +
            "}";
    }
}
