package br.com.aluguel.service.mapper;

import br.com.aluguel.domain.*;
import br.com.aluguel.service.dto.VeiculoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Veiculo and its DTO VeiculoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface VeiculoMapper extends EntityMapper<VeiculoDTO, Veiculo> {



    default Veiculo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Veiculo veiculo = new Veiculo();
        veiculo.setId(id);
        return veiculo;
    }
}
