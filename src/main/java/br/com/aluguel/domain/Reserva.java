package br.com.aluguel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Reserva.
 */
@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_inicial", nullable = false)
    private LocalDate data_inicial;

    @Column(name = "data_final")
    private LocalDate data_final;

    @OneToOne(mappedBy = "cliente_id")
    @JsonIgnore
    private Cliente cliente_id;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculo_id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_inicial() {
        return data_inicial;
    }

    public Reserva data_inicial(LocalDate data_inicial) {
        this.data_inicial = data_inicial;
        return this;
    }

    public void setData_inicial(LocalDate data_inicial) {
        this.data_inicial = data_inicial;
    }

    public LocalDate getData_final() {
        return data_final;
    }

    public Reserva data_final(LocalDate data_final) {
        this.data_final = data_final;
        return this;
    }

    public void setData_final(LocalDate data_final) {
        this.data_final = data_final;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public Reserva cliente_id(Cliente cliente) {
        this.cliente_id = cliente;
        return this;
    }

    public void setCliente_id(Cliente cliente) {
        this.cliente_id = cliente;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public Reserva veiculo_id(Veiculo veiculo) {
        this.veiculo_id = veiculo;
        return this;
    }

    public void setVeiculo_id(Veiculo veiculo) {
        this.veiculo_id = veiculo;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Reserva reserva = (Reserva) o;
        if (reserva.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reserva.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Reserva{" +
            "id=" + getId() +
            ", data_inicial='" + getData_inicial() + "'" +
            ", data_final='" + getData_final() + "'" +
            "}";
    }
}
