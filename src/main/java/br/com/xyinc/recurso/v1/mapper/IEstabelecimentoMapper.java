package br.com.xyinc.recurso.v1.mapper;

/*
    Created by Wesly Vinicius date:29/05/19
*/

import java.util.List;

import org.mapstruct.Mapper;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoDTO;

@Mapper(componentModel = "spring")
public interface IEstabelecimentoMapper {

    Estabelecimento fromEntity(final EstabelecimentoDTO estabelecimentoDTO);

    List<Estabelecimento> fromListEstabelecimento(final List<EstabelecimentoDTO> listaEstabelecimentoDTO);

    EstabelecimentoDTO fromEstabelecimentoDTO(final Estabelecimento estabelecimentoEntity);

    List<EstabelecimentoDTO> fromListEstabelecimentoDTO(final List<Estabelecimento> listaEstabelecimento);
}
