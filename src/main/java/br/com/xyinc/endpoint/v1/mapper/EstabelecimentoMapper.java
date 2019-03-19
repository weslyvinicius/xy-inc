package br.com.xyinc.endpoint.v1.mapper;

import br.com.xyinc.endpoint.v1.dto.EstabelecimentoDTO;
import br.com.xyinc.exception.exception_custom.ResultNotFoundException;
import br.com.xyinc.persistence.entity.Estabelecimento;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
@Component
@Slf4j
public class EstabelecimentoMapper {

    public List<EstabelecimentoDTO> convertListEstabecimentoEntityParaListEstabelecimentoDTO(final List<Estabelecimento> listaEstabelecimento){
        log.info("Inicio do metodo converter uma Lista de Estabelecimentos Entity para Lista de Estbelecimento DTO");
        return Optional.ofNullable(listaEstabelecimento)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::convertEstabecimentoEntityParaEstabelecimentoDTO)
                .collect(Collectors.toList());

    }

    public EstabelecimentoDTO convertEstabecimentoEntityParaEstabelecimentoDTO(final Estabelecimento estabelecimentoEntity){
        log.info("Inicio do metodo converter uma Estabelecimentos Entity para Estbelecimento DTO");
        return Optional.ofNullable(estabelecimentoEntity)
                .map(estab -> EstabelecimentoDTO.builder()
                        .id(estab.getId())
                        .nome(estab.getNome())
                        .posicaoX(estab.getPosicaoX())
                        .posicaoY(estab.getPosicaoY())
                        .build())
                .orElseThrow(() -> new ResultNotFoundException());
    }
}
