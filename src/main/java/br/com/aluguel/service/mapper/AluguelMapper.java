package br.com.aluguel.service.mapper;

import br.com.aluguel.domain.*;
import br.com.aluguel.service.dto.AluguelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Aluguel and its DTO AluguelDTO.
 */
@Mapper(componentModel = "spring", uses = {ClienteMapper.class, VeiculoMapper.class})
public interface AluguelMapper extends EntityMapper<AluguelDTO, Aluguel> {

    @Mapping(source = "cliente_id.id", target = "cliente_idId")
    @Mapping(source = "cliente_id.cliente_id", target = "cliente_idCliente_id")
    @Mapping(source = "veiculo_id.id", target = "veiculo_idId")
    @Mapping(source = "veiculo_id.veiculo_id", target = "veiculo_idVeiculo_id")
    AluguelDTO toDto(Aluguel aluguel);

    @Mapping(source = "cliente_idId", target = "cliente_id")
    @Mapping(source = "veiculo_idId", target = "veiculo_id")
    @Mapping(target = "reserva_id", ignore = true)
    Aluguel toEntity(AluguelDTO aluguelDTO);

    default Aluguel fromId(Long id) {
        if (id == null) {
            return null;
        }
        Aluguel aluguel = new Aluguel();
        aluguel.setId(id);
        return aluguel;
    }
}
