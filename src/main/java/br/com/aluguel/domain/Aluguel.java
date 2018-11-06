package br.com.aluguel.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Aluguel.
 */
@Entity
@Table(name = "aluguel")
public class Aluguel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "data_retirada", nullable = false)
    private LocalDate data_retirada;

    @NotNull
    @Column(name = "data_prev_devolucao", nullable = false)
    private LocalDate data_prev_devolucao;

    @NotNull
    @Column(name = "data_real_devolucao", nullable = false)
    private LocalDate data_real_devolucao;

    @Column(name = "valor_locacao", precision = 10, scale = 2)
    private BigDecimal valor_locacao;

    @OneToOne
    @JoinColumn(unique = true)
    private Cliente cliente_id;

    @OneToOne
    @JoinColumn(unique = true)
    private Veiculo veiculo_id;

    @OneToOne(mappedBy = "reserva_id")
    @JsonIgnore
    private Reserva reserva_id;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData_retirada() {
        return data_retirada;
    }

    public Aluguel data_retirada(LocalDate data_retirada) {
        this.data_retirada = data_retirada;
        return this;
    }

    public void setData_retirada(LocalDate data_retirada) {
        this.data_retirada = data_retirada;
    }

    public LocalDate getData_prev_devolucao() {
        return data_prev_devolucao;
    }

    public Aluguel data_prev_devolucao(LocalDate data_prev_devolucao) {
        this.data_prev_devolucao = data_prev_devolucao;
        return this;
    }

    public void setData_prev_devolucao(LocalDate data_prev_devolucao) {
        this.data_prev_devolucao = data_prev_devolucao;
    }

    public LocalDate getData_real_devolucao() {
        return data_real_devolucao;
    }

    public Aluguel data_real_devolucao(LocalDate data_real_devolucao) {
        this.data_real_devolucao = data_real_devolucao;
        return this;
    }

    public void setData_real_devolucao(LocalDate data_real_devolucao) {
        this.data_real_devolucao = data_real_devolucao;
    }

    public BigDecimal getValor_locacao() {
        return valor_locacao;
    }

    public Aluguel valor_locacao(BigDecimal valor_locacao) {
        this.valor_locacao = valor_locacao;
        return this;
    }

    public void setValor_locacao(BigDecimal valor_locacao) {
        this.valor_locacao = valor_locacao;
    }

    public Cliente getCliente_id() {
        return cliente_id;
    }

    public Aluguel cliente_id(Cliente cliente) {
        this.cliente_id = cliente;
        return this;
    }

    public void setCliente_id(Cliente cliente) {
        this.cliente_id = cliente;
    }

    public Veiculo getVeiculo_id() {
        return veiculo_id;
    }

    public Aluguel veiculo_id(Veiculo veiculo) {
        this.veiculo_id = veiculo;
        return this;
    }

    public void setVeiculo_id(Veiculo veiculo) {
        this.veiculo_id = veiculo;
    }

    public Reserva getReserva_id() {
        return reserva_id;
    }

    public Aluguel reserva_id(Reserva reserva) {
        this.reserva_id = reserva;
        return this;
    }

    public void setReserva_id(Reserva reserva) {
        this.reserva_id = reserva;
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
        Aluguel aluguel = (Aluguel) o;
        if (aluguel.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), aluguel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Aluguel{" +
            "id=" + getId() +
            ", data_retirada='" + getData_retirada() + "'" +
            ", data_prev_devolucao='" + getData_prev_devolucao() + "'" +
            ", data_real_devolucao='" + getData_real_devolucao() + "'" +
            ", valor_locacao=" + getValor_locacao() +
            "}";
    }
}
