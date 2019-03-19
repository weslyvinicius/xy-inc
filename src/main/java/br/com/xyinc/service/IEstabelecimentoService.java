package br.com.xyinc.service;

import br.com.xyinc.persistence.entity.Estabelecimento;
import br.com.xyinc.recurso.v1.dto.EstabelecimentoPorDistanciaDTO;

import java.util.List;

/**
 * Criado por @autor wcardoso2  em 3/18/2019
 **/
public interface IEstabelecimentoService {

    /**
     * Serviço de busca todos os estabelecimentos cadastrados.
     * @return Lista de Estabelecimentos
     */
    List<Estabelecimento> listAll();

    /**
     * Serviço de salvar o cadastro de um estabelecimento
     * @param estabelecimento
     */
    Estabelecimento salvar(final Estabelecimento estabelecimento);

    /**
     * Serviço de busca dos estabelecimetos por raio
     * @param estabelecimentoPorDistanciaDTO
     * @return Lista de Estabelecimetos
     */
    List<Estabelecimento> buscaEstabelecimentosPorRaio(final EstabelecimentoPorDistanciaDTO estabelecimentoPorDistanciaDTO);
}
