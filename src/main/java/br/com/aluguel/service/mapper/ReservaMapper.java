package br.com.aluguel.service.mapper;

import br.com.aluguel.domain.*;
import br.com.aluguel.service.dto.ReservaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reserva and its DTO ReservaDTO.
 */
@Mapper(componentModel = "spring", uses = {VeiculoMapper.class})
public interface ReservaMapper extends EntityMapper<ReservaDTO, Reserva> {

    @Mapping(source = "veiculo_id.id", target = "veiculo_idId")
    @Mapping(source = "veiculo_id.veiculo", target = "veiculo_idVeiculo")
    ReservaDTO toDto(Reserva reserva);

    @Mapping(target = "cliente_id", ignore = true)
    @Mapping(source = "veiculo_idId", target = "veiculo_id")
    Reserva toEntity(ReservaDTO reservaDTO);

    default Reserva fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reserva reserva = new Reserva();
        reserva.setId(id);
        return reserva;
    }
}
